package com.tourguide.tasks;

import android.os.AsyncTask;

import com.tourguide.activities.LoginActivity;
import com.tourguide.factories.UsuariosFactory;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.models.Usuario;
import com.tourguide.support.Constants;

import java.util.Map;

public class IniciarSesionTask extends AsyncTask<Void, Void, Usuario> {

  private String                               email;
  private String                               contraseña;
  private LoginActivity                        actividad;
  private Map<Boolean, BackendResponseHandler> handlers;

  public IniciarSesionTask(LoginActivity actividad, String email, String password, Map<Boolean, BackendResponseHandler> handlers) {
    this.email = email;
    contraseña = password;
    this.actividad = actividad;
    this.handlers = handlers;
  }

  @Override
  protected Usuario doInBackground(Void... params) {
    return UsuariosFactory.construirPorCredenciales(email, contraseña);
  }

  @Override
  protected void onPostExecute(Usuario usuario) {
    onCancelled();

    Constants.setUsuario(usuario);

    handlers.get(usuario != null).handle();
  }

  @Override
  protected void onCancelled() {
    actividad.setLoginTask(null);
    actividad.mostrarProgreso(false);
  }

}
