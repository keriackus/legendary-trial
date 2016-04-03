package com.keriackus.auction.data.cache;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.domain.usecases.GetItemsUseCase;
import com.keriackus.auction.domain.usecases.UseCaseInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keriackus on 4/1/2016.
 */
public class CacheManager {

    private static volatile CacheManager instance;
    private DatabaseHelper databaseHelper;
    private Context context;


    public static CacheManager getInstance(Context context) {
        if (instance == null) {
            instance = new CacheManager(context);
        }

        return instance;
    }

    private CacheManager(Context context) {
        this.context = context.getApplicationContext();
        databaseHelper = new DatabaseHelper(this.context);
    }

    public <T extends Entity> void createOrUpdate(T data, UseCaseInterface useCase) {
        try {
            Dao<T, String> dao = (Dao<T, String>) databaseHelper.getDao(data.getClass());
            Dao.CreateOrUpdateStatus status = dao.createOrUpdate(data);
            useCase.onCreateOrUpdateRequestSuccess(status, data);
        } catch (SQLException e) {
            useCase.onError(e);
        }
    }

    public <T extends Entity> T queryById(Class clazz, Object id, UseCaseInterface useCase) {
        T entity = null;
        try {
            entity = (T) databaseHelper.getDao(clazz).queryForId(id);
            useCase.onFindByIdRequestSuccess(entity);
        } catch (SQLException e) {
            useCase.onError(e);
        }

        return entity;
    }

    public <T extends Entity> List<T> queryForAll(Class clazz, UseCaseInterface useCase) {
        List entities = new ArrayList();
        try {
            entities = databaseHelper.getDao(clazz).queryForAll();
            useCase.onQueryRequestSuccess(entities);
        } catch (SQLException e) {
            useCase.onError(e);
        }
        return entities;
    }

    public <T extends Entity> List<T> queryForEqual(Class clazz, String fieldName, Object value, UseCaseInterface useCase) {
        List entities = new ArrayList();
        try {
            entities = databaseHelper.getDao(clazz).queryForEq(fieldName, value);
            useCase.onQueryRequestSuccess(entities);
        } catch (SQLException e) {
            useCase.onError(e);
        }
        return entities;
    }

}
