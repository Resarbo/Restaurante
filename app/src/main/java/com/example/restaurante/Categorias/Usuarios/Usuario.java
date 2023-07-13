package com.example.restaurante.Categorias.Usuarios;

public class Usuario {
    private String correo;
    private String contrasena;
    private int usuario_tipo;
    private int id_usuario;
    private String nombre_usuario_tipo;

    public Usuario(int usuario_tipo, int id_usuario, String correo, String contrasena, String nombre_usuario_tipo) {
        this.id_usuario = id_usuario;
        this.usuario_tipo = usuario_tipo;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre_usuario_tipo = nombre_usuario_tipo;
    }

    public Usuario() {
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

    public int getUsuario_tipo() {
        return usuario_tipo;
    }

    public void setUsuario_tipo(int usuario_tipo) {
        this.usuario_tipo = usuario_tipo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario_tipo() {
        return nombre_usuario_tipo;
    }

    public void setNombre_usuario_tipo(String nombre_usuario_tipo) {
        this.nombre_usuario_tipo = nombre_usuario_tipo;
    }
}
