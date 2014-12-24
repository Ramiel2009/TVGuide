package ua.viasat.tvguide;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class BackgroundWorker extends AsyncTask<Void, Void, Void> {

    static boolean refreshing = true;
    private ProgressDialog pd;
    private View contentFragment;
    private Content_fragment cf;

    public BackgroundWorker(View activity) {
        contentFragment = activity;
        pd = new ProgressDialog(contentFragment.getContext());
    }


    public BackgroundWorker(Content_fragment cf) {
        this.cf = cf;
        pd = new ProgressDialog(cf.getActivity());
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
        refreshing = false;
        cf.TVCreator();
        Toast.makeText(contentFragment.getContext(), "TEST111", Toast.LENGTH_LONG).show();
    }
}