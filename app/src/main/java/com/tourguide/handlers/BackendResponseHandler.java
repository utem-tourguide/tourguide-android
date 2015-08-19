package com.tourguide.handlers;

import com.tourguide.backend.Response;

/**
 * Clase padre de todas las clases que pueden reaccionar a una respuesta del backend web.
 */
abstract public class BackendResponseHandler {

  protected Response response;

  /**
   * Actúa según la respuesta del backend web.
   */
  public abstract void handle();

  /**
   * Devuelve la respuesta HTTP enviada por el backend web.
   */
  public Response getResponse() {
    return response;
  }

  /**
   * Coloca la respuesta HTTP enviada por el backend web.
   *
   * @param response
   */
  public void setResponse(Response response) {
    this.response = response;
  }
}
