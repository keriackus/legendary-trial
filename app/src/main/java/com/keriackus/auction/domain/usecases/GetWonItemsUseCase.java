package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by keriackus on 4/2/2016.
 */
public class GetWonItemsUseCase extends UseCaseImplementation {

    public GetWonItemsUseCase(Context applicationContext, PresenterInterface presenter) {
        super(applicationContext, presenter);
    }

    @Override
    public void run() {
        CacheManager.getInstance(applicationContext).queryForEqual(Item.class, Item.InBidFieldName, true, this);
    }

    @Override
    public void onQueryRequestSuccess(List<Entity> entities) {
        Item item = null;
        List<Item> wonItems = new ArrayList<>();
        for (Entity entity : entities) {
            item = (Item) entity;

            if (item.startTime < Calendar.getInstance().getTimeInMillis()) {
                if (item.biddingAmount > item.otherMaximumBiddingAmount) {
                    wonItems.add(item);
                }
            }
        }
        presenter.onSuccess(wonItems);
    }
}
