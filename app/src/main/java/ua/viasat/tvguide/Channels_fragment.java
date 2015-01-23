package ua.viasat.tvguide;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Channels_fragment extends Fragment implements AdapterView.OnItemClickListener {
    View rootView;
    private List<ListViewItem> mItems;
    static int selectedChannel;
    static String[] names = {"TV1000", "TV1000 Русское Кино", "TV1000 Action", "TV1000 Premium HD",
            "TV1000 Megahit HD", "Comedy HD", "Viasat History", "Viasat Explore", "Viasat Nature", "Viasat History",
            "Viasat Nature-History HD", "Viasat Sport", "Discovery Channel", "Discovery Showacase HD",
            "Перший Національний", "Інтер", "1+1", "ICTV", "Новий Канал"};

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.channels_layout, container, false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.listView);
        lvMain.setOnItemClickListener(this);

        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();
        int a = names.length;

        for (int i = 0; i < a; i++) {
            Channels.setChannelsLogo();
            mItems.add(new ListViewItem(resources.getDrawable(Channels.channelsLogo.get(i)), names[i]));
        }
        lvMain.setAdapter(new ListViewAdapterChannels(getActivity(), mItems));
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedChannel = position;
        Intent intent = new Intent (this.getActivity(), ChannelScheduleActivity.class);
        startActivity(intent);
    }
}
