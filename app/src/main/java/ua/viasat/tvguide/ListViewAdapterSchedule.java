package ua.viasat.tvguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mmaloshtan on 23.01.2015.
 */

    public class ListViewAdapterSchedule extends ArrayAdapter<ListViewSchedule> {
        public ListViewAdapterSchedule(Context context, List<ListViewSchedule> items) {
            super(context, R.layout.schedule_list, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                // inflate the GridView item layout
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.schedule_list, parent, false);

                // initialize the view holder
                viewHolder = new ViewHolder();
                viewHolder.tvTimeS = (TextView) convertView.findViewById(R.id.tvTimeS);
                viewHolder.tvNameS = (TextView) convertView.findViewById(R.id.tvNameS);

                convertView.setTag(viewHolder);
            } else {
                // recycle the already inflated view
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // update the item view
            ListViewSchedule item = getItem(position);
                System.out.println(position);
           // if(position==ChannelScheduleActivity.mark) {
                viewHolder.tvTimeS.setText("\n"+ item.time+"   ");
                viewHolder.tvNameS.setText("\n"+item.name);
           /* }
            else{
            viewHolder.tvTimeS.setText("\n"+ item.time+"   ");
            viewHolder.tvNameS.setText("\n NOW"+item.name);
            }*/
            return convertView;
        }

        /**
         * The view holder design pattern prevents using findViewById()
         * repeatedly in the getView() method of the adapter.
         */
        private static class ViewHolder {
            TextView tvTimeS;
            TextView tvNameS;
        }
    }