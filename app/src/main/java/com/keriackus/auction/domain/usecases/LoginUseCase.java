package com.keriackus.auction.domain.usecases;

import android.content.Context;

import com.keriackus.auction.data.cache.CacheManager;
import com.keriackus.auction.data.entities.Account;
import com.keriackus.auction.data.entities.Entity;
import com.keriackus.auction.presentation.presenters.PresenterInterface;

import java.sql.SQLException;

/**
 * Created by keriackus on 4/1/2016.
 */
public class LoginUseCase extends UseCaseImplementation {

    Account account;

    public LoginUseCase(Context applicationContext, PresenterInterface presenter, Account account) {
        super(applicationContext, presenter);
        this.account = account;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run() {
        CacheManager cacheManager = CacheManager.getInstance(applicationContext);
        cacheManager.queryById(Account.class, account.getEmail(), this);
    }

    @Override
    public void onFindByIdRequestSuccess(Entity entity) {
        Account cachedAccount = (Account) entity;
        if (cachedAccount == null) {
            presenter.onError();
        } else if (!cachedAccount.getPassword().equals(account.getPassword())) {
            presenter.onError();
        } else {
            presenter.onSuccess();
        }

    }
}
