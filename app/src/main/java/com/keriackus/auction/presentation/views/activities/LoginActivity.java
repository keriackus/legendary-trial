package com.keriackus.auction.presentation.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.keriackus.auction.R;
import com.keriackus.auction.presentation.presenters.LoginPresenter;


public class LoginActivity extends BaseActivity {


    LoginPresenter presenter;
    private UserLoginTask mAuthTask = null;
    // UI references.
    public AutoCompleteTextView mEmailView;
    public EditText mPasswordView;
    protected View mProgressView;
    protected View formView;

    static String IS_LOGGED_IN_PREFS_KEY = "IS.LOGGED.IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        boolean isLoggedIn = getSharedPreferences(LoginActivity.class.getName(), MODE_PRIVATE).getBoolean(IS_LOGGED_IN_PREFS_KEY, false);
        if (isLoggedIn) {
            goToHomeActivity();
            return;
        }
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    presenter.login(LoginActivity.this);
                    return true;
                }
                return false;
            }
        });
        formView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(presenter);

        Button mEmailRegisterButton = (Button) findViewById(R.id.email_register_button);
        mEmailRegisterButton.setPaintFlags(mEmailRegisterButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mEmailRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), REGISTER_RESULT_CODE);
            }
        });

    }

    public static final int REGISTER_RESULT_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_RESULT_CODE && resultCode == RESULT_OK) {
            goToHomeActivity();
        }
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

    public void resetErrors() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

    }

    public String getEmail() {
        return mEmailView.getText().toString();
    }

    public String getPassword() {
        return mPasswordView.getText().toString();
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                //give the progress bar a chance to show
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }


            // TODO access database and check for user
            boolean userFound = false;

            return userFound;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    @Override
    public void onUpdate(Object... params) {
        super.onUpdate(params);
        goToHomeActivity();
    }

    @Override
    public void onError(int error) {
        super.onError(error);

        mEmailView.setError(getString(R.string.error_incorrect_email));
    }

    private void goToHomeActivity() {
        getSharedPreferences(LoginActivity.class.getName(), MODE_PRIVATE).edit().putBoolean(IS_LOGGED_IN_PREFS_KEY, true).commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

