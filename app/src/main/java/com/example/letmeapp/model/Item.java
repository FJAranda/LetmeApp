package com.example.letmeapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Comparable, Serializable {
    //private int itemCode;
    private String nombre;
    private String srcImagen;
    private String descripcion;
    private String tipo;
    private String estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSrcImagen() {
        return srcImagen;
    }

    public void setSrcImagen(String srcImagen) {
        this.srcImagen = srcImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Item(String nombre, String srcImagen, String descripcion, String tipo, String estado) {
        this.nombre = nombre;
        this.srcImagen = srcImagen;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        return ((Item)o).getNombre().equals(getNombre());
    }

    @Override
    public int compareTo(Object o) {
        if (((Item)o).getNombre().equals(getNombre())){
            return ((Item)o).getDescripcion().compareTo(getDescripcion());
        }else{
            return ((Item)o).getNombre().compareTo(getNombre());
        }
    }
}
