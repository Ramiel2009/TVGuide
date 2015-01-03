package ua.viasat.tvguide;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Channels_fragment extends Fragment {
    View rootView;
    private List<ListViewItem> mItems;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.channels_layout, container, false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.listView);

        String[] names = {"TV1000", "TV1000 Русское Кино", "TV1000 Action", "TV1000 Premium HD",
                "TV1000 Megahit HD", "Viasat History", "Viasat Explore", "Viasat Nature", "Viasat History",
                "Viasat Nature / History HD", "Viasat Sport", "Discovery Channel", "Discovery Showacase HD",
                "Перший Національний", "Інтер", "1+1", "ICTV", "Новий Канал"};


        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();
        int a = names.length;

        for (int i = 0; i < a; i++) {
            mItems.add(new ListViewItem(resources.getDrawable(R.drawable.ic_drawer), Parser.channel.get(i)));
        }
        lvMain.setAdapter(new ListViewAdapter(getActivity(), mItems));
        return rootView;
    }
}
