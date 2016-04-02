package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.sql.SQLException;

/**
 * Created by keriackus on 4/2/2016.
 */
public class GetBiddingItemsUseCase extends UseCaseImplementation {

    public GetBiddingItemsUseCase(Context applicationContext, PresenterInterface presenter) {
        super(applicationContext, presenter);
    }

    @Override
    public void run() {
        try {
            presenter.onSuccess(CacheManager.getInstance(applicationContext).queryForEqual(Item.class, Item.InBidFieldName, true));
        } catch (SQLException e) {
            e.printStackTrace();
            presenter.onError();
        }
    }
}
