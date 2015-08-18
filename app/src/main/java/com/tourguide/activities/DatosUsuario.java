package com.tourguide.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tourguide.R;

public class DatosUsuario extends Activity {

  public Spinner spinner;
  public EditText nombre;
  public EditText apellido;
  public EditText correo;
  public Button guardar;
  private Configuration conf;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_datos_usuario);

    nombre = (EditText) findViewById(R.id.nombre);
    apellido = (EditText) findViewById(R.id.apellido);
    correo = (EditText) findViewById(R.id.correo);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_datos_usuario, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

}
