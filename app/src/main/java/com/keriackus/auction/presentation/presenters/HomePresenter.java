package com.keriackus.auction.presentation.presenters;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.domain.usecases.GetBiddingItemsUseCase;
import com.keriackus.auction.domain.usecases.GetItemsUseCase;
import com.keriackus.auction.domain.usecases.GetWonItemsUseCase;
import com.keriackus.auction.presentation.views.activities.BaseActivity;
import com.keriackus.auction.presentation.views.activities.DisplayItemActivity;
import com.keriackus.auction.presentation.views.activities.MainActivity;

/**
 * Created by keriackus on 4/2/2016.
 */
public class HomePresenter extends PresenterImplementation implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ListingType listingType;

    private enum ListingType {ALL, WON, BIDDING}

    ;


    public HomePresenter(BaseActivity view) {
        super(view);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        listingType = ListingType.values()[position];
        reload();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item chosenItem = (Item) parent.getItemAtPosition(position);
        Intent i = new Intent(activity, DisplayItemActivity.class);
        i.putExtra(DisplayItemActivity.ITEM_INTENT_EXTRA_KEY, chosenItem);
        activity.startActivityForResult(i, MainActivity.DISPLAY_ITEM_REQUEST_CODE);
    }

    @Override
    public void onSuccess(Object... params) {
        activity.onUpdate(params);
    }

    @Override
    public void onError(Object... params) {
        activity.onError(0);
    }

    private void loadAllItems() {
        new GetItemsUseCase(activity.getApplicationContext(), this).run();
    }

    private void loadBiddingItems() {
        new GetBiddingItemsUseCase(activity.getApplicationContext(), this).run();
    }

    private void loadWonItems() {
        new GetWonItemsUseCase(activity.getApplicationContext(), this).run();
    }

    public void reload() {
        switch (listingType) {
            case ALL:
                loadAllItems();
                break;
            case WON:
                loadWonItems();
                break;
            case BIDDING:
                loadBiddingItems();
                break;
        }
    }
}
