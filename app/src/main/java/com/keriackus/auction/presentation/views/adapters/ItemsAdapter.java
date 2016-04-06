package com.keriackus.auction.presentation.views.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Item;

import java.util.List;

/**
 * Created by keriackus on 4/6/2016.
 */
public class ItemsAdapter extends BaseAdapter {

    List<Item> items;
    Context context;

    public ItemsAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_row_layout, null);
        }
        TextView mainText = (TextView) convertView.findViewById(R.id.list_view_item_main_text);
        TextView secondaryText = (TextView) convertView.findViewById(R.id.list_view_item_secondary_text);
        TextView mostRightText = (TextView) convertView.findViewById(R.id.right_info_text);

        Item item = items.get(position);
        mainText.setText(item.name);
        mostRightText.setText(String.valueOf(item.estimatePrice) + "$");

        if (item.isWon()) {
            secondaryText.setText(R.string.won);
            secondaryText.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
        } else if (item.isOutOfDate()) {
            secondaryText.setText(R.string.sold);
            secondaryText.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        } else if (item.inBid) {
            secondaryText.setText(R.string.in_bidding);
            secondaryText.setTextColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
        } else {
            secondaryText.setText(R.string.available);
            secondaryText.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
        }
        return convertView;
    }
}
