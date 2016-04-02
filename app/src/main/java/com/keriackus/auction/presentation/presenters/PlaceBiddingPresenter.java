package com.keriackus.auction.presentation.presenters;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.domain.usecases.PlaceBiddingUseCase;
import com.keriackus.auction.presentation.views.activities.BaseActivity;
import com.keriackus.auction.presentation.views.activities.DisplayItemActivity;

/**
 * Created by keriackus on 4/2/2016.
 */
public class PlaceBiddingPresenter extends PresenterImplementation {

    public PlaceBiddingPresenter(BaseActivity view) {
        super(view);
    }

    @Override
    public void onSuccess(Object... params) {
        activity.onUpdate(params);
    }

    public void placeBidding(Item item) {
        DisplayItemActivity displayItemActivity = (DisplayItemActivity) activity;

        double biddingAmount = 0d;
        try {
            biddingAmount = Double.parseDouble(displayItemActivity.getBiddingAmount());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (biddingAmount <= 0) {
            displayItemActivity.onError(R.string.error_invalid_bidding_amount);
        } else {
            item.biddingAmount = biddingAmount;
            new PlaceBiddingUseCase(activity.getApplicationContext(), this, item).run();
        }
    }

    @Override
    public void onError(Object... params) {
        activity.onError(0);
    }
}
