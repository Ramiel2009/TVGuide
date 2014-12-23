package ua.viasat.tvguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by mmaloshtan on 23.12.2014.
 */
public class Channels_fragment extends Fragment implements View.OnClickListener {
    View rootView;

    Button btn;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.channels_layout, container, false);
       // Button btn = (Button)findViewById(R.id.btnRefresh);
        //btn.setOnClickListener(this);
        return rootView;



    }



    @Override
    public void onClick (View v){

    }
}
