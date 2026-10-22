package com.example.modelo.empleados;

public abstract class Empleado {
    protected String nombre;
    protected int idEmpleado;
    protected double salarioBase;

    public Empleado(String nombre, int idEmpleado, double salarioBase) {
        this.nombre = nombre;
        this.idEmpleado = idEmpleado;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();


    public String getNombre() {
        return nombre;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}
