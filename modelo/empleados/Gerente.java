package com.example.modelo.empleados;

public class Gerente extends Empleado {
    private double bonoGerencial;

    public Gerente(String nombre, int idEmpleado, double salarioBase, double bonoGerencial) {
        super(nombre, idEmpleado, salarioBase);
        this.bonoGerencial = bonoGerencial;
    }


    //Rebisar metodo
    @Override
    public double calcularSalario() {
        return salarioBase + bonoGerencial;
    }
}
