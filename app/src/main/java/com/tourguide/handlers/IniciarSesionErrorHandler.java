package com.tourguide.handlers;

import android.widget.EditText;

import com.tourguide.R;
import com.tourguide.activities.LoginActivity;

public class IniciarSesionErrorHandler extends BackendResponseHandler {

  private LoginActivity actividad;

  public IniciarSesionErrorHandler(LoginActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    EditText contraseñaInput = actividad.getContraseñaInput();
    contraseñaInput.setError(actividad.getString(R.string.error_incorrect_password));
    contraseñaInput.requestFocus();
  }

}
