package com.developer.rdguzman.agendatelefonica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Guzman on 08/04/2017.
 */

public class Adaptador extends BaseAdapter {

    Context contexto;
    List<Datos> ListaObjetos;

    public Adaptador(Context contexto, List<Datos> listaObjetos) {
        this.contexto = contexto;
        ListaObjetos = listaObjetos;
    }

    @Override
    public int getCount() {
        return ListaObjetos.size();//Retornar la cantidad de elementos de la lista
    }

    @Override
    public Object getItem(int position) {
        return ListaObjetos.get(position);//Retorna el objeto de la posicion indicada
    }

    @Override
    public long getItemId(int position) {
        return ListaObjetos.get(position).getId();//Retornamos el id de la posicion indicada
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista=inflate.inflate(R.layout.itemlistview,null);

        ImageView imagen = (ImageView) vista.findViewById(R.id.ImageView);
        TextView titulo = (TextView) vista.findViewById(R.id.txtTitulo);
        TextView detalle = (TextView) vista.findViewById(R.id.txtDetalle);

        titulo.setText(ListaObjetos.get(position).getTitulo().toString());
        detalle.setText(ListaObjetos.get(position).getDetalle().toString());
        imagen.setImageResource(ListaObjetos.get(position).getImagen());

        return vista;

    }
}
