package ua.viasat.tvguide;


import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class TitleFragment extends Fragment {
    private String id = null;

    private  ArrayList<String> itemInfo;

    TitleFragment() {
    }

    TitleFragment(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_title, container, false);
        TextView tv  = new TextView(rootView.getContext());

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            LinearLayout lroot = (LinearLayout) rootView.findViewById(R.id.fragmentLinearLayout);
            ImageView iv = new ImageView(rootView.getContext());
            try {
                itemInfo = Parser.getItemInfo(id);
                ImageDownloader imdownloader = new ImageDownloader(this.getActivity(), Parser.getItemInfo(id).get(1).toString(), iv);
                imdownloader.execute();

            } catch (IOException e) {
                e.printStackTrace();
            }
            lroot.setOrientation(LinearLayout.VERTICAL);
            lroot.addView(iv);
            iv.getLayoutParams().width=ActionBar.LayoutParams.WRAP_CONTENT;

            tv.setText(Html.fromHtml("<p align=\"right\">"+itemInfo.get(0)+"</p>"));

            tv.setBackgroundColor(Color.TRANSPARENT);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);


                    lroot.addView(tv);
        }
        return rootView;
    }
}