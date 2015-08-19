package com.tourguide.tasks;

import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.tourguide.activities.PerfilActivity;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.models.Usuario;

import java.io.IOException;
import java.util.HashMap;

public class ModificarPerfilTask extends AsyncTask<Void, Void, Response> {

  private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; chaset=utf8");

  /**
   * Usuario cuyo perfil se modificará.
   */
  private Usuario usuario;

  /**
   * Instancia de PerfilActivity.
   */
  private PerfilActivity actividad;

  /**
   * Manejadores de respuestas del backend.
   */
  private HashMap<Integer, BackendResponseHandler> handlers;

  public ModificarPerfilTask(Usuario usuario, PerfilActivity actividad, HashMap<Integer, BackendResponseHandler> handlers) {
    this.usuario = usuario;
    this.actividad = actividad;
    this.handlers = handlers;
  }

  @Override
  protected void onPreExecute() {
    System.out.println("Guardando perfil de usuario en backend web...");

    actividad.mostrarProgreso(true);
  }

  @Override
  protected void onPostExecute(Response respuesta) {
    System.out.println("Información enviada. Procesando respuesta.");

    handlers.get(respuesta.code()).handle();

    actividad.finish();
  }

  @Override
  protected Response doInBackground(Void... params) {
    return ejecutarPeticion(generarRequest());
  }

  private Request generarRequest() {
    return new Request.Builder().url(usuario.getUrlParaModificar())
                                .header("Accept", MEDIA_TYPE_JSON.toString())
                                .patch(RequestBody.create(MEDIA_TYPE_JSON, usuario.toJson()))
                                .build();
  }

  private Response ejecutarPeticion(Request request) {
    Response respuesta = null;

    try {
      respuesta = new OkHttpClient().newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return respuesta;
  }

}
