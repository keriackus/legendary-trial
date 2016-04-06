package com.keriackus.auction.data.cache;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.j256.ormlite.dao.Dao;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.data.entities.Item;
import com.keriackus.auction.domain.usecases.UseCaseInterface;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by keriackus on 4/5/2016.
 */
@RunWith(AndroidJUnit4.class)

public class TestCacheManager extends ActivityInstrumentationTestCase2 {
    public TestCacheManager(Class activityClass) {
        super(activityClass);
    }

    @Test
    public void x() {
        CacheManager cacheManager = CacheManager.getInstance(getActivity().getApplicationContext());

        UseCaseInterface useCase = new UseCaseInterface() {
            @Override
            public void onQueryRequestSuccess(List<Entity> entities) {
                assertThat(entities.size(), is(665));
            }

            @Override
            public void onFindByIdRequestSuccess(Entity entity) {

            }

            @Override
            public void onCreateOrUpdateRequestSuccess(Dao.CreateOrUpdateStatus status, Entity entity) {


            }

            @Override
            public void onError(Exception exception) {

            }

            @Override
            public void run() {

            }
        };

        cacheManager.createOrUpdate(new Item(), useCase);

        cacheManager.queryForAll(Item.class, useCase);

        assertThat(true, is(false));
    }

}
