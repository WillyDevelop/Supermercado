package com.example.modelo.productos;

import java.util.UUID;

public abstract class Producto {


    private static int totalProductos = 0;
    private String nombre;
    private UUID codigo; // Revisar funcionamiento del ID
    private double precio;
    private String categoria;

    // Constructores
    public Producto(String nombre, Integer precio, String categoria) {
        this.codigo = UUID.randomUUID();
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        totalProductos++;

    }

    public static int getTotalProductos() {
        return totalProductos;
    }

//    public static void setTotalProductos(int totalProductos) {
//        Producto.totalProductos = totalProductos;
//    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UUID getCodigo() {
        return codigo;
    }

//    public void setCodigo(UUID codigo) {
//        this.codigo = codigo;
//    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public abstract double calcularPrecioFinal();
    public abstract String mostrarInformacion();


}