package com.tourguide.handlers;

/**
 * Clase padre de todas las clases que pueden reaccionar a una respuesta del backend web.
 */
abstract public class BackendResponseHandler {

  /**
   * Actúa según una respuesta del backend web.
   */
  public abstract void handle();

}
