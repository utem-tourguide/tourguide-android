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
import com.tourguide.handlers.ModificarPerfilUnprocessableHandler;
import com.tourguide.models.Usuario;
import com.tourguide.tasks.ModificarPerfilTask;
import com.tourguide.tasks.ObtenerPerfilTask;

import java.util.HashMap;

public class PerfilActivity extends ProgressActivity {

  private static final int USUARIO_ID = 10;

  private EditText nombreInput;
  private EditText apellidoInput;
  private EditText emailInput;
  private EditText contraseñaInput;
  private EditText contraseñaConfirmationInput;
  private Spinner  idiomaInput;

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
    nombreInput = (EditText) findViewById(R.id.nombre);
    apellidoInput = (EditText) findViewById(R.id.apellido);
    emailInput = (EditText) findViewById(R.id.email);
    contraseñaInput = (EditText) findViewById(R.id.contrasena);
    contraseñaConfirmationInput = (EditText) findViewById(R.id.contrasena_confirmation);
    idiomaInput = (Spinner) findViewById(R.id.idioma);
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

  public EditText getNombreInput() {
    return nombreInput;
  }

  public EditText getApellidoInput() {
    return apellidoInput;
  }

  public EditText getEmailInput() {
    return emailInput;
  }

  public EditText getContraseñaInput() {
    return contraseñaInput;
  }

  public EditText getContraseñaConfirmationInput() {
    return contraseñaConfirmationInput;
  }

  public Spinner getIdiomaInput() {
    return idiomaInput;
  }

  private void actualizarUsuario() {
    usuario.setEmail(getEmailInput().getText().toString());
    usuario.setNombre(getNombreInput().getText().toString());
    usuario.setApellido(getApellidoInput().getText().toString());
    usuario.setIdioma(getIdiomaInput().getSelectedItemPosition());
  }

  private HashMap generarHandlersParaModificar() {
    HashMap<Integer, BackendResponseHandler> handlers = new HashMap<>();

    handlers.put(200, new ModificarPerfilSuccessHandler(this));
    handlers.put(422, new ModificarPerfilUnprocessableHandler(this));

    return handlers;
  }

}
