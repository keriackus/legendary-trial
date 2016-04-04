package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.util.List;

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


    @Override
    public void onCreateOrUpdateRequestSuccess(Dao.CreateOrUpdateStatus status, Entity entity) {

    }

    @Override
    public void onQueryRequestSuccess(List<Entity> entities) {
        Item item = null;
        for (Entity entity : entities)
        {
            item = (Item) entity;
            if(item.isOutOfDate())
            {
                item.inBid = false;
                CacheManager.getInstance(applicationContext).createOrUpdate(item, this);
            }
        }
        presenter.onSuccess(entities);

    }
}
