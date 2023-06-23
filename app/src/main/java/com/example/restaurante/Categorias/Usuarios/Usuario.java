package com.example.restaurante.Categorias.Usuarios;

public class Usuario {
    private int id;
    private int id_usuario;
    private String correo;
    private String contrasena;

    public Usuario(int id, int id_usuario, String correo, String contrasena) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
