package com.tourguide.factories;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tourguide.models.Usuario;
import com.tourguide.support.Constants;

import java.io.IOException;

public class UsuariosFactory {

  public static Usuario construirConId(int id) {
    Usuario usuario;

    try {
      usuario = obtenerDatosDeUsuarioDesdeBackend(id);
    } catch (IOException e) {
      e.printStackTrace();
      usuario = null;
    }

    return usuario;
  }

  private static Usuario obtenerDatosDeUsuarioDesdeBackend(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarRequestParaDatosDeUsuario(id)).execute();

    Usuario usuario;
    if (response.isSuccessful()) {
      usuario = new Gson().fromJson(response.body().charStream(), Usuario.class);
    } else {
      usuario = null;
    }

    return usuario;
  }

  private static Request generarRequestParaDatosDeUsuario(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/usuarios/" + id)
                                .header("Accept", "application/json")
                                .build();
  }

}
