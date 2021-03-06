package com.keriackus.auction.presentation.views.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.keriackus.auction.R;
import com.keriackus.auction.presentation.presenters.AddItemPresenter;

public class AddItemActivity extends BaseActivity {

    EditText titleEditText;
    EditText categoryEditText;
    EditText priceEditText;
    EditText descriptionEditText;

    TextView dateTextViewChooser;
    AddItemPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setupToolbar(true, R.string.title_activity_add_item);

        presenter = new AddItemPresenter(AddItemActivity.this);

        titleEditText = (EditText) findViewById(R.id.item_title_edit_text);
        categoryEditText = (EditText) findViewById(R.id.item_category_edit_text);
        priceEditText = (EditText) findViewById(R.id.item_title_price_text);
        descriptionEditText = (EditText) findViewById(R.id.item_description_edit_text);

        dateTextViewChooser = (TextView) findViewById(R.id.item_start_date_text_view);

        dateTextViewChooser.setOnClickListener(presenter);

    }

    public void setStartTime(String startTime){

        dateTextViewChooser.setText(startTime);
    };
    public String getItemTitle() {
        return titleEditText.getText().toString();
    }

    public String getItemCategory() {
        return categoryEditText.getText().toString();
    }

    public String getItemPrice() {
        return priceEditText.getText().toString();
    }

    public String getItemDescription() {
        return descriptionEditText.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_item_done) {
            setResult(RESULT_OK);
            presenter.addItem();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetErrors() {
        titleEditText.setError(null);
        priceEditText.setError(null);
        descriptionEditText.setError(null);
    }

    @Override
    public void onError(int error) {
        switch (error) {
            case R.string.missing_item_name:
                titleEditText.setError(getString(error));
                break;
            case R.string.missing_item_price:
                priceEditText.setError(getString(error));
                break;
            case R.string.missing_item_description:
                descriptionEditText.setError(getString(error));
                break;
            case R.string.missing_item_category:
                categoryEditText.setError(getString(error));
                break;
            case R.string.missing_item_start_date:
                Snackbar.make(dateTextViewChooser, error, Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(titleEditText, R.string.error_adding_item, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdate(Object... params) {
        super.onUpdate(params);
        setResult(RESULT_OK);
        finish();
    }
}

