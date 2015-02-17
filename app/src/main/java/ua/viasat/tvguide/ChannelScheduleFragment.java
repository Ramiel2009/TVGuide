package ua.viasat.tvguide;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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

public class ChannelScheduleFragment extends Fragment {


    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_channel_schedule, container, false);
        lvMain = (ListView)rootView.findViewById(R.id.listViewSchedule);
        Context request = new Context();
        request.execute();
        mItems = new ArrayList<ListViewSchedule>();
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        return rootView;
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
                        e.getElementsByClass("ch-title").text()));
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
            pd = new ProgressDialog(getActivity());
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
            lvMain.setAdapter(new ListViewAdapterSchedule(getActivity().getBaseContext(), mItems));
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
                check=true;
            }
            // Outputs -1 as date1 is before date2
            // Outputs 1 as date1 is after date2
            // Outputs 0 as the dates are now equal
        } catch (ParseException e){
            e.printStackTrace();
        }
    }




//sliding tabs
    // Adapter
    class SamplePagerAdapter extends PagerAdapter {


        /**
         * Return the number of pages to display
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * Return true if the value returned from is the same object as the View
         * added to the ViewPager.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        /**
         * Return the title of the item at position. This is important as what
         * this method returns is what is displayed in the SlidingTabLayout.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return "Item " + (position + 1);
        }

        /**
         * Instantiate the View which should be displayed at position. Here we
         * inflate a layout from the apps resources and then change the text
         * view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Inflate a new layout from our resources
            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

            // Retrieve a TextView from the inflated View, and update it's text
            TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(String.valueOf(position + 1));

            // Return the View
            return view;
        }

        /**
         * Destroy the item from the ViewPager. In our case this is simply
         * removing the View.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}


