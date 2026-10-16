package com.example.modelo.clientes;

import com.example.modelo.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private int dni;
    private List<Producto> compras;
    public static int totalclientes=0;

    // Constructor
    public Cliente(String nombre, int dni,int totalclientes) {
        this.nombre = nombre;
        this.dni = dni;
        this.compras = new ArrayList<>();
        Cliente.totalclientes++;
    }

    // Seters y Geters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public static int getTotalclientes() {
        return totalclientes;
    }

    public static void setTotalclientes(int totalclientes) {
        Cliente.totalclientes = totalclientes;
    }

    // Metodos
    public void comprar(Producto producto) {
        if (producto != null) {
            this.compras.add(producto);
        }
    }

    public void mostrarCompras() {
        if (compras.isEmpty()) {
            System.out.println("El cliente " + nombre + " no tiene compras registradas.");
        } else {
            System.out.println("Compras de " + nombre + ":");
            for (Producto p : compras) {
                System.out.println("- " + p.mostrarInformacion());
            }
        }
    }
    }

