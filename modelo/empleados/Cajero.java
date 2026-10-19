package com.example.modelo.empleados;

public class Cajero extends Empleado {
    private int ventasRealizadas;

    public Cajero(String nombre, int idEmpleado, double salarioBase, int ventasRealizadas) {
        super(nombre, idEmpleado, salarioBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    //Revisar metodo
    @Override
    public double calcularSalario() {

        double comision = ventasRealizadas * 100;

        return salarioBase + comision;
    }
        public int getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(int ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }
}
