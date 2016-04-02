package com.keriackus.auction.presentation.presenters;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.domain.usecases.AddItemUseCase;
import com.keriackus.auction.presentation.views.activities.AddItemActivity;
import com.keriackus.auction.presentation.views.activities.BaseActivity;

/**
 * Created by keriackus on 4/2/2016.
 */
public class AddItemPresenter extends PresenterImplementation {
    public AddItemPresenter(BaseActivity view) {
        super(view);
    }

    @Override
    public void onSuccess(Object... params) {
        activity.onUpdate();
    }

    @Override
    public void onError(Object... params) {
        activity.onError(0);
    }

    public void addItem() {

        AddItemActivity addItemActivity = (AddItemActivity) activity;

        addItemActivity.resetErrors();
        Item item = new Item();
        item.name = addItemActivity.getItemTitle();
        item.category = addItemActivity.getItemCategory();
        item.description = addItemActivity.getItemDescription();

        try {
            Double price = Double.parseDouble(addItemActivity.getItemPrice());
            item.estimatePrice = price;
        } catch (NumberFormatException e) {
            addItemActivity.onError(R.string.missing_item_price);
            e.printStackTrace();
        }

        boolean valid = true;
        if (item.name == null || item.name.isEmpty()) {
            addItemActivity.onError(R.string.missing_item_name);
            valid = false;
        }
        if (item.estimatePrice <= 0) {
            addItemActivity.onError(R.string.missing_item_name);
            valid = false;
        }
        if (item.description == null || item.description.isEmpty()) {
            addItemActivity.onError(R.string.missing_item_description);
            valid = false;
        }
        if (item.category == null || item.category.isEmpty()) {
            addItemActivity.onError(R.string.missing_item_category);
            valid = false;
        }

        if (valid) {
            new AddItemUseCase(addItemActivity.getApplicationContext(), this, item).run();
        }
    }


}
