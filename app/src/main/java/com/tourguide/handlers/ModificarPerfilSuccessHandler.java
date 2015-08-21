package com.tourguide.handlers;

import android.widget.Toast;

import com.tourguide.activities.PerfilActivity;

public class ModificarPerfilSuccessHandler extends BackendResponseHandler {

  /**
   * Actividad de perfil de usuario.
   */
  private PerfilActivity actividad;

  public ModificarPerfilSuccessHandler(PerfilActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    Toast.makeText(actividad, "Perfil guardado.", Toast.LENGTH_SHORT).show();

    actividad.finish();
  }

}
