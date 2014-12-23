package ua.viasat.tvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Grid_fragment extends Fragment {

    TextView textView1;
    View rootView;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.grid_layout, container, false);
        return rootView;
    }

}
