package ua.viasat.tvguide;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Maxim on 03.01.2015.
 */
public class ListViewAdapterChannels extends ArrayAdapter<ListViewItem> {

    public ListViewAdapterChannels(Context context, List<ListViewItem> items) {
        super(context, R.layout.channels_list, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.channels_list, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvTimeChannels = (TextView)convertView.findViewById(R.id.tvTimeChannels);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

            viewHolder.tvTimeNext = (TextView)convertView.findViewById(R.id.tvTimeNext);
            viewHolder.tvTitleNext = (TextView) convertView.findViewById(R.id.tvTitleNext);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        ListViewItem item = getItem(position);
        //Live events
        viewHolder.ivIcon.setImageDrawable(item.icon);
        viewHolder.tvTimeChannels.setText("  " + item.time);
        viewHolder.tvTimeChannels.setTypeface(null, Typeface.BOLD);
        viewHolder.tvTitle.setText("  : " + item.title);
        viewHolder.tvTitle.setTypeface(null, Typeface.BOLD);
        //next events

        viewHolder.tvTimeNext.setText("  " + item.nTime);
        viewHolder.tvTimeNext.setTypeface(null, Typeface.NORMAL);
        viewHolder.tvTitleNext.setText("  : " + item.nTitle);
        viewHolder.tvTitleNext.setTypeface(null, Typeface.NORMAL);
        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     */
    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTimeChannels;
        TextView tvTitle;
        TextView tvTimeNext;
        TextView tvTitleNext;
    }
}