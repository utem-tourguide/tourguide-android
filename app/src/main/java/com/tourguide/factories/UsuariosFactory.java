package com.tourguide.factories;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.tourguide.models.Usuario;
import com.tourguide.support.Constants;

import java.io.IOException;

public class UsuariosFactory extends Factory {

  public static Usuario construirPorId(int id) {
    Usuario usuario = null;

    try {
      usuario = obtenerDatosDeUsuarioDesdeBackend(id);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return usuario;
  }

  public static Usuario construirPorCredenciales(String email, String contraseña) {
    Request request = generarPeticionParaIniciarSesion(email, contraseña);
    Response response;
    Usuario usuario = null;

    try {
      response = new OkHttpClient().newCall(request).execute();
      if (response.isSuccessful()) {
        usuario = new Gson().fromJson(response.body().string(), Usuario.class);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return usuario;
  }

  private static Usuario obtenerDatosDeUsuarioDesdeBackend(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarPeticionParaDatosDeUsuario(id)).execute();

    Usuario usuario;
    if (response.isSuccessful()) {
      usuario = new Gson().fromJson(response.body().charStream(), Usuario.class);
    } else {
      usuario = null;
    }

    return usuario;
  }

  private static Request generarPeticionParaIniciarSesion(String email, String contraseña) {
    RequestBody formBody = generarCuerpoParaIniciarSesion(email, contraseña);

    return new Request.Builder()
      .post(formBody)
      .url(Constants.BACKEND_URL + "/sesiones")
      .header("Accept", "application/json")
      .build();
  }

  private static Request generarPeticionParaDatosDeUsuario(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/usuarios/" + id)
                                .header("Accept", "application/json")
                                .build();
  }

  private static RequestBody generarCuerpoParaIniciarSesion(String email, String contraseña) {
    return new FormEncodingBuilder()
      .add("email", email)
      .add("contrasena", contraseña)
      .build();
  }

}
