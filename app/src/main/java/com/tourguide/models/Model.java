package com.tourguide.models;

import com.google.gson.Gson;

/**
 * Clase base para los modelos del app.
 */
abstract public class Model {

  /**
   * Identificador único del modelo.
   */
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

}
