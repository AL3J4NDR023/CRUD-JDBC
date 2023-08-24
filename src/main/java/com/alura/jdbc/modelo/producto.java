package com.alura.jdbc.modelo;

public class producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    public producto(String nombre, String descripcion, Integer cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public producto(int id, String nombre, String descripcion, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return String.format("{id: %s, nombre: %s, descripcion, %s, cantidad: %d}",
                this.id, this.nombre, this.descripcion, this.cantidad);
    }

}