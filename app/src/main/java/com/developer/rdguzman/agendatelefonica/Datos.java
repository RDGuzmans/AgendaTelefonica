package com.developer.rdguzman.agendatelefonica;

import java.io.Serializable;

/**
 * Created by Guzman on 08/04/2017.
 */

public class Datos implements Serializable {

    private int Id;
    private String Titulo;
    private String Detalle;
    private  int Imagen;

    public Datos(int id, String titulo, String detalle, int imagen) {
        Id = id;
        Titulo = titulo;
        Detalle = detalle;
        Imagen = imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
