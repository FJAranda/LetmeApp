package com.example.letmeapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Item implements Comparable, Serializable {
    public static final String TAG = "item";
    //FIRESTORE COLLECTION NAMES
    public static final String ITEMS_FIRESTORE = "items";
    public static final String ITEM_OWNER = "owner";

    @PrimaryKey(autoGenerate = true)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    private String nombre;
    private String srcImagen;
    @NonNull
    private String descripcion;
    @NonNull
    private String tipo;
    @NonNull
    private String estado;
    @NonNull
    private String owner;

    @NonNull
    public String getOwner() {
        return owner;
    }

    public void setOwner(@NonNull String owner) {
        this.owner = owner;
    }

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

    public Item(int id, String owner, @NonNull String nombre, String srcImagen, @NonNull String descripcion, @NonNull String tipo, @NonNull String estado) {
        this.id = id;
        this.owner = owner;
        this.nombre = nombre;
        this.srcImagen = srcImagen;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.estado = estado;
    }

    @Ignore
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
