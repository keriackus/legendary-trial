package com.keriackus.auction.presentation.presenters;

import android.text.TextUtils;
import android.view.View;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Account;
import com.keriackus.auction.domain.usecases.LoginUseCase;
import com.keriackus.auction.presentation.views.activities.BaseActivity;
import com.keriackus.auction.presentation.views.activities.LoginActivity;
import com.keriackus.auction.presentation.views.util.Validators;

/**
 * Created by keriackus on 4/1/2016.
 */
public class LoginPresenter extends PresenterImplementation implements View.OnClickListener {
    public LoginPresenter(BaseActivity view) {
        super(view);
    }

    @Override
    public void onSuccess(Object... params) {
        activity.onUpdate();
    }

    @Override
    public void onError(Object... params) {
        activity.onError(0);
        activity.showProgress(false);
    }

    public void login(LoginActivity loginActivity) {
        loginActivity.resetErrors();

        String email = loginActivity.getEmail();
        String password = loginActivity.getPassword();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginActivity.mPasswordView.setError(loginActivity.getString(R.string.error_invalid_password));
            focusView = loginActivity.mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            loginActivity.mEmailView.setError(loginActivity.getString(R.string.error_field_required));
            focusView = loginActivity.mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            loginActivity.mEmailView.setError(loginActivity.getString(R.string.error_invalid_email));
            focusView = loginActivity.mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            loginActivity.showProgress(true);
            new LoginUseCase(activity.getApplicationContext(), this, new Account(email, password)).run();
        }

    }


    private boolean isEmailValid(String email) {
        return Validators.isValidEmail(email);
    }

    private boolean isPasswordValid(String password) {
        return  Validators.isValidPassword(password);
    }

    @Override
    public void onClick(View v) {

        login((LoginActivity) activity);
    }
}
