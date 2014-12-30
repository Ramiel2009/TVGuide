package ua.viasat.tvguide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nshkarupa on 25.12.2014.
 */
public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private static final String LOG_TAG = null;
    private Activity v;
    private Uri url;
    private Bitmap finalBmp;
    private ImageView iv;

    public ImageDownloader() {
    }

    public ImageDownloader(Activity v, String url, ImageView iv) {
        this.v = v;
        this.url = Uri.parse(url);
        this.iv = iv;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            finalBmp = putBitmapInDiskCache(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //sets bitmap returned by doInBackground
    @Override
    protected void onPostExecute(Bitmap result) {
        v.runOnUiThread(new Runnable() {
            public void run() {
                iv.setImageBitmap(finalBmp);
            }
        });
    }

    public Bitmap DownloadImageFromPath(String path) {

        InputStream in = null;
        Bitmap bmp = null;
        int responseCode = -1;
        try {
            URL url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.connect();
            //download info for catch
         /*   Map<String, List<String>> map = con.getHeaderFields();
            CacheControl = map.get("Cache-Control").toString();
            ExpiresDate = map.get("Expires").toString();
            LastModifiedDate = map.get("Last-Modified").toString();
            System.out.println("CacheControl: " + CacheControl + "__" + "__ExpiresDate: " + ExpiresDate + "__LastModifiedDate: " + LastModifiedDate);
            System.out.println(v.getCacheDir());
           */

            responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
                finalBmp = bmp;
            }

        } catch (Exception ex) {
            Toast toast = Toast.makeText(v.getBaseContext(), "Connection failed...", Toast.LENGTH_LONG);
            toast.show();
        }
        return finalBmp;
    }

    /**
     * Write bitmap associated with a url to disk cache
     */
    private Bitmap putBitmapInDiskCache(Uri url) {
        File sdCard = Environment.getExternalStorageDirectory();
        File cacheDir = new File(sdCard + "/Android/data/" + v.getPackageName(), "thumbnails");
        File cacheFile = new File(cacheDir, "" + url.hashCode());
        FileInputStream fis = null;
        ArrayList<String> fileNames = getFileNames(GetFiles(cacheDir.toString()));
        System.out.println(Arrays.toString(fileNames.toArray()));
        System.out.println(cacheDir.toString() + url.hashCode());
        if (fileNames.size() == 0) {
            try {
                cacheDir.mkdir();
                cacheFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(cacheFile);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileNames.contains(String.valueOf(url.hashCode())) == false) {
            Bitmap avatar = DownloadImageFromPath(url.toString());
            System.out.println("FromInternet");
            try {
                cacheDir.mkdir();
                cacheFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(cacheFile);
                avatar.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error when saving image to cache. ", e);
            }
        }
        try {
            fis = new FileInputStream(cacheFile);
        } catch (FileNotFoundException e) {
            Toast toast = Toast.makeText(v.getBaseContext(), "Check connection to the Internet!!!", Toast.LENGTH_LONG);
            toast.show();
            // e.printStackTrace();
        }
        Bitmap bmp = BitmapFactory.decodeStream(fis);
        return bmp;
    }
    //смотрим имена файла в каталоге кеша
    public File[] GetFiles(String DirectoryPath) {
        File f = new File(DirectoryPath);
        f.mkdirs();
        File[] file = f.listFiles();
        return file;
    }

    public ArrayList<String> getFileNames(File[] file) {
        ArrayList<String> arrayFiles = new ArrayList<String>();
        for (int i = 0; i < file.length; i++) {
            arrayFiles.add(file[i].getName());
        }
        return arrayFiles;
    }
}

