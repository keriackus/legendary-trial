package com.keriackus.auction.presentation.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.PlaceBiddingPresenter;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DisplayItemActivity extends BaseActivity {


    public static final String ITEM_INTENT_EXTRA_KEY = "item_id";
    private static final int BID_ACTIVITY_REQUEST_CODE = 1;

    PlaceBiddingPresenter presenter;
    EditText biddingAmountEditText;
    EditText startInEditText;
    Item auctionedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        setupToolbar(true, R.string.title_activity_display_item);
        presenter = new PlaceBiddingPresenter(DisplayItemActivity.this);
        auctionedItem = (com.keriackus.auction.data.entities.Item) getIntent().getSerializableExtra(ITEM_INTENT_EXTRA_KEY);

        ((EditText) findViewById(R.id.item_category_edit_text)).setText(auctionedItem.category);
        ((EditText) findViewById(R.id.item_description_edit_text)).setText(auctionedItem.description);
        ((EditText) findViewById(R.id.item_title_edit_text)).setText(auctionedItem.name);
        ((EditText) findViewById(R.id.item_title_price_text)).setText(String.valueOf(auctionedItem.estimatePrice));

        biddingAmountEditText = (EditText) findViewById(R.id.bid_amount_edit_text);
        startInEditText = (EditText) findViewById(R.id.start_in_edit_text);

        if (auctionedItem.won || auctionedItem.inBid) {
            biddingAmountEditText.setText(String.valueOf(auctionedItem.biddingAmount));
            biddingAmountEditText.setEnabled(false);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_submit_bid).setVisible(!auctionedItem.inBid && !auctionedItem.won);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onUpdate(Object... params) {
        super.onUpdate(params);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStartInText();
    }

    private void setStartInText() {
        long afterInMillis = auctionedItem.startTime - Calendar.getInstance().getTimeInMillis();

        if (afterInMillis > 0) {
            String startsAfter = String.format("%02d Hours, %02d Minutes",
                    TimeUnit.MILLISECONDS.toHours(afterInMillis),
                    TimeUnit.MILLISECONDS.toMinutes(afterInMillis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(afterInMillis)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(afterInMillis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(afterInMillis)));

            startInEditText.setText(startsAfter);
        } else {
            startInEditText.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setResult(resultCode);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display_item, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit_bid:
                presenter.placeBidding(auctionedItem);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onError(int error) {
        switch (error) {
            case R.string.error_invalid_bidding_amount:
                biddingAmountEditText.setError(getString(error));
                break;
            default:
                Snackbar.make(biddingAmountEditText, R.string.error_unable_bid_on_item, Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    public String getBiddingAmount() {
        return biddingAmountEditText.getText().toString();
    }
}
