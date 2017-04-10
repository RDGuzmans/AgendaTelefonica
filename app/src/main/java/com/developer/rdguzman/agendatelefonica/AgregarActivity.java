package com.developer.rdguzman.agendatelefonica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarActivity extends AppCompatActivity {

    Button      guardar;
    EditText    nombre,apellidoP,apellidoM,numero,correo,direccion;
    Spinner     tipoTel;
    String      Nombre,ApellidoP,ApellidoM,TipoTel,Numero,Corrreo,Direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        guardar = (Button) findViewById(R.id.btnGuardar);
        nombre = (EditText) findViewById(R.id.txtNombre);
        apellidoP = (EditText) findViewById(R.id.txtApellidoP);
        apellidoM = (EditText) findViewById(R.id.txtApellidoM);
        numero = (EditText) findViewById(R.id.txtNumero);
        correo = (EditText) findViewById(R.id.txtCorreo);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        tipoTel = (Spinner) findViewById(R.id.spinTel);

       guardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Nombre=nombre.getText().toString();
               ApellidoP=apellidoP.getText().toString();
               ApellidoM=apellidoM.getText().toString();
               Numero=numero.getText().toString();
               TipoTel=tipoTel.getSelectedItem().toString();
               Corrreo=correo.getText().toString();
               Direccion=direccion.getText().toString();

               if(Nombre.length()==0 || ApellidoP.length()==0 || ApellidoM.length()==0 || Numero.length()==0){
                   Toast.makeText(AgregarActivity.this, "Ingrese Datos Para Continuar", Toast.LENGTH_SHORT).show();
               }else{
                   DB db = new DB(getApplicationContext(),null,null,1);
                   String mensaje = db.guardar(Nombre,ApellidoP,ApellidoM,Numero,TipoTel,Corrreo,Direccion);
                   Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                   finish();
               }
           }
       });
    }
}
