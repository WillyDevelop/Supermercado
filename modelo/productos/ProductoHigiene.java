package com.example.modelo.productos;

import com.example.interfaces.ImpuestoAplicable;
import com.example.interfaces.Promocionable;


public class ProductoHigiene extends Producto implements Promocionable, ImpuestoAplicable {

    private TipoDeUso tipoDeUso;

    public ProductoHigiene(String nombre, Integer precio, String categoria, TipoDeUso tipoDeUso) {
        super(nombre, precio, categoria);
        this.tipoDeUso = tipoDeUso;
    }

    //get y set


    public TipoDeUso getTipoDeUso() {
        return tipoDeUso;
    }

    public void setTipoDeUso(TipoDeUso tipoDeUso) {
        this.tipoDeUso = tipoDeUso;
    }

    @Override //Completar Logica
    public double calcularPrecioFinal() {

        double impuesto = calcularImpuesto(10.0); // 10% de impuesto específico
        return this.getPrecio() + impuesto;
    }

    @Override
    public String mostrarInformacion() {
        return String.format("[%s] %s | Código: %s | Base: $%.2f | Final: $%.2f | Tipo de uso: %s",
                getCategoria(),
                getNombre(),
                getCodigo(),
                (double) getPrecio(),
                calcularPrecioFinal(),
                tipoDeUso

        );
    }
    //Interfaces

    @Override
    public double calcularImpuesto(double porcentaje) {
        return this.getPrecio() * (porcentaje / 100.0);
    }

    @Override
    public double aplicarDescuento(double porcentaje) {
        double descuento = this.getPrecio() * (porcentaje / 100);
        double nuevoPrecio = this.getPrecio() - descuento;

        this.setPrecio(nuevoPrecio);

        return nuevoPrecio;
    }

    @Override
    public double aplicarPromocion(String tipoPromocion) {
        if (tipoPromocion.equalsIgnoreCase("2x1")) {
            return aplicarDescuento(50);
        } else if (tipoPromocion.equalsIgnoreCase("2da unidad")) {
            return aplicarDescuento(35);
        }
        return this.getPrecio();
    }
}


