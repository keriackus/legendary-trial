package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.sql.SQLException;

/**
 * Created by keriackus on 4/2/2016.
 */
public class GetItemsUseCase extends UseCaseImplementation {

    public GetItemsUseCase(Context applicationContext, PresenterInterface presenter) {
        super(applicationContext, presenter);
    }

    @Override
    public void run() {
       CacheManager.getInstance(applicationContext).queryForAll(Item.class, this);
    }


}
