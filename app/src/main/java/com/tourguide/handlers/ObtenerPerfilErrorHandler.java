package com.tourguide.handlers;

import android.widget.Toast;

import com.tourguide.activities.PerfilActivity;

public class ObtenerPerfilErrorHandler extends BackendResponseHandler {

  private PerfilActivity actividad;

  public ObtenerPerfilErrorHandler(PerfilActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    Toast.makeText(actividad, "No se pudo cargar el perfil.", Toast.LENGTH_SHORT).show();
  }

}
