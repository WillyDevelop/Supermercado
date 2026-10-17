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

    //no se porq no habia setters asi q los agregue
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
}
