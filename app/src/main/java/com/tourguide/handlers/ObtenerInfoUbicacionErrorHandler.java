package com.tourguide.handlers;

import android.widget.Toast;

import com.tourguide.activities.InformacionActivity;

public class ObtenerInfoUbicacionErrorHandler extends BackendResponseHandler {

  private InformacionActivity actividad;

  public ObtenerInfoUbicacionErrorHandler(InformacionActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    String mensaje = "No se pudo obtener la información de la ubicación";
    Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT).show();
  }

}
