package com.keriackus.auction.domain.usecases;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.entities.Entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by keriackus on 4/1/2016.
 */
public interface UseCaseInterface {


    void onQueryRequestSuccess(List<Entity> entities);

    void onFindByIdRequestSuccess(Entity entity);

    void onCreateOrUpdateRequestSuccess(Dao.CreateOrUpdateStatus status, Entity entity);

    void onError(Exception exception);

    void run();
}
