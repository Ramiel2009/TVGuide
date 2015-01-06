package ua.viasat.tvguide;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

public class BackgroundWorker extends AsyncTask<Void, Void, Void> {
    FragmentManager fm;
    private View contentFragment;
    private String id  = null;
    ProgressBar pb;

    public BackgroundWorker(View contentFragment,  FragmentManager fm) {
        this.contentFragment = contentFragment;
        this.fm = fm;
        pb = (ProgressBar)contentFragment.findViewById(R.id.pb);
    }

    @Override
    public void onPreExecute() {
        if (MainActivity.launch==true){
        pb.setVisibility(View.VISIBLE);
        MainActivity.actionBar.hide();}
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
        pb.setVisibility(View.GONE);
        Fragment objFragment = new Content_fragment();
        fm.beginTransaction().replace(R.id.container, objFragment).addToBackStack("Content").commit();
        MainActivity.actionBar.show();
        MainActivity.launch=false;
    }
}