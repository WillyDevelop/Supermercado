package com.example.modelo.clientes;

import com.example.modelo.productos.Producto;

public class ClienteVIP extends Cliente {
    private final int descuentoVIP;

    // Constructor
    public ClienteVIP(String nombre, int dni, int totalclientes, int descuentoVIP) {
        super(nombre, dni, totalclientes);
        this.descuentoVIP = descuentoVIP;
    }

    // Metodos
    public void aplicarDescuentoVIP(Producto producto) {
        if (producto != null) {
            // Calculamos el descuento usando el porcentaje de la variable descuentoVIP
            double descuento = producto.getPrecio() * (this.descuentoVIP / 100.0);
            double nuevoPrecio = producto.getPrecio() - descuento;

            // Actualizamos el precio del producto
            producto.setPrecio(nuevoPrecio);
            System.out.println("Se aplicó un descuento VIP del " + this.descuentoVIP + "% al producto " + producto.getNombre());
        }
    }
    public void comprar(Producto producto) {
        // Aplicar el descuento del VIP
        aplicarDescuentoVIP(producto);
        super.comprar(producto);
    }
}
