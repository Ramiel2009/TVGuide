package ua.viasat.tvguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Content_fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

    View rootView;
    static boolean  flagRefreshed;
    static boolean  flagRefreshing;
    private SwipeRefreshLayout swipeLayout;
    public int a = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_layout, container, false);
        TVCreator();
        ScrollView sw = (ScrollView) rootView.findViewById(R.id.scrollView1);
        System.out.println(sw.getMaxScrollAmount());


            if(flagRefreshed==false){
                BackgroundWorker dRequest = new BackgroundWorker(rootView);
                dRequest.execute();
                flagRefreshed=true;
        }
            swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
            swipeLayout.setOnRefreshListener(this);
            return rootView;
    }

    @Override
    public void onRefresh() {
        TVCreator();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 1000);
    }

   public void TVCreator() {

       System.out.println("tvCreator from 1 to " + Parser.title.size());
        if ( flagRefreshing == false ){
           for (a = 1; a < Parser.title.size(); a++) {

               RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.rl);
               RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                       ViewGroup.LayoutParams.WRAP_CONTENT,
                       ViewGroup.LayoutParams.WRAP_CONTENT);



               TextView tvd = new TextView(rootView.getContext());
               tvd.setOnClickListener(this);
               final int b = a;
               tvd.setId(b);
               params.addRule(RelativeLayout.BELOW, a - 1);
               rl.addView(tvd, params);
               tvd.setText("");
               tvd.setText(Html.fromHtml("<b>" + a + ". " + Parser.title.get(a - 1) + "</b>" + "<br>"
                       + Parser.time.get(a - 1) + "   " + "<font color=\"grey\">"
                       + Parser.channel.get(a - 1) + "</font>" + "<br>"));
            flagRefreshing = true;
           }
           System.out.println("tvCreator from 1 to " + a + " finished");
       }
   }

    @Override
    public void onClick(View v) {
       System.out.println(v.getId());
        TitleFragment title= new TitleFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.scrollView1, title)
                .addToBackStack(null)
                .commit();
    }
}


