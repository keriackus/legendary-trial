package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.presentation.presenters.PresenterInterface;

/**
 * Created by keriackus on 4/2/2016.
 */
public class ImaginaryBidUseCase extends  UseCaseImplementation {
    public ImaginaryBidUseCase(Context applicationContext, PresenterInterface presenter) {
        super(applicationContext, presenter);
    }

    @Override
    public void run() {

    }
}
