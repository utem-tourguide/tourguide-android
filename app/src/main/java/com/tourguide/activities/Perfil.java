package com.tourguide.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.tourguide.R;
import com.tourguide.tasks.ObtenerPerfilTask;

public class Perfil extends Activity {

  private static final int USUARIO_ID = 1;

  public EditText nombre;
  public EditText apellido;
  public EditText email;
  public EditText contraseña;
  public EditText contraseña_confirmation;
  public Spinner  idioma;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_perfil);

    cargarReferencias();
  }

  @Override
  protected void onStart() {
    super.onStart();

    cargarPerfil();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_perfil, menu);

    return true;
  }

  private void cargarReferencias() {
    nombre = (EditText) findViewById(R.id.nombre);
    apellido = (EditText) findViewById(R.id.apellido);
    email = (EditText) findViewById(R.id.email);
    contraseña = (EditText) findViewById(R.id.contrasena);
    contraseña_confirmation = (EditText) findViewById(R.id.contrasena_confirmation);
    idioma = (Spinner) findViewById(R.id.idioma);
  }

  private void cargarPerfil() {
    ObtenerPerfilTask tarea = new ObtenerPerfilTask(USUARIO_ID, this);
    tarea.execute();
  }

  /**
   * Guarda los datos del perfil del usuario en el backend web.
   *
   * @param item
   */
  public void onGuardarItemClick(MenuItem item) {

  }

}
