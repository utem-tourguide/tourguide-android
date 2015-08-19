package com.tourguide.models;

import com.tourguide.support.Constants;

/**
 * Representa un usuario de TourGuide.
 */
public class Usuario extends Model {

  public static final String[] atributos = {
    "nombre",
    "apellido",
    "email",
    "contrasena",
    "contrasena_confirmation",
    "idioma"
  };

  private String email;
  private String nombre;
  private String apellido;
  private String idioma;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public String getUrlParaModificar() {
    return Constants.BACKEND_URL + "/usuarios/" + getId();
  }
}
