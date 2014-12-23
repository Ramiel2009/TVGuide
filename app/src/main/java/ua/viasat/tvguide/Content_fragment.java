package ua.viasat.tvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mmaloshtan on 23.12.2014.
 */
public class Content_fragment extends Fragment implements View.OnClickListener{
    View rootView;

    TextView tv;



//CREATING VIEW
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_layout, container, false);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRefresh:


        }
    }

}
