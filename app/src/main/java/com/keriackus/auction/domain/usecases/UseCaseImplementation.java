package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.presentation.presenters.PresenterInterface;

/**
 * Created by keriackus on 4/1/2016.
 */
public abstract class UseCaseImplementation implements UseCaseInterface {

    protected Context applicationContext;
    protected PresenterInterface presenter;

    public UseCaseImplementation(Context applicationContext, PresenterInterface presenter) {
        this.applicationContext = applicationContext;
        this.presenter = presenter;
    }

}
