package com.tourguide.handlers;

import android.widget.ArrayAdapter;

import com.tourguide.activities.PerfilActivity;
import com.tourguide.models.Usuario;

public class ObtenerPerfilSuccessHandler extends BackendResponseHandler {

  private PerfilActivity actividad;

  public ObtenerPerfilSuccessHandler(PerfilActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    Usuario usuario = actividad.getUsuario();

    actividad.getNombreInput().setText(usuario.getNombre());
    actividad.getApellidoInput().setText(usuario.getApellido());
    actividad.getEmailInput().setText(usuario.getEmail());

    int posicion = obtenerPosicionDeIdioma(usuario.getIdioma());
    actividad.getIdiomaInput().setSelection(posicion);
  }

  private int obtenerPosicionDeIdioma(String idioma) {
    ArrayAdapter<String> opciones = new ArrayAdapter<String>(actividad, 0);

    return opciones.getPosition(idioma);
  }

}
