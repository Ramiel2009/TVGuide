package ua.viasat.tvguide;

import android.graphics.drawable.Drawable;

/**
 * Created by Maxim on 03.01.2015.
 */
public class ListViewItem {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String time;
    public final String title;
    public final String nTime;
    public final String nTitle;
    // the text for the ListView item title

    public ListViewItem(Drawable icon, String time, String title, String nTime, String nTitle) {
        this.icon = icon;
        this.time = time;
        this.title = title;
        this.nTime = nTime;
        this.nTitle = nTitle;
    }
}
