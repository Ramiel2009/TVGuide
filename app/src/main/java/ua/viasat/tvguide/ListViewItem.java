package ua.viasat.tvguide;

import android.graphics.drawable.Drawable;

/**
 * Created by Maxim on 03.01.2015.
 */
public class ListViewItem {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the ListView item title

    public ListViewItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }
}
