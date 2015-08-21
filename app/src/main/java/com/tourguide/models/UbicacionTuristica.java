package com.tourguide.models;

public class UbicacionTuristica extends Model {

  private String nombre;
  private String localizacion;
  private Informacion[] informacion;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getLocalizacion() {
    return localizacion;
  }

  public void setLocalizacion(String localizacion) {
    this.localizacion = localizacion;
  }

  public Informacion[] getInformacion() {
    return informacion;
  }

  public void setInformacion(Informacion[] informacion) {
    this.informacion = informacion;
  }

  public Informacion getInformacionEnIdioma(String idioma) {
    for (Informacion info: informacion) {
      if (info.getIdioma().equals(idioma)) return info;
    }

    return null;
  }

}
