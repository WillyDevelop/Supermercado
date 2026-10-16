package com.example.modelo.productos;

import com.example.interfaces.ImpuestoAplicable;
import com.example.interfaces.Promocionable;

import java.util.UUID;

public class ProductoElectronico extends Producto implements Promocionable, ImpuestoAplicable {

    private int garantia;

//    public ProductoElectronico(String nombre, Integer codigo, Integer precio, String categoria) {
//        super(nombre, codigo, precio, categoria);
//    }

    public ProductoElectronico(String nombre, Integer precio, String categoria, int garantia) {
        super(nombre, precio, categoria);
        this.garantia = garantia;
    }
    //get y set


    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    @Override// Completar logica
    public double calcularPrecioFinal() {
        double precioBase =  this.getPrecio();

        if (this.garantia > 2){
            return precioBase * 1.05;
        }

        return precioBase;
    }

    @Override
    public String mostrarInformacion() {
        return String.format("[%s] %s | Código: %s | Base: $%.2f | Final: $%.2f | Garantia: %s",
                getCategoria(),
                getNombre(),
                getCodigo(),
                (double) getPrecio(),
                calcularPrecioFinal(),
                garantia + (garantia == 1 ? " año" : " años")

        );
    }

    //METODOS DE INTERFACES

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
            return aplicarDescuento(25);
        }
        return this.getPrecio();
    }

    @Override
    public double calcularImpuesto(double porcentaje) {
        return this.getPrecio() * (porcentaje / 100);
    }
}
