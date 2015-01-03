package ua.viasat.tvguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Channels_fragment extends Fragment {
    View rootView;
    String[] names = { "TV1000", "TV1000 Русское Кино", "TV1000 Action", "TV1000 Premium HD",
            "TV1000 Megahit HD", "Viasat History", "Viasat Explore", "Viasat Nature", "Viasat History",
            "Viasat Nature / History HD", "Viasat Sport", "Discovery Channel", "Discovery Showacase HD",
            "Перший Національний", "Інтер", "1+1", "ICTV", "Новий Канал"};
    Button btn;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.channels_layout, container, false);

        ListView lvMain = (ListView) rootView.findViewById(R.id.listView);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_list_item_1, names);


        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
        return rootView;
    }
}
