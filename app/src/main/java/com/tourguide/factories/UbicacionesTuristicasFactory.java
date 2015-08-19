package com.tourguide.factories;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tourguide.models.UbicacionTuristica;
import com.tourguide.support.Constants;

import java.io.IOException;

public class UbicacionesTuristicasFactory extends Factory {

  public static UbicacionTuristica construirPorId(int id) {
    UbicacionTuristica ubicacion = null;

    try {
      ubicacion = obtenerDatosDeUbicacionDesdeBackend(id);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ubicacion;
  }

  private static UbicacionTuristica obtenerDatosDeUbicacionDesdeBackend(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarPeticionParaDatosDeUbicacion(id)).execute();

    UbicacionTuristica ubicacion;
    if (response.isSuccessful()) {
      ubicacion = new Gson().fromJson(response.body().charStream(), UbicacionTuristica.class);
    } else {
      ubicacion = null;
    }

    return ubicacion;
  }

  private static Request generarPeticionParaDatosDeUbicacion(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/ubicaciones/" + id)
                                .header("Accept", "application/json")
                                .build();
  }

}
