package com.tourguide.handlers;

import com.tourguide.activities.InformacionActivity;
import com.tourguide.fragments.InformacionFragment;
import com.tourguide.models.UbicacionTuristica;

public class ObtenerInfoUbicacionSuccessHandler extends BackendResponseHandler {

  private InformacionActivity actividad;
  private InformacionFragment fragment;

  @Override
  public void handle() {
    UbicacionTuristica ubicacion = actividad.getUbicacion();

    fragment.getInformacionTituloText().setText(ubicacion.getNombre());
  }

}
