package com.keriackus.auction.presentation.presenters;

/**
 * Created by keriackus on 4/1/2016.
 */
public interface PresenterInterface {
    void onSuccess(Object... params);

    void onError(Object... params);
}
