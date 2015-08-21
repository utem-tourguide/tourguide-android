package com.tourguide.handlers;

import com.tourguide.activities.InformacionActivity;
import com.tourguide.fragments.InformacionFragment;
import com.tourguide.models.Informacion;
import com.tourguide.models.UbicacionTuristica;

public class ObtenerInfoUbicacionSuccessHandler extends BackendResponseHandler {

  private InformacionActivity actividad;
  private InformacionFragment fragment;

  public ObtenerInfoUbicacionSuccessHandler(InformacionActivity actividad, InformacionFragment fragment) {
    this.actividad = actividad;
    this.fragment = fragment;
  }

  @Override
  public void handle() {
    UbicacionTuristica ubicacion = actividad.getUbicacion();

    actividad.setTitle(ubicacion.getNombre());

    fragment.getInformacionTituloText().setText(ubicacion.getNombre());
    fragment.getInformacionLocalizacionText().setText(ubicacion.getLocalizacion());

    // TODO: Mostrar información en el idioma preferido del usuario.
    Informacion informacion = ubicacion.getInformacionEnIdioma("es");
    fragment.getInformacionContenidoText().setText(informacion.getContenido());
  }

}
