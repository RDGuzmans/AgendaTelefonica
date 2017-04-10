package com.developer.rdguzman.agendatelefonica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarActivity extends AppCompatActivity {
    ArrayList<String>   datos;
    String      nombreS,apellidoPS,apellidoMS,telefonoS,correoS,direccionS,numero,tipoS;
    TextView    nombre,apellidoP,apellidoM,telefono,correo,direccion;
    Spinner     tipo;
    String      NUMERO;
    Button      btnGuardarCambios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nombre = (TextView) findViewById(R.id.txtNombreEd);
        apellidoP = (TextView) findViewById(R.id.txtApellidoPEd);
        apellidoM = (TextView) findViewById(R.id.txtApellidoMEd);
        telefono = (TextView) findViewById(R.id.txtNumeroEd);
        tipo = (Spinner) findViewById(R.id.spinTelEdit);
        correo = (TextView) findViewById(R.id.txtCorreoEd);
        direccion = (TextView) findViewById(R.id.txtDireccionEd);

        btnGuardarCambios =(Button) findViewById(R.id.btnGuardarCambios);

        //Datos obj=(Datos) getIntent().getExtras().getSerializable("objeto");

        DB db = new DB(getApplicationContext(),null,null,1);

        NUMERO=getIntent().getExtras().getString("parametro");

        datos=db.llenar_Datos(NUMERO);
        nombre.setText(datos.get(0));
        apellidoP.setText(datos.get(1));
        apellidoM.setText(datos.get(2));
        telefono.setText(datos.get(3));
        correo.setText(datos.get(5));
        direccion.setText(datos.get(6));


        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreS = nombre.getText().toString();
                apellidoPS = apellidoP.getText().toString();
                apellidoMS = apellidoM.getText().toString();
                tipoS=tipo.getSelectedItem().toString();
                telefonoS = telefono.getText().toString();
                correoS=correo.getText().toString();
                direccionS=direccion.getText().toString();

                if(nombreS.length()==0 || apellidoPS.length()==0 || apellidoMS.length()==0 || telefonoS.length()==0) {
                    Toast.makeText(EditarActivity.this, "Ingrese Datos Para Continuar", Toast.LENGTH_SHORT).show();
                }else{

                    DB db = new DB(getApplicationContext(),null,null,1);
                    String mensaje = db.actualizar(NUMERO,telefonoS,nombreS,apellidoPS,apellidoMS,tipoS,correoS,direccionS);
                    Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });



    }
}
