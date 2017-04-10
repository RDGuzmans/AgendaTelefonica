package com.developer.rdguzman.agendatelefonica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Guzman on 06/04/2017.
 */

public class DB extends SQLiteOpenHelper {
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "ContactoBD", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacto(nombre text, apellidoP text, apellidoM text, numero text, tipo text, email text, direccion text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String guardar(String nombre,String apellidoP,String apellidoM,String numero,String tipoTel,String correo,String direccion){
        String mensaje="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",nombre);
        contenedor.put("apellidoP",apellidoP);
        contenedor.put("apellidoM",apellidoM);
        contenedor.put("numero",numero);
        contenedor.put("tipo",tipoTel);
        contenedor.put("email",correo);
        contenedor.put("direccion",direccion);
        try {
            database.insertOrThrow("contacto",null,contenedor);
            mensaje="Contacto Ingresado Correctamente";
        }catch (android.database.SQLException e){
            mensaje="Contacto No Ingresado";
        }
        this.close();
        return  mensaje;
    }
    public ArrayList llenar_NombreCompleto(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT nombre,apellidoP,apellidoM FROM contacto";
        Cursor registro = database.rawQuery(q,null);
        if(registro.moveToFirst()){
            do{
                lista.add(registro.getString(0)+" "+registro.getString(1)+" "+registro.getString(2));
            }while (registro.moveToNext());
        }
        this.close();
        return lista;
    }

    public ArrayList llenar_Numero(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT numero FROM contacto";
        Cursor registro = database.rawQuery(q,null);
        if(registro.moveToFirst()){
            do{
                lista.add(registro.getString(0));
            }while (registro.moveToNext());
        }
        this.close();
        return lista;
    }


    public ArrayList llenar_Datos(String telefono){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM contacto WHERE numero = '"+telefono+"' ";
        Cursor registro = database.rawQuery(q,null);
        if(registro.moveToFirst()){
            do{
                lista.add(registro.getString(0));
                lista.add(registro.getString(1));
                lista.add(registro.getString(2));
                lista.add(registro.getString(3));
                lista.add(registro.getString(4));
                lista.add(registro.getString(5));
                lista.add(registro.getString(6));
            }while (registro.moveToNext());
        }
        this.close();
        return lista;
    }

    public String actualizar(String numeroAntiguo,String numero, String nombre, String apellidoP,String apellidoM, String tipo, String correo, String direccion){
        String mensaje=null;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",nombre);
        contenedor.put("apellidoP",apellidoP);
        contenedor.put("apellidoM",apellidoM);
        contenedor.put("numero",numero);
        contenedor.put("tipo",tipo);
        contenedor.put("email",correo);
        contenedor.put("direccion",direccion);
        int cantidad= database.update("contacto",contenedor,"numero='"+numeroAntiguo+"'",null);

        if(cantidad !=0){
            mensaje = "Contacto Actualizado Corecctamente";
        }else {
            mensaje="Contacto No Actualizado";
        }

        return mensaje;
    }

    public String eliminar(String numero){
        String mensaje=null;
        SQLiteDatabase database = this.getWritableDatabase();
        int cantidad = database.delete("contacto","numero='"+numero+"'",null);
        if(cantidad !=0){
            mensaje="Contacto Eliminado Correctamente";
        }else{
            mensaje="Contacto No Eliminado";
        }
        return mensaje;
    }

}
