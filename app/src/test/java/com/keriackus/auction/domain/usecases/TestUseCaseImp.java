package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.presentation.presenters.PresenterInterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by keriackus on 4/4/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUseCaseImp {

    public TestUseCaseImp() {
    }

    PresenterInterface presenter = mock(PresenterInterface.class);
    @Mock
    Context mMockContext;


    @Test
    public void If_UseCase_Error_IsCalled_Then_Presenter_Error_isCalled() {
        UseCaseImplementation useCase = new UseCaseImplementation(mMockContext, presenter);
        Exception e = new Exception("");
        useCase.onError(e);
        verify(presenter).onError(e);

    }

    @Test
    public void If_UseCase_OnRequestSuccess_IsCalled_Then_Presenter_onSuccess_isCalled() {
        UseCaseImplementation useCase = new UseCaseImplementation(mMockContext, presenter);
        useCase.onCreateOrUpdateRequestSuccess(null, null);
        useCase.onFindByIdRequestSuccess(null);
        useCase.onQueryRequestSuccess(null);

        verify(presenter, atLeastOnce()).onSuccess(null);
    }



}
