package com.tourguide.tasks;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.tourguide.activities.Perfil;
import com.tourguide.factories.UsuariosFactory;
import com.tourguide.models.Usuario;

public class ObtenerPerfilTask extends AsyncTask<Void, Void, Usuario> {

  private int usuarioId;
  private Perfil actividad;

  public ObtenerPerfilTask(int usuarioId, Perfil actividad) {
    this.usuarioId = usuarioId;
    this.actividad = actividad;
  }

  @Override
  protected Usuario doInBackground(Void... params) {
    System.out.println("Obteniendo perfil de usuario con id " + usuarioId + ".");
    return UsuariosFactory.construirConId(usuarioId);
  }

  @Override
  protected void onPostExecute(Usuario usuario) {
    System.out.println("Perfil obtenido. Mostrando perfil en pantalla.");

    actividad.nombre.setText(usuario.getNombre());
    actividad.apellido.setText(usuario.getApellido());
    actividad.email.setText(usuario.getEmail());

    int posicion = obtenerPosicionDeIdioma(usuario.getIdioma());
    actividad.idioma.setSelection(posicion);
  }

  private int obtenerPosicionDeIdioma(String idioma) {
    ArrayAdapter<String> opciones = new ArrayAdapter<String>(actividad, 0);

    return opciones.getPosition(idioma);
  }
}
