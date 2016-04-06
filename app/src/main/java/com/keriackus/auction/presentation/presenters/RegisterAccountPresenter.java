package com.keriackus.auction.presentation.presenters;

import android.text.TextUtils;
import android.view.View;

import com.keriackus.auction.R;
import com.keriackus.auction.data.entities.Account;
import com.keriackus.auction.domain.usecases.RegisterAccountUseCase;
import com.keriackus.auction.presentation.views.activities.RegisterActivity;

/**
 * Created by keriackus on 4/1/2016.
 */
public class RegisterAccountPresenter extends PresenterImplementation implements View.OnClickListener {

    public RegisterAccountPresenter(RegisterActivity view) {
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

    @Override
    public void onClick(View v) {
        RegisterActivity registerActivity = (RegisterActivity) activity;
        if (v.getId() == registerActivity.mEmailRegisterButton.getId()) {
            attemptRegister(registerActivity);
        }
    }


    public void attemptRegister(RegisterActivity registerActivity) {

        registerActivity.resetErrors();

        String email = registerActivity.getEmail();
        String password = registerActivity.getPassword();
        String passwordRepeated = registerActivity.getRepeatedPassword();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            registerActivity.mPasswordView.setError(registerActivity.getString(R.string.error_field_required));
            focusView = registerActivity.mPasswordView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            registerActivity.mPasswordView.setError(registerActivity.getString(R.string.error_invalid_password));
            focusView = registerActivity.mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(passwordRepeated)) {
            registerActivity.mRepeatPasswordView.setError(registerActivity.getString(R.string.error_field_required));
            focusView = registerActivity.mRepeatPasswordView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(passwordRepeated) && !password.equals(passwordRepeated)) {
            registerActivity.mRepeatPasswordView.setError(registerActivity.getString(R.string.error_password_mismatch));
            focusView = registerActivity.mRepeatPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            registerActivity.mEmailView.setError(registerActivity.getString(R.string.error_field_required));
            focusView = registerActivity.mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            registerActivity.mEmailView.setError(registerActivity.getString(R.string.error_invalid_email));
            focusView = registerActivity.mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            registerActivity.showProgress(true);
            registerAccount(new Account(email, password));
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void registerAccount(Account account) {
        useCase = new RegisterAccountUseCase(activity.getApplicationContext(), this, account);
        useCase.run();
    }
}
