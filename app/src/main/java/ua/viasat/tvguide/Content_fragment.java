package ua.viasat.tvguide;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Content_fragment extends Fragment {
    View rootView;
    static boolean  flagRefreshed;
    static boolean  flagRefreshing;
    private List<ListViewItemContent> mItems;
    public static int selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_layout, container, false);
        final ListView lvMain1 = (ListView) rootView.findViewById(R.id.listView1);
        final FragmentManager fm = Content_fragment.this.getActivity().getSupportFragmentManager();

            if(flagRefreshed==false){
                BackgroundWorker dRequest = new BackgroundWorker(rootView, fm);
                dRequest.execute();
                setFlagRefreshed(true);
        }
        mItems = new ArrayList<>();
        Resources resources = getResources();
        for (int a = 0; a < Parser.title.size(); a++) {
            mItems.add(new ListViewItemContent(resources.getDrawable(R.drawable.ic_drawer),
                    Parser.title.get(a), Parser.time.get(a), Parser.channel.get(a)));
        }
        lvMain1.setAdapter(new ListViewAdapterContent(getActivity().getBaseContext(), mItems));


        lvMain1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                System.out.println("Position: "+position+" ID: "+id);
                selected = position;
                Fragment title= null;
                title = new TitleFragment(Parser.id.get(position));
                FragmentManager fm = Content_fragment.this.getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container, title).addToBackStack("Content").commit();
            }
        });


        //ScrollRefreshLayout
        final SwipeRefreshLayout  refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BackgroundWorker dRequest = new BackgroundWorker(rootView, fm);
                        dRequest.execute();
                        Toast.makeText(rootView.getContext(), "Refreshed", Toast.LENGTH_LONG).show();
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        lvMain1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topPosition = (lvMain1 == null || lvMain1.getChildCount() == 0) ? 0 : lvMain1.getChildAt(0).getTop();
                refreshLayout.setEnabled(topPosition >= 0);
            }
        });
        return rootView;
    }
    public static void setFlagRefreshed(boolean b){
        flagRefreshed = b;
    }
}
