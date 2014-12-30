package ua.viasat.tvguide;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import java.io.IOException;

public class BackgroundWorker extends AsyncTask<Void, Void, Void> {
    private ProgressDialog pd;
    FragmentManager fm;
    private View contentFragment;
    private String id  = null;

    public BackgroundWorker(View contentFragment,  FragmentManager fm) {
        this.contentFragment = contentFragment;
        this.fm = fm;
        pd = new ProgressDialog(contentFragment.getContext());
    }



    @Override
    public void onPreExecute() {
        pd.setTitle("Getting Data");
        pd.setMessage("Loading....");
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Parser.refreshItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute(Void result) {
        pd.dismiss();
        //Toast.makeText(contentFragment.getContext(), "TEST111", Toast.LENGTH_LONG).show();
        Fragment objFragment = new Content_fragment();
        fm.beginTransaction().replace(R.id.container, objFragment).addToBackStack("Content").commit();
        // TVCreator();
    }
}