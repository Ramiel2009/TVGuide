package ua.viasat.tvguide;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mmaloshtan on 23.12.2014.
 */
public class Content_fragment extends Fragment implements View.OnClickListener {
    View rootView;
    static boolean  flagRefreshed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_layout, container, false);

        Button btn = (Button) rootView.findViewById(R.id.btnRefresh);
        btn.setOnClickListener(this);


            if(flagRefreshed==false){
                BackgroundWorker dRequest = new BackgroundWorker(rootView);
                dRequest.execute();
                flagRefreshed=true;
            }
            TVCreator();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRefresh:
                flagRefreshed=false;
                BackgroundWorker at = new BackgroundWorker(rootView);
                at.execute();
                break;
        }
    }


    public void TVCreator() {
System.out.println("tvCreator from 1 to "+Parser.title.size());
        int a;
        for (a = 1; a < Parser.title.size(); a++) {
            RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.rl);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView tvd = new TextView(rootView.getContext());
            final int b = a;
            tvd.setId(b);
            tvd.setOnClickListener(this);
            params.addRule(RelativeLayout.BELOW, a - 1);
            rl.addView(tvd, params);


            tvd.setText("");
            tvd.setText(Html.fromHtml("<b>" + a + ". " + Parser.title.get(a - 1) + "</b>" + "<br>"
                    + Parser.time.get(a - 1) + "   " + "<font color=\"grey\">"
                    + Parser.channel.get(a - 1) + "</font>" + "<br>"));
        }
        System.out.println("tvCreator from 1 to "+a+"finished");

    }
}




