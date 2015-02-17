package ua.viasat.tvguide;


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
    public String Url;
    static int selectedChannel;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.channels_layout, container, false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.listView);
        lvMain.setOnItemClickListener(this);

        mItems = new ArrayList<ListViewItem>();
        Resources resources = getResources();
        int a = Channels.names.length;

        for (int i = 0; i < a; i++) {
            Channels.setChannelsLogo();
            mItems.add(new ListViewItem(resources.getDrawable(Channels.channelsLogo.get(i)),
                    ScheduleParser.liveEventsTime[i], ScheduleParser.liveEvents[i],
                    ScheduleParser.nextEventsTime[i], ScheduleParser.nextEvents[i]));
        }
        lvMain.setAdapter(new ListViewAdapterChannels(getActivity(), mItems));
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedChannel = position;
        android.support.v4.app.FragmentManager fm = Channels_fragment.this.getActivity().getSupportFragmentManager();
        Fragment objFragment = new ChannelScheduleFragment();

        fm.popBackStack();
        fm.beginTransaction().replace(R.id.container, objFragment).addToBackStack(null)
                .commit();

    }
}
