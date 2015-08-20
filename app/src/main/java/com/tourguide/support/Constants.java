package com.tourguide.support;

import com.tourguide.models.Usuario;

/**
 * Almacena constantes que pueden accederse desde toda la aplicación y que no pertenecen a alguna
 * clase en particular.
 */
public class Constants {

  private static Usuario usuario;

  public static final String BACKEND_URL = "http://192.168.1.68:8000";

  public static synchronized Usuario getUsuario() {
    return usuario;
  }

  public static synchronized void setUsuario(Usuario usuario) {
    Constants.usuario = usuario;
  }

}
