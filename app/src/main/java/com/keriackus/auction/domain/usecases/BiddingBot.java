package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by keriackus on 4/2/2016.
 */
public class BiddingBot extends UseCaseImplementation {


    Random random;

    public BiddingBot(Context applicationContext, PresenterInterface presenter) {
        super(applicationContext, presenter);
        this.random = new Random();

    }


    private void bidOnItem(Item item) {
        double randomAmount = item.estimatePrice * generateRandomDouble();
        if (item.otherMaximumBiddingAmount < randomAmount && item.biddingAmount < randomAmount) {
            item.otherMaximumBiddingAmount = randomAmount;
            CacheManager.getInstance(applicationContext).createOrUpdate(item, this);
        }
    }

    private double generateRandomDouble() {
        double minX = 0.5d;
        double maxX = 2.00d;


        double finalX = random.nextDouble() * (maxX - minX) + minX;
        return finalX;
    }

    @Override
    public void run() {
        CacheManager.getInstance(applicationContext).queryForAll(Item.class, this);
    }

    @Override
    public void onCreateOrUpdateRequestSuccess(Dao.CreateOrUpdateStatus status, Entity entity) {
    }

    private final long HALF_MINUTE_IN_MILLIS = 30000L;

    @Override
    public void onQueryRequestSuccess(List<Entity> entities) {
        Item item = null;
        for (Entity entity : entities) {
            if (random.nextBoolean() && random.nextBoolean()) {
                item = (Item) entity;
                if (item.startTime > Calendar.getInstance().getTimeInMillis() + HALF_MINUTE_IN_MILLIS) {
                    bidOnItem(item);
                }
            }
        }
        presenter.onSuccess();
    }
}
