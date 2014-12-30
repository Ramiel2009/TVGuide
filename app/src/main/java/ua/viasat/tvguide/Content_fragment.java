package ua.viasat.tvguide;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.IOException;


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
        FragmentManager fm = Content_fragment.this.getActivity().getSupportFragmentManager();

            if(flagRefreshed==false){
                BackgroundWorker dRequest = new BackgroundWorker(rootView, fm);
                dRequest.execute();
                setFlagRefreshed(true);

        }
            swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
            swipeLayout.setOnRefreshListener(this);
            return rootView;
    }
    public static void setFlagRefreshed(boolean b){
        flagRefreshed = b;
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
       GradientDrawable gd = new GradientDrawable();
       gd.setColor(Color.WHITE); // Changes this drawbale to use a single color instead of a gradient
       gd.setCornerRadius(10);
       gd.setStroke(1, 0xFF000000);
       System.out.println("tvCreator from 1 to " + Parser.title.size());
        if ( flagRefreshing == false ){
           for (a = 1; a < Parser.title.size(); a++) {

               RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.rl);
               RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                       ViewGroup.LayoutParams.MATCH_PARENT,
                       ViewGroup.LayoutParams.WRAP_CONTENT);

             //  RelativeLayout.LayoutParams lrelLayoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
               TextView tvd = new TextView(rootView.getContext());
               tvd.setBackgroundDrawable(gd);
               tvd.setOnClickListener(this);
               final int b = a;
               tvd.setId(b);
               tvd.setPadding(5, 0, 0, 0);
               params.addRule(RelativeLayout.BELOW, a - 1);
               rl.addView(tvd, params);
               tvd.setText("");
               tvd.setText(Html.fromHtml("<b>" + a + ". " + Parser.title.get(a - 1) + "</b>" + "<br>"
                       + Parser.time.get(a - 1) + "   " + "<font color=\"grey\">"
                       + Parser.channel.get(a - 1) + "</font>" + "<br>"));
           }
           System.out.println("tvCreator from 1 to " + a + " finished");
       }
   }

    @Override
    public void onClick(View v) {
        System.out.println(Parser.id.get(v.getId()));
        Fragment title= null;
        title = new TitleFragment(Parser.id.get(v.getId()-1));
        FragmentManager fm = Content_fragment.this.getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, title).addToBackStack("Content").commit();
    }
}


