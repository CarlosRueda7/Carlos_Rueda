package com.example.carlos_rueda;


import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private Double precio;
    private String Url;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}


