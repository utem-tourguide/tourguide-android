package com.tourguide.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tourguide.R;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.handlers.IniciarSesionErrorHandler;
import com.tourguide.handlers.IniciarSesionSuccessHandler;
import com.tourguide.managers.LoginManager;
import com.tourguide.models.Usuario;
import com.tourguide.support.Constants;
import com.tourguide.tasks.IniciarSesionTask;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends ProgressActivity {

  private IniciarSesionTask loginTask = null;

  private EditText emailInput;
  private EditText contraseñaInput;
  private View     progressBar;
  private View     loginForm;

  public void attemptLogin() {
    if (loginTask != null) return;

    emailInput.setError(null);
    contraseñaInput.setError(null);

    String email = emailInput.getText().toString();
    String password = contraseñaInput.getText().toString();

    boolean cancel = false;
    View focusView = null;

    if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
      contraseñaInput.setError(getString(R.string.error_invalid_password));
      focusView = contraseñaInput;
      cancel = true;
    }

    if (TextUtils.isEmpty(email)) {
      emailInput.setError(getString(R.string.error_field_required));
      focusView = emailInput;
      cancel = true;
    } else if (!isEmailValid(email)) {
      emailInput.setError(getString(R.string.error_invalid_email));
      focusView = emailInput;
      cancel = true;
    }

    if (cancel) {
      focusView.requestFocus();
    } else {
      mostrarProgreso(true);
      Map handlers = generarHandlersParaLogin();
      setLoginTask(new IniciarSesionTask(this, email, password, handlers));
      loginTask.execute((Void) null);
    }
  }

  private Map generarHandlersParaLogin() {
    Map<Boolean, BackendResponseHandler> handlers = new HashMap<>();

    handlers.put(true, new IniciarSesionSuccessHandler(this));
    handlers.put(false, new IniciarSesionErrorHandler(this));

    return handlers;
  }

  @Override
  public View getEscondible() {
    return loginForm;
  }

  @Override
  public ProgressBar getProgressBar() {
    return (ProgressBar) progressBar;
  }

  public IniciarSesionTask getLoginTask() {
    return loginTask;
  }

  public void setLoginTask(IniciarSesionTask loginTask) {
    this.loginTask = loginTask;
  }

  public EditText getEmailInput() {
    return emailInput;
  }

  public void setEmailInput(EditText emailInput) {
    this.emailInput = emailInput;
  }

  public EditText getContraseñaInput() {
    return contraseñaInput;
  }

  public void setContraseñaInput(EditText contraseñaInput) {
    this.contraseñaInput = contraseñaInput;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    LoginManager.initialize(getSharedPreferences("tourguide", Context.MODE_PRIVATE));

    if (LoginManager.hasLoginId()) {
      procederConSesionRecordada(LoginManager.getLoginId());
    } else {
      procederConFormularioLogin();
    }
  }

  private void procederConFormularioLogin() {
    setContentView(R.layout.activity_login);

    emailInput = (EditText) findViewById(R.id.email);

    contraseñaInput = (EditText) findViewById(R.id.password);
    contraseñaInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

    loginForm = findViewById(R.id.login_form);
    progressBar = findViewById(R.id.login_progress);
  }

  private void procederConSesionRecordada(int id) {
    Usuario usuario = new Usuario();
    usuario.setId(id);

    Constants.setUsuario(usuario);

    startActivity(new Intent(this, ScannerActivity.class));
  }

  private boolean isEmailValid(String email) {
    return email.contains("@");
  }

  private boolean isPasswordValid(String password) {
    return password.length() > 4;
  }

}
