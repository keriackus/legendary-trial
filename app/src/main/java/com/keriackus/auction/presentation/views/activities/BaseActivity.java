package com.keriackus.auction.presentation.views.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.keriackus.auction.R;

/**
 * Created by keriackus on 4/1/2016.
 */
public class BaseActivity extends AppCompatActivity implements ViewListener {

    private ActionBar actionBar;
    private ProgressBar progress;

    private void setupToolbarHelper(boolean showUpButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.black));

        progress = (ProgressBar) toolbar.findViewById(R.id.toolbar_progress_spinner);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(showUpButton);
    }

    protected void setupToolbar(boolean showUpButton, int titleResID) {
        setupToolbarHelper(showUpButton);
        if (titleResID != -1)
            actionBar.setTitle(titleResID);
    }

    protected void setupToolbar(boolean showUpButton, String title) {
        setupToolbarHelper(showUpButton);
        if (title != null)
            actionBar.setTitle(title);
    }

    protected Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);

    }

    protected void setupToolbar(boolean showUpButton, String titleRes, int homeIndicatorResID) {
        setupToolbar(showUpButton, titleRes);
        actionBar.setHomeAsUpIndicator(homeIndicatorResID);
    }

    protected void setupToolbar(boolean showUpButton, int titleResID, int homeIndicatorResID) {
        setupToolbar(showUpButton, titleResID);
        actionBar.setHomeAsUpIndicator(homeIndicatorResID);
    }

    public void showProgressInActionBar() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgressInActionBar() {
        progress.setVisibility(View.GONE);
    }


    public void setActionBarTitle(int resId) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(resId);
    }

    @Override
    public void onUpdate(Object... params) {

    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    @Override
    public void onError(int error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyboard();
    }

    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null)
            inputManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public void showProgress(boolean b) {

    }
}
