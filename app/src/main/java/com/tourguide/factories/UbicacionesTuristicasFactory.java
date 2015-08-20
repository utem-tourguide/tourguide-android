package com.tourguide.factories;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tourguide.models.Informacion;
import com.tourguide.models.UbicacionTuristica;
import com.tourguide.support.Constants;

import java.io.IOException;

public class UbicacionesTuristicasFactory extends Factory {

  public static UbicacionTuristica construirPorId(int id) {
    UbicacionTuristica ubicacion = null;

    try {
      ubicacion = obtenerUbicacionDesdeBackend(id);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ubicacion;
  }

  private static UbicacionTuristica obtenerUbicacionDesdeBackend(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarPeticionParaUbicacion(id)).execute();

    UbicacionTuristica ubicacion;
    if (response.isSuccessful()) {
      ubicacion = new Gson().fromJson(response.body().charStream(), UbicacionTuristica.class);
      ubicacion.setInformacion(obtenerInfoDeUbicacionDesdeBackend(id));
    } else {
      ubicacion = null;
    }

    return ubicacion;
  }

  private static Request generarPeticionParaUbicacion(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/ubicaciones/" + id)
        .header("Accept", "application/json")
        .build();
  }

  private static Informacion[] obtenerInfoDeUbicacionDesdeBackend(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarPeticionParaInfoDeUbicacion(id)).execute();
    Informacion[] info = construirInformacion(response.body().string());

    return info;
  }

  private static Informacion[] construirInformacion(String json) {
    Informacion[] info = new Gson().fromJson(json, Informacion[].class);

    return info;
  }

  private static Request generarPeticionParaInfoDeUbicacion(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/ubicaciones/" + id + "/informacion")
                                .header("Accept", "application/json")
                                .build();
  }

}
