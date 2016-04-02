package com.keriackus.auction.presentation.presenters;

import com.keriackus.auction.domain.usecases.UseCaseImplementation;
import com.keriackus.auction.presentation.views.activities.BaseActivity;

/**
 * Created by keriackus on 4/1/2016.
 */
public abstract class PresenterImplementation  implements PresenterInterface{

    protected BaseActivity activity;
    protected UseCaseImplementation useCase;

    public PresenterImplementation(BaseActivity view) {
        this.activity = view;
    }


}
