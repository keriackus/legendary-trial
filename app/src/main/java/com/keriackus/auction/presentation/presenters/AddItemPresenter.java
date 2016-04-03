package com.keriackus.auction.presentation.presenters;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.domain.usecases.AddItemUseCase;
import com.keriackus.auction.presentation.views.activities.AddItemActivity;
import com.keriackus.auction.presentation.views.activities.BaseActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by keriackus on 4/2/2016.
 */
public class AddItemPresenter extends PresenterImplementation implements View.OnClickListener {
    Item item;
    long itemStartTime = 0l;

    public AddItemPresenter(BaseActivity view) {
        super(view);
        item = new Item();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 10);
        itemStartTime = c.getTime().getTime();
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

        item.name = addItemActivity.getItemTitle();
        item.category = addItemActivity.getItemCategory();
        item.description = addItemActivity.getItemDescription();

        item.startTime = itemStartTime;
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

    Date chosenDate;

    @Override
    public void onClick(View v) {
        final View view = v;
        Calendar c = Calendar.getInstance();
        if (v.getId() == R.id.item_start_date_text_view) {
            new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar c = Calendar.getInstance();
                    c.set(year, monthOfYear, dayOfMonth);
                    chosenDate = c.getTime();

                    new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            Calendar c = Calendar.getInstance();
                            c.setTime(chosenDate);
                            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            c.set(Calendar.MINUTE, minute);
                            itemStartTime = c.getTimeInMillis();

                            ((AddItemActivity) activity).setStartTime(c.getTime().toString());
                        }
                    }, 0, 0, true).show();
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

        }
    }
}
