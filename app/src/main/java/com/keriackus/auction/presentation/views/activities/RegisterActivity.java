package com.keriackus.auction.presentation.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.keriackus.auction.R;
import com.keriackus.auction.presentation.presenters.RegisterAccountPresenter;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends BaseActivity {


    private RegisterAccountPresenter presenter;

    public AutoCompleteTextView mEmailView;
    public EditText mPasswordView;
    public EditText mRepeatPasswordView;
    public Button mEmailRegisterButton;
    private View mProgressView;
    private View formView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupToolbar(true, R.string.title_activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mRepeatPasswordView = (EditText) findViewById(R.id.repeat_password);
        mEmailRegisterButton = (Button) findViewById(R.id.email_register_sign_in_button);

        presenter = new RegisterAccountPresenter(this);
        mEmailRegisterButton.setOnClickListener(presenter);
        formView = findViewById(R.id.email_register_form);

        mProgressView = findViewById(R.id.login_progress);
    }


    public void resetErrors() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mRepeatPasswordView.setError(null);
    }

    public String getEmail() {
        return mEmailView.getText().toString();
    }

    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    public String getRepeatedPassword() {
        return mRepeatPasswordView.getText().toString();
    }


    public void showProgress(final boolean show) {

        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        formView.setVisibility(show ? View.GONE : View.VISIBLE);
        formView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                formView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    @Override
    public void onUpdate(Object... params) {
        super.onUpdate(params);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onError(int error) {
        mEmailView.setError(getString(R.string.error_email_already_registered));
        mEmailView.requestFocus();
    }
}

