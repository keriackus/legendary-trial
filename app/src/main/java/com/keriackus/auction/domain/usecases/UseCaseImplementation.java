package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.util.List;

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

    @Override
    public void onError(Exception e) {
        presenter.onError(e);
    }

    @Override
    public void onQueryRequestSuccess(List<Entity> entities) {
        presenter.onSuccess(entities);
    }

    @Override
    public void onCreateOrUpdateRequestSuccess(Dao.CreateOrUpdateStatus status, Entity entity) {
        presenter.onSuccess(entity);
    }

    @Override
    public void onFindByIdRequestSuccess(Entity entity) {
        presenter.onSuccess(entity);
    }
}
