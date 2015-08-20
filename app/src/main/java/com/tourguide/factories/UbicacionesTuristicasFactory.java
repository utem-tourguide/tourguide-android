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
    String json = obtenerJSONParaUbicacion(id);

    UbicacionTuristica ubicacion = null;
    if (json != null) {
      ubicacion = construirUbicacionDesdeJson(json);
      ubicacion.setInformacion(obtenerInfoDeUbicacionDesdeBackend(id));
    }

    return ubicacion;
  }

  private static String obtenerJSONParaUbicacion(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarPeticionParaUbicacion(id)).execute();

    String body = response.body().string();
    System.out.println("RESPUESTA: " + body);

    return response.isSuccessful() ? body : null;
  }

  private static UbicacionTuristica construirUbicacionDesdeJson(String json) throws IOException {
    UbicacionTuristica ubicacion = new Gson().fromJson(json, UbicacionTuristica.class);

    return ubicacion;
  }

  private static Request generarPeticionParaUbicacion(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/ubicaciones/" + id)
        .header("Accept", "application/json")
        .build();
  }

  private static Informacion[] obtenerInfoDeUbicacionDesdeBackend(int id) throws IOException {
    Informacion[] info = construirInformacionDesdeJSON(obtenerJSONParaInfoDeUbicacion(id));

    return info;
  }

  private static String obtenerJSONParaInfoDeUbicacion(int id) throws IOException {
    Response response = new OkHttpClient().newCall(generarPeticionParaInfoDeUbicacion(id)).execute();

    return response.body().string();
  }

  private static Informacion[] construirInformacionDesdeJSON(String json) {
    Informacion[] info = new Gson().fromJson(json, Informacion[].class);

    return info;
  }

  private static Request generarPeticionParaInfoDeUbicacion(int id) {
    return new Request.Builder().url(Constants.BACKEND_URL + "/ubicaciones/" + id + "/informacion")
                                .header("Accept", "application/json")
                                .build();
  }

}
