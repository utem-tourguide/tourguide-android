package com.tourguide.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.tourguide.R;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.handlers.ModificarPerfilSuccessHandler;
import com.tourguide.models.Usuario;
import com.tourguide.tasks.ModificarPerfilTask;
import com.tourguide.tasks.ObtenerPerfilTask;

import java.util.HashMap;

public class PerfilActivity extends ProgressActivity {

  private static final int USUARIO_ID = 10;

  public EditText nombre;
  public EditText apellido;
  public EditText email;
  public EditText contraseña;
  public EditText contraseña_confirmation;
  public Spinner  idioma;

  private Usuario usuario;

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
    if (usuario == null) return;

    actualizarUsuario();
    HashMap handlers = generarHandlersParaModificar();

    ModificarPerfilTask tarea = new ModificarPerfilTask(usuario, this, handlers);
    tarea.execute();
  }

  @Override
  public View getEscondible() {
    return findViewById(R.id.perfil_layout);
  }

  @Override
  public ProgressBar getProgressBar() {
    return (ProgressBar) findViewById(R.id.perfil_progress);
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  private void actualizarUsuario() {
    usuario.setEmail(email.getText().toString());
    usuario.setNombre(nombre.getText().toString());
    usuario.setApellido(apellido.getText().toString());
    usuario.setIdioma(idioma.getSelectedItemPosition());
  }

  private HashMap generarHandlersParaModificar() {
    HashMap<Integer, BackendResponseHandler> handlers = new HashMap<>();

    handlers.put(200, new ModificarPerfilSuccessHandler(this));

    return handlers;
  }

}
