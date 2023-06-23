package com.example.restaurante.Categorias.Usuarios;

public class Usuario_tipo {
    private int id;
    private String nombre;

    public Usuario_tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario_tipo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
