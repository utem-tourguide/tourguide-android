package mx.edu.integadora3.utem.tourguide.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import mx.edu.integadora3.utem.tourguide.R;


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tgdatos_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public class DatosUsuario{
        private String nombre;
        private String apellido;
        private String correo;

        public DatosUsuario(String nombre, String apellido, String correo){
            this.nombre = nombre;
            this.apellido = apellido;
            this.correo = correo;
        }

        public String getNombre(){
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido(){
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getCorreo(){
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
    }
}
