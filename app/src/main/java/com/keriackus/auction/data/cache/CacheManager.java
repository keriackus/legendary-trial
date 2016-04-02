package com.keriackus.auction.data.cache;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.entities.Entity;

import java.sql.SQLException;
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

    public <T extends Entity> void createOrUpdate(T data) throws SQLException {
        Dao<T, String> dao = (Dao<T, String>) databaseHelper.getDao(data.getClass());
        dao.createOrUpdate(data);
    }

    public Object queryById(Class clazz, Object id) throws SQLException {
        return databaseHelper.getDao(clazz).queryForId(id);
    }

    public <T extends Entity> List<T> queryForAll(Class clazz) throws SQLException {
        return databaseHelper.getDao(clazz).queryForAll();
    }

    public <T extends Entity> List<T> queryForEqual(Class clazz, String fieldName, Object value) throws SQLException {
        return databaseHelper.getDao(clazz).queryForEq(fieldName, value);
    }

}
