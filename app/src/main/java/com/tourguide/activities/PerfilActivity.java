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
import com.tourguide.handlers.ObtenerPerfilErrorHandler;
import com.tourguide.handlers.ObtenerPerfilSuccessHandler;
import com.tourguide.support.Constants;
import com.tourguide.tasks.ModificarPerfilTask;

import java.util.HashMap;
import java.util.Map;

public class PerfilActivity extends ProgressActivity {

  private EditText nombreInput;
  private EditText apellidoInput;
  private EditText emailInput;
  private EditText contraseñaInput;
  private EditText contraseñaConfirmationInput;
  private Spinner  idiomaInput;

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
    new ObtenerPerfilSuccessHandler(this).handle();
  }

  /**
   * Guarda los datos del perfil del usuario en el backend web.
   *
   * @param item
   */
  public void onGuardarItemClick(MenuItem item) {
    if (Constants.getUsuario() == null) return;

    actualizarUsuario();
    Map handlers = generarHandlersParaModificar();

    ModificarPerfilTask tarea = new ModificarPerfilTask(Constants.getUsuario(), this, handlers);
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
    Constants.getUsuario().setEmail(getEmailInput().getText().toString());
    Constants.getUsuario().setNombre(getNombreInput().getText().toString());
    Constants.getUsuario().setApellido(getApellidoInput().getText().toString());
    Constants.getUsuario().setIdioma(getIdiomaInput().getSelectedItemPosition());
  }

  private Map generarHandlersParaObtener() {
    Map<Boolean, BackendResponseHandler> handlers = new HashMap<>();

    handlers.put(true, new ObtenerPerfilSuccessHandler(this));
    handlers.put(false, new ObtenerPerfilErrorHandler(this));

    return handlers;
  }

  private Map generarHandlersParaModificar() {
    Map<Integer, BackendResponseHandler> handlers = new HashMap<>();

    handlers.put(200, new ModificarPerfilSuccessHandler(this));
    handlers.put(422, new ModificarPerfilUnprocessableHandler(this));

    return handlers;
  }

}
