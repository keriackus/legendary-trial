package com.keriackus.auction.presentation.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.presentation.presenters.HomePresenter;

import java.util.List;

public class MainActivity extends BaseActivity {

    private final int ADD_ITEM_REQUEST_CODE = 1;
    FloatingActionButton addItemFab;
    ListView itemsListView;
    Spinner toolbarSpinner;
    HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar(false, "");
        presenter = new HomePresenter(this);
        toolbarSpinner = (Spinner) findViewById(R.id.toolbar_spinner);
        addItemFab = (FloatingActionButton) findViewById(R.id.fab_add_item);
        itemsListView = (ListView) findViewById(R.id.main_items_list_view);

        itemsListView.setOnItemClickListener(presenter);
        initSpinner();

        addItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddItemActivity.class), ADD_ITEM_REQUEST_CODE);
            }
        });
    }

    private void initSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.list_types_array));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbarSpinner.setAdapter(spinnerArrayAdapter);
        toolbarSpinner.setOnItemSelectedListener(presenter);
    }

    @Override
    public void onError(int StringResId) {
        Toast.makeText(MainActivity.this, R.string.error_loading_items, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate(Object... params) {
        if (itemsListView.getEmptyView() == null) {
            itemsListView.setEmptyView(findViewById(R.id.main_items_list_empty_view));
        }
        List<Item> items = (List<Item>) params[0];
        itemsListView.setAdapter(new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                getSharedPreferences(LoginActivity.class.getName(), MODE_PRIVATE).edit().putBoolean(LoginActivity.IS_LOGGED_IN_PREFS_KEY, false).commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            presenter.reload();
        }
    }
}
