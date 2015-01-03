package ua.viasat.tvguide;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class TitleFragment extends Fragment {
    private String id = null;
    private ArrayList<String> itemInfo;

    public TitleFragment() {
    }

    public TitleFragment(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Content_fragment.setFlagRefreshed(true);
        View rootView = inflater.inflate(R.layout.fragment_title, container, false);
        TextView tvTitle = (TextView)rootView.findViewById(R.id.titleTv);
        tvTitle.setText(""+Parser.title.get(Content_fragment.selected));
        ImageView iv = (ImageView) rootView.findViewById(R.id.TitleImageView1);
        TextView tv = (TextView) rootView.findViewById(R.id.TitleTextView1);
        System.out.println("Title: " + Parser.title.get(Content_fragment.selected));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            ATgetItemInfo atItem = new ATgetItemInfo(this.getActivity(), id, tv, iv);
            atItem.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }
}