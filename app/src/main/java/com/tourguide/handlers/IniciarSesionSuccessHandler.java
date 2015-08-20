package com.tourguide.handlers;

import android.content.Intent;

import com.tourguide.activities.LoginActivity;
import com.tourguide.activities.MainActivity;
import com.tourguide.managers.LoginManager;
import com.tourguide.support.Constants;

public class IniciarSesionSuccessHandler extends BackendResponseHandler {

  private LoginActivity actividad;

  public IniciarSesionSuccessHandler(LoginActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    LoginManager.setLoginId(Constants.getUsuario().getId());

    actividad.startActivity(new Intent(actividad, MainActivity.class));

    actividad.finish();
  }

}
