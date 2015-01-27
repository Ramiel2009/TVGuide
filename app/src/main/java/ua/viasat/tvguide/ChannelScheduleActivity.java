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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChannelScheduleActivity extends ActionBarActivity {
    public static ActionBar actionBar;
    ProgressDialog pd;
    String Url = Channels.setChannelUrl();
    private List <ListViewSchedule> mItems;
    ListView lvMain;
    String nextEvTime = "00:00";
    String pervEvTime = "00:00";
    int flag;
    boolean check =false;
    public static int mark;

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
        actionBar.setTitle(Channels.names[Channels_fragment.selectedChannel]);
    }

    public void Parse () throws IOException{
        Document doc = Jsoup.connect(Url).get();
        Elements item = doc.select(".time-block");
        int i=0;
        for(Element e: item ) {
            i++;
            nextEvTime = e.getElementsByClass("ch-time").text(); //perv Event time
            getCurrentEvent();
            pervEvTime = e.getElementsByClass("ch-time").text(); //next Event time

                mItems.add(new ListViewSchedule (e.getElementsByClass("ch-time").text(),
                        e.getElementsByClass("ch-title").text()));//adding text to collection
            if (check == true){
                mark=i-1;
                System.out.println(mark+" Live!");
                check=false;
            }
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

    public void getCurrentEvent(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date nextDate = sdf.parse(nextEvTime); //event time
            Date curDate = sdf.parse(sdf.format(new Date())); //current time
            Date pervDate = sdf.parse(pervEvTime);
            flag = nextDate.compareTo(curDate);
            System.out.println(flag);

            if (pervDate.compareTo(curDate) ==-1 && curDate.compareTo(nextDate)==-1){
                System.out.println("Current Date: "+curDate+" PervDate: "+pervEvTime+" nextDate: "+nextEvTime);
                check=true;
            }
            // Outputs -1 as date1 is before date2
            // Outputs 1 as date1 is after date2
            // Outputs 0 as the dates are now equal
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}