package com.tourguide.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.tourguide.activities.PerfilActivity;
import com.tourguide.models.Usuario;

import java.io.IOException;

public class ModificarPerfilTask extends AsyncTask<Void, Void, Boolean> {

  private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; chaset=utf8");

  /**
   * Usuario cuyo perfil se modificará.
   */
  private Usuario usuario;

  /**
   * Instancia de PerfilActivity.
   */
  private PerfilActivity actividad;

  public ModificarPerfilTask(Usuario usuario, PerfilActivity actividad) {
    this.usuario = usuario;
    this.actividad = actividad;
  }

  @Override
  protected void onPreExecute() {
    System.out.println("Guardando perfil de usuario en backend web...");

    actividad.mostrarProgreso(true);
  }

  @Override
  protected void onPostExecute(Boolean exito) {
    System.out.println("Información enviada. Procesando respuesta.");

    actividad.mostrarProgreso(false);

    String mensaje = exito ? "Perfil guardado" : "No se pudo guardar el perfil";
    Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT).show();

    actividad.finish();
  }

  @Override
  protected Boolean doInBackground(Void... params) {
    return ejecutarRequest(generarRequest());
  }

  private Request generarRequest() {
    return new Request.Builder().url(usuario.getUrlParaModificar())
                                .header("Accept", MEDIA_TYPE_JSON.toString())
                                .patch(RequestBody.create(MEDIA_TYPE_JSON, usuario.toJson()))
                                .build();
  }

  private Boolean ejecutarRequest(Request request) {
    Response response;

    try {
      response = new OkHttpClient().newCall(request).execute();
      if (response.isSuccessful()) {
        return true;
      } else {
        System.out.println("Ocurrió un error al intentar guardar el perfil.");
        System.out.println(response.body().string());
        return false;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return false;
  }

}
