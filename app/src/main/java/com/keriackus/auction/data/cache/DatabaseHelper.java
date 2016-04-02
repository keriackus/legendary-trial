package com.keriackus.auction.data.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.keriackus.auction.data.entities.Account;
import com.keriackus.auction.data.entities.Item;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keriackus on 4/1/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "auction.keriackus";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;

    /**
     * Constructor,
     *
     * @param context Context used for database
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Account.class);
            TableUtils.createTable(connectionSource, Item.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, Account.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Map<Class, Dao> dateAccessObjects = new HashMap<>();


    public Dao<?, ?> getDao(Object data) throws SQLException {
        Class clazz = data.getClass();
        if (dateAccessObjects.get(clazz) == null) {
            dateAccessObjects.put(clazz, getDao(clazz));
        }
        return dateAccessObjects.get(clazz);
    }
}
