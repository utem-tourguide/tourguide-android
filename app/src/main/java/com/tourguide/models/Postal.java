package com.tourguide.models;

import com.tourguide.support.Constants;

public class Postal extends Model {

  private float precio;
  private int ubicacionId;

  public float getPrecio() {
    return precio;
  }

  public void setPrecio(float precio) {
    this.precio = precio;
  }

  public int getUbicacionId() {
    return ubicacionId;
  }

  public void setUbicacionId(int ubicacionId) {
    this.ubicacionId = ubicacionId;
  }

  public String getUrlParaDescargar() {
    return Constants.BACKEND_URL + "/images/postales/" + id;
  }

}
