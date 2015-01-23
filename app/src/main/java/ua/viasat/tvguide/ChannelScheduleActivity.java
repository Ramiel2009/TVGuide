package ua.viasat.tvguide;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChannelScheduleActivity extends ActionBarActivity {
    public static ActionBar actionBar;
    ProgressDialog pd;
    String Url = Channels.setChannelUrl();
    public static String [] sd = new String[14];
    private List <ListViewSchedule> mItems;
    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_schedule);
        lvMain = (ListView)findViewById(R.id.listViewSchedule);
        restoreActionBar();

        Context request = new Context();
            request.execute();

        mItems = new ArrayList<ListViewSchedule>();
    }

    public void restoreActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#225180")));
        actionBar.setTitle(Channels_fragment.names[Channels_fragment.selectedChannel]);
    }

    public void Parse () throws IOException{
        Document doc = Jsoup.connect(Url).get();
        Elements item = doc.select(".time-block");
        for(Element e: item ) {
            mItems.add(new ListViewSchedule ("\n"+e.getElementsByClass("ch-time").text()+"   ",
            "\n"+e.getElementsByClass("ch-title").text()));
        }
    }
    private class Context extends AsyncTask<Void, Void, Void> {

        @Override
        public void onPreExecute() {
            pd = new ProgressDialog(ChannelScheduleActivity.this);
            pd.setTitle("Getting Data");
            pd.setMessage("Loading...");
            pd.show();
        }
        @Override
        public Void doInBackground(Void... params) {
            try {
                Parse();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onPostExecute(Void result) {
            lvMain.setAdapter(new ListViewAdapterSchedule(getBaseContext(), mItems));
           pd.dismiss();
        }
    }
}