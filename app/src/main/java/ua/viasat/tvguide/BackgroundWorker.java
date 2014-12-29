package ua.viasat.tvguide;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class BackgroundWorker extends AsyncTask<Void, Void, Void> {
    private ProgressDialog pd;
    private View contentFragment;
    private String id  = null;

    public BackgroundWorker(View activity) {
        contentFragment = activity;
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

        Toast.makeText(contentFragment.getContext(), "TEST111", Toast.LENGTH_LONG).show();
        // TVCreator();
    }
}