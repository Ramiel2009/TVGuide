package ua.viasat.tvguide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nshkarupa on 30.12.2014.
 */
public class ATgetItemInfo extends AsyncTask<String, Void, Bitmap> {

        private static final String LOG_TAG = null;
        private Activity v;
        private String id;
        private ArrayList<String> itemInfo;

        private TextView  tv;
        private ImageView iv;



        public ATgetItemInfo(Activity v, String id,  TextView  tv, ImageView iv) {
            this.v = v;
            this.id = id;
            this.tv=tv;
            this.iv = iv;
        }

        public ArrayList getInfo(){
        return itemInfo;
    }

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                itemInfo = Parser.getItemInfo(id);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //sets bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            ImageDownloader imDownloader = new ImageDownloader(v, itemInfo.get(1), iv);
            imDownloader.execute();
            v.runOnUiThread(new Runnable() {
                public void run() {

                    TextView  tv = (TextView) v.findViewById(R.id.TitleTextView1);
                    tv.setText(Html.fromHtml(itemInfo.get(0).toString()));
                }
            });

        }
}
