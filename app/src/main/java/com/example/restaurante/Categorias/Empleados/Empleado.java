package com.example.restaurante.Categorias.Empleados;

public class Empleado {
    private int id_empleado;
    private int id_empleado_tipo;
    private int id_usuario;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String descripcion;
    private String dni;

    public Empleado(int id_empleado, int id_empleado_tipo, int id_usuario, String nombre, String apellido, String fecha_nacimiento, String descripcion, String dni) {
        this.id_empleado = id_empleado;
        this.id_empleado_tipo = id_empleado_tipo;
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.descripcion = descripcion;
        this.dni = dni;
    }

    public Empleado(String nombre, String apellido, String descripcion, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.dni = dni;
    }

    public Empleado() {
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_empleado_tipo() {
        return id_empleado_tipo;
    }

    public void setId_empleado_tipo(int id_empleado_tipo) {
        this.id_empleado_tipo = id_empleado_tipo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


}
