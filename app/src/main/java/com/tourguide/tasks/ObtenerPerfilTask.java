package com.tourguide.tasks;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tourguide.activities.PerfilActivity;
import com.tourguide.factories.UsuariosFactory;
import com.tourguide.models.Usuario;

public class ObtenerPerfilTask extends AsyncTask<Void, Void, Usuario> {

  private int            usuarioId;
  private PerfilActivity actividad;

  public ObtenerPerfilTask(int usuarioId, PerfilActivity actividad) {
    this.usuarioId = usuarioId;
    this.actividad = actividad;
  }

  @Override
  protected void onPreExecute() {
    actividad.mostrarProgreso(true);
  }

  @Override
  protected Usuario doInBackground(Void... params) {
    System.out.println("Obteniendo perfil de usuario con id " + usuarioId + ".");
    return UsuariosFactory.construirConId(usuarioId);
  }

  @Override
  protected void onPostExecute(Usuario usuario) {
    System.out.println("Perfil obtenido. Mostrando perfil en pantalla.");

    actividad.mostrarProgreso(false);

    if (usuario == null) {
      Toast.makeText(actividad, "No se pudo cargar el perfil.", Toast.LENGTH_SHORT).show();
    } else {
      actividad.getNombreInput().setText(usuario.getNombre());
      actividad.getApellidoInput().setText(usuario.getApellido());
      actividad.getEmailInput().setText(usuario.getEmail());

      int posicion = obtenerPosicionDeIdioma(usuario.getIdioma());
      actividad.getIdiomaInput().setSelection(posicion);
    }

    actividad.setUsuario(usuario);
  }

  private int obtenerPosicionDeIdioma(String idioma) {
    ArrayAdapter<String> opciones = new ArrayAdapter<String>(actividad, 0);

    return opciones.getPosition(idioma);
  }
}
