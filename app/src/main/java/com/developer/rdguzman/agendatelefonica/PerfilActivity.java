package com.developer.rdguzman.agendatelefonica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {
    ArrayList<String> datos;


    TextView nombre,apellidoP,apellidoM,telefono,tipo,correo,direccion;
    Button editar,borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre = (TextView) findViewById(R.id.lblNombrePe);
        apellidoP=(TextView) findViewById(R.id.lblApellidoPPE);
        apellidoM=(TextView) findViewById(R.id.lblApellidoMPe);
        telefono=(TextView) findViewById(R.id.lblTelefonoPE);
        tipo=(TextView) findViewById(R.id.lblTipoPe);
        correo=(TextView) findViewById(R.id.lblCorreoPe);
        direccion=(TextView) findViewById(R.id.lblDireccionPe);

        editar = (Button) findViewById(R.id.btnEditar);
        borrar = (Button) findViewById(R.id.btnBorrar);

        Datos obj=(Datos) getIntent().getExtras().getSerializable("objeto");

        DB db = new DB(getApplicationContext(),null,null,1);

        datos=db.llenar_Datos(obj.getDetalle());
        nombre.setText(datos.get(0));
        apellidoP.setText(datos.get(1));
        apellidoM.setText(datos.get(2));
        telefono.setText(datos.get(3));
        tipo.setText(datos.get(4));
        correo.setText(datos.get(5));
        direccion.setText(datos.get(6));

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditarActivity.class);
                intent.putExtra("parametro",telefono.getText().toString());
                startActivity(intent);
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db = new DB(getApplicationContext(),null,null,1);
                String numero = telefono.getText().toString();
                String mensaje = db.eliminar(numero);
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
