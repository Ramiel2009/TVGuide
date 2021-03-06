package ua.viasat.tvguide;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private FragmentManager fragmentManager = this.getSupportFragmentManager();
    public static ActionBar actionBar;
    public static boolean launch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Connection: " + NetConnection.checkConnection(this));
        restoreActionBar();
        NavigationDrawerFragment mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = new Content_fragment();
        switch (position) {
            case 0:
                objFragment = new Content_fragment();
                Content_fragment.flagRefreshing = false;

                break;
            case 1:
                objFragment = new Channels_fragment();
                actionBar.setTitle("Channels");
                break;
            case 2:
                objFragment = new SlidingScheduleFragment();
                actionBar.setTitle("Test Menu");
                break;
        }
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.container, objFragment)
                .commit();
    }
    public void restoreActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#225180")));
    }
}