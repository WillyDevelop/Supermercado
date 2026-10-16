package com.example.modelo.productos;
import com.example.interfaces.Promocionable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ProductoAlimenticio extends Producto implements Promocionable {

    private LocalDate fechaCaducidad;
    private boolean esPerecedero;

    public ProductoAlimenticio(String nombre, Integer precio, String categoria, LocalDate fechaCaducicad, boolean esPerecedero) {
        super(nombre, precio, categoria);
        this.fechaCaducidad = fechaCaducicad;
        this.esPerecedero = esPerecedero;
    }


    // Getter y setter por las dudas
    public LocalDate getFechaCaducicad() {
        return fechaCaducidad;
    }

    public void setFechaCaducicad(LocalDate fechaCaducicad) {
        this.fechaCaducidad = fechaCaducicad;
    }

    public boolean isEsPerecedero() {
        return esPerecedero;
    }

    public void setEsPerecedero(boolean esPerecedero) {
        this.esPerecedero = esPerecedero;
    }

    @Override //Completar logica
    public double calcularPrecioFinal() {
        double precioBase = this.getPrecio();

        // ChronoUnit.DAYS.between
        // funcion que calcula los días restantes entre hoy y la fecha de caducidad
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), this.fechaCaducidad);

        if (diasRestantes >= 0 && diasRestantes < 5){
            return precioBase * 0.70;
        }

        return precioBase;

    }

    @Override
    public String mostrarInformacion() {
        return String.format("[%s] %s | Código: %s | Base: $%.2f | Final: $%.2f | Vence: %s | Perecedero: %s",
                getCategoria(),
                getNombre(),
                getCodigo(),
                (double) getPrecio(),
                calcularPrecioFinal(),
                fechaCaducidad,
                esPerecedero ? "Sí" : "No"
        );
    }

    // Interfaz
    @Override
    public double aplicarDescuento(double porcentaje) {
        double descuento = this.getPrecio() * (porcentaje / 100.0);
        double nuevoPrecio = this.getPrecio() - descuento;
        this.setPrecio((int) nuevoPrecio);
        return nuevoPrecio;
    }
    @Override
    public double aplicarPromocion(String tipoPromocion) {
        if (tipoPromocion.equalsIgnoreCase("2x1")) {
            return aplicarDescuento(50); //
        } else if (tipoPromocion.equalsIgnoreCase("2da unidad")) {
            return aplicarDescuento(25);
        }
        return this.getPrecio();
    }
}
