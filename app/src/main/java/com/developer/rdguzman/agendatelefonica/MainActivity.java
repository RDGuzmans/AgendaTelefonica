package com.developer.rdguzman.agendatelefonica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button agregar;
    ListView listaContacto;

    ArrayList<String> listaNombre;
    ArrayList<String> listaNumero;

    ArrayList<Datos> Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar=(Button) findViewById(R.id.btnAgregar);
        listaContacto = (ListView) findViewById(R.id.listView) ;

        Lista = new ArrayList<Datos>();

        DB db = new DB(getApplicationContext(),null,null,1);
        listaNombre = db.llenar_NombreCompleto();
        listaNumero = db.llenar_Numero();

        for (int x=0;x<listaNombre.size();x++){
            Lista.add(new Datos(x,listaNombre.get(x), listaNumero.get(x), R.drawable.user_icon));
        }

        Adaptador miadaptador = new Adaptador(getApplicationContext(), Lista);
        listaContacto.setAdapter(miadaptador);

        listaContacto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos obj =(Datos) parent.getItemAtPosition(position);
                Intent paso = new Intent(getApplicationContext(),PerfilActivity.class);
                paso.putExtra("objeto",(Serializable)obj);
                startActivity(paso);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AgregarActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }
}
