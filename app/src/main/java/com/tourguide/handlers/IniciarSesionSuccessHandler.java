package com.tourguide.handlers;

import android.content.Intent;

import com.tourguide.activities.LoginActivity;
import com.tourguide.activities.MainActivity;

public class IniciarSesionSuccessHandler extends BackendResponseHandler {

  private LoginActivity actividad;

  public IniciarSesionSuccessHandler(LoginActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    actividad.startActivity(new Intent(actividad, MainActivity.class));
  }

}
