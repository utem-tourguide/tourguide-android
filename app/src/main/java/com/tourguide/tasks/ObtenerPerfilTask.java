package com.tourguide.tasks;

import android.os.AsyncTask;

import com.tourguide.activities.PerfilActivity;
import com.tourguide.factories.UsuariosFactory;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.models.Usuario;

import java.util.Map;

public class ObtenerPerfilTask extends AsyncTask<Void, Void, Usuario> {

  private int                                  usuarioId;
  private PerfilActivity                       actividad;
  private Map<Boolean, BackendResponseHandler> handlers;

  public ObtenerPerfilTask(int usuarioId, PerfilActivity actividad, Map<Boolean, BackendResponseHandler> handlers) {
    this.usuarioId = usuarioId;
    this.actividad = actividad;
    this.handlers = handlers;
  }

  @Override
  protected void onPreExecute() {
    actividad.mostrarProgreso(true);
  }

  @Override
  protected Usuario doInBackground(Void... params) {
    System.out.println("Obteniendo perfil de usuario con id " + usuarioId + ".");
    return UsuariosFactory.construirPorId(usuarioId);
  }

  @Override
  protected void onPostExecute(Usuario usuario) {
    System.out.println("Perfil obtenido. Mostrando perfil en pantalla.");

    actividad.mostrarProgreso(false);
    actividad.setUsuario(usuario);

    handlers.get(usuario != null).handle();
  }

}
