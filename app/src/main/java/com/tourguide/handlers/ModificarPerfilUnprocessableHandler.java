package com.tourguide.handlers;

import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tourguide.activities.PerfilActivity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ModificarPerfilUnprocessableHandler extends BackendResponseHandler {

  private PerfilActivity actividad;

  public ModificarPerfilUnprocessableHandler(PerfilActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void handle() {
    Map<String, EditText> inputs = generarHashDeInputs();
    Map<String, String[]> errores = generarHashDeErrores();

    for (Map.Entry<String, EditText> entrada : inputs.entrySet()) {
      String campo = entrada.getKey();
      if (errores.containsKey(campo)) entrada.getValue().setError(errores.get(campo)[0]);
    }
  }

  private Map<String, EditText> generarHashDeInputs() {
    Map<String, EditText> inputs = new HashMap<>();

    inputs.put("nombre", actividad.getNombreInput());
    inputs.put("apellido", actividad.getApellidoInput());
    inputs.put("email", actividad.getEmailInput());

    return inputs;
  }

  private Map<String, String[]> generarHashDeErrores() {
    Type tipo = new TypeToken<Map<String, String[]>>(){}.getType();

    return new Gson().fromJson(response.getBody(), tipo);
  }

}
