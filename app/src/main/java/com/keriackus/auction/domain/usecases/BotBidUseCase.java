package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.util.Random;

/**
 * Created by keriackus on 4/2/2016.
 */
public class BotBidUseCase extends UseCaseImplementation {
    Item item;

    public BotBidUseCase(Context applicationContext, PresenterInterface presenter, Item item) {
        super(applicationContext, presenter);
        this.item = item;
    }


    @Override
    public void run() {
        if (item.inBid) {
            double BotBiddingAmount = item.estimatePrice * generateRandomDouble();
            presenter.onSuccess(BotBiddingAmount);
        }
    }

    private double generateRandomDouble() {
        double minX = 0.9d;
        double maxX = 2.00d;

        Random rand = new Random();

        double finalX = rand.nextDouble() * (maxX - minX) + minX;
        return finalX;
    }
}
