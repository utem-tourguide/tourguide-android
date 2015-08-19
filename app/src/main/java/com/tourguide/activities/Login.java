package com.tourguide.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.tourguide.R;
import com.tourguide.models.Usuario;

public class Login extends ProgressActivity {

  private UserLoginTask mAuthTask = null;

  private EditText mEmailView;
  private EditText mPasswordView;
  private View mProgressView;
  private View mLoginFormView;

  public void attemptLogin() {
    if (mAuthTask != null) {
      return;
    }

    mEmailView.setError(null);
    mPasswordView.setError(null);

    String email = mEmailView.getText().toString();
    String password = mPasswordView.getText().toString();

    boolean cancel = false;
    View focusView = null;

    if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
      mPasswordView.setError(getString(R.string.error_invalid_password));
      focusView = mPasswordView;
      cancel = true;
    }

    if (TextUtils.isEmpty(email)) {
      mEmailView.setError(getString(R.string.error_field_required));
      focusView = mEmailView;
      cancel = true;
    } else if (!isEmailValid(email)) {
      mEmailView.setError(getString(R.string.error_invalid_email));
      focusView = mEmailView;
      cancel = true;
    }

    if (cancel) {
      focusView.requestFocus();
    } else {
      mostrarProgreso(true);
      mAuthTask = new UserLoginTask(email, password, this);
      mAuthTask.execute((Void) null);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mEmailView = (EditText) findViewById(R.id.email);

    mPasswordView = (EditText) findViewById(R.id.password);
    mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
          attemptLogin();
          return true;
        }
    });

    Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mEmailSignInButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        attemptLogin();
      }
    });

    mLoginFormView = findViewById(R.id.login_form);
    mProgressView = findViewById(R.id.login_progress);
  }

  private boolean isEmailValid(String email) {
    return email.contains("@");
  }

  private boolean isPasswordValid(String password) {
    return password.length() > 4;
  }

  @Override
  public View getEscondible() {
    return mLoginFormView;
  }

  @Override
  public ProgressBar getProgressBar() {
    return (ProgressBar) mProgressView;
  }

  private class UserLoginTask extends AsyncTask<Void, Void, Integer> {

    private final String  mEmail;
    private final String  mPassword;
    private final Context mContext;

    UserLoginTask(String email, String password, Context context) {
      mEmail    = email;
      mPassword = password;
      mContext  = context;
    }

    @Override
    protected Integer doInBackground(Void... params) {
      OkHttpClient client = new OkHttpClient();
      try {
        RequestBody formBody = new FormEncodingBuilder()
          .add("email", mEmail)
          .add("contrasena", mPassword)
          .build();

        Request request = new Request.Builder()
          .method("POST", formBody)
          .url("http://tourguide-admin.herokuapp.com/sesiones/entrar")
          .header("Accept", "application/json")
          .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
          Usuario usuario = new Gson().fromJson(response.body().charStream(), Usuario.class);
          return usuario.getId();
        } else {
          return null;
        }
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }

    @Override
    protected void onPostExecute(final Integer userId) {
      mAuthTask = null;
      mostrarProgreso(false);

      if (userId != null) {
        launchMainActivity();
      } else {
        mPasswordView.setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
      }
    }

    @Override
    protected void onCancelled() {
      mAuthTask = null;
      mostrarProgreso(false);
    }

    protected void launchMainActivity() {
      mContext.startActivity(new Intent(this.mContext, Main.class));
    }
  }
}

