package com.example.carlos_rueda;


import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

import javax.annotation.PropertyKey;

public class Producto implements Serializable {

    private String nombre;
    private Double precio;
    private String Url;

    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Producto(){}
    public Producto(String nombre, Double precio, String Url){
        this.nombre=nombre;
        this.precio=precio;
        this.Url=Url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    @PropertyName("url_image")
    public String getUrl() {
        return Url;
    }
    @PropertyName("url_image")
    public void setUrl(String url) {
        Url = url;
    }

}


