package com.tourguide.models;

import com.tourguide.support.Constants;

/**
 * Representa un usuario de TourGuide.
 */
public class Usuario extends Model {

  private static final String[] CLAVES_IDIOMAS = { "es", "en", "fr" };

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

  public void setIdioma(int idiomaId) {
    this.idioma = CLAVES_IDIOMAS[idiomaId];
  }

  public String getUrlParaModificar() {
    return Constants.BACKEND_URL + "/usuarios/" + getId();
  }
}
