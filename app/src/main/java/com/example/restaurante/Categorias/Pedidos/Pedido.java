package com.example.restaurante.Categorias.Pedidos;

public class Pedido {
    private int id_pedido;
    private String nombrePlato;
    private int cantidad;
    private int estado;

    public Pedido(int id_pedido, String nombrePlato, int cantidad, int estado) {
        this.id_pedido = id_pedido;
        this.nombrePlato = nombrePlato;
        this.cantidad = cantidad;
        this.estado = estado;
    }


    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
