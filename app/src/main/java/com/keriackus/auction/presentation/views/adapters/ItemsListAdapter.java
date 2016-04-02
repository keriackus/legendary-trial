package com.keriackus.auction.presentation.views.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.keriackus.auction.data.entities.Item;

import java.util.List;

/**
 * Created by keriackus on 4/2/2016.
 */
public class ItemsListAdapter extends ArrayAdapter<Item> {
    List<Item> items;
    public ItemsListAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.items = items;
    }

}
