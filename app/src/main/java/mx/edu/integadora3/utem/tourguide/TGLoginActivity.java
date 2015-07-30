package mx.edu.integadora3.utem.tourguide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import mx.edu.integadora3.utem.tourguide.models.User;

public class TGLoginActivity extends Activity implements LoaderCallbacks<Cursor> {

  private UserLoginTask mAuthTask = null;

  private AutoCompleteTextView mEmailView;
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
      showProgress(true);
      mAuthTask = new UserLoginTask(email, password, this);
      mAuthTask.execute((Void) null);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tglogin);

    mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
    populateAutoComplete();

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

  private void populateAutoComplete() {
    getLoaderManager().initLoader(0, null, this);
  }

  private boolean isEmailValid(String email) {
    return email.contains("@");
  }

  private boolean isPasswordValid(String password) {
    return password.length() > 4;
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public void showProgress(final boolean show) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
      mLoginFormView.animate().setDuration(shortAnimTime).alpha(
        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
    } else {
      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
  }

  @Override
  public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
    return new CursorLoader(this,
      Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

      ContactsContract.Contacts.Data.MIMETYPE +
        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
      .CONTENT_ITEM_TYPE},

      ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
  }

  @Override
  public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
    List<String> emails = new ArrayList<String>();
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      emails.add(cursor.getString(ProfileQuery.ADDRESS));
      cursor.moveToNext();
    }

    addEmailsToAutoComplete(emails);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> cursorLoader) {

  }

  private interface ProfileQuery {
    String[] PROJECTION = {
      ContactsContract.CommonDataKinds.Email.ADDRESS,
      ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
    };

    int ADDRESS = 0;
  }


  private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
    ArrayAdapter<String> adapter =
      new ArrayAdapter<String>(TGLoginActivity.this,
        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

    mEmailView.setAdapter(adapter);
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
          User user = new Gson().fromJson(response.body().charStream(), User.class);
          return user.getId();
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
      showProgress(false);

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
      showProgress(false);
    }

    protected void launchMainActivity() {
      mContext.startActivity(new Intent(this.mContext, TGMainActivity.class));
    }
  }
}

