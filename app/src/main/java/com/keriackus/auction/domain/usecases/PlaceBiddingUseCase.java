package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.sql.SQLException;

/**
 * Created by keriackus on 4/2/2016.
 */
public class PlaceBiddingUseCase extends UseCaseImplementation {

    Item item;
    public PlaceBiddingUseCase(Context applicationContext, PresenterInterface presenter, Item item) {
        super(applicationContext, presenter);
        this.item = item;
    }

    @Override
    public void run() {


        try {
            item.inBid = true;
            CacheManager.getInstance(applicationContext).createOrUpdate(item);
            presenter.onSuccess(item);
        } catch (SQLException e) {
            item.inBid = false;
            presenter.onError(item);
            e.printStackTrace();
        }
    }
}
