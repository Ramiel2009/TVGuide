package ua.viasat.tvguide;

import android.graphics.drawable.Drawable;

/**
 * Created by Maxim on 03.01.2015.
 */
public class ListViewItemContent {
    public final Drawable icon;
    public final String title;
    public final String time;
    public final String channel;

    public ListViewItemContent(Drawable icon, String title, String time, String channel) {
        this.icon = icon;
        this.title = title;
        this.time = time;
        this.channel = channel;
    }
}
