package com.example.menu_diario.entidades;

public class Menues {
    private int id;
    private String tipo_menu;
    private String detalle;
    private String preparacion;
    private String compras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_menu() {
        return tipo_menu;
    }

    public void setTipo_menu(String tipo_menu) {
        this.tipo_menu = tipo_menu;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getCompras() {
        return compras;
    }

    public void setCompras(String compras) {
        this.compras = compras;
    }

}
