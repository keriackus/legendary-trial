package com.keriackus.auction.presentation.presenters;

import com.keriackus.auction.presentation.views.activities.BaseActivity;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by keriackus on 4/4/2016.
 */
public class TestPresenterImp {

    public TestPresenterImp() {
    }


    BaseActivity activity = mock(BaseActivity.class);


    @Test
    public void If_Presenter_Error_IsCalled_Then_Activity_Error_isCalled() {

        PresenterImplementation presenter = new PresenterImplementation(activity);
        presenter.onError();

        verify(activity).onError(0);
    }

    @Test
    public void If_Presenter_OnSuccess_IsCalled_Then_Activity_onUpdate_isCalled() {
        PresenterImplementation presenter = new PresenterImplementation(activity);
        Object successObj = new Object();
        presenter.onSuccess(successObj);

        verify(activity).onUpdate(successObj);

    }

}
