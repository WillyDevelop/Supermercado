package com.example.controlador;

import com.example.excepciones.ClienteNoRegistradoException;
import com.example.excepciones.ProductoNoDisponibleException;
import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.productos.Producto;
import com.example.modelo.productos.ProductoAlimenticio;
import com.example.modelo.productos.ProductoElectronico;
import com.example.modelo.productos.ProductoHigiene;
import com.example.modelo.productos.TipoDeUso;
import com.example.service.SupermercadoService;
import com.example.vista.FrmVenta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CtrlVenta {

    private SupermercadoService servicio;
    private List<Producto> carrito;

    public CtrlVenta() {
        this.servicio = SupermercadoService.getInstancia();// aplico patron singleton
        this.carrito = new ArrayList<>();

        // creo productos si no hay - para pruebas
        if (this.servicio.getProductosDisponibles().isEmpty()) {
            this.servicio.agregarProducto(new ProductoAlimenticio(
                    "Arroz Gallo Oro", 1500, "Alimenticio", LocalDate.now().plusDays(30), true, 20));
            this.servicio.agregarProducto(new ProductoAlimenticio(
                    "Lentejas", 1000, "Alimenticio", LocalDate.now().plusDays(45), true, 20));
            this.servicio.agregarProducto(new ProductoElectronico(
                    "Auriculares Bluetooth", 25000, "Electrónico", 1, 15));
            this.servicio.agregarProducto(new ProductoElectronico(
                    "Monitor 24 pulgadas", 185000, "Electrónico", 2, 25));
            this.servicio.agregarProducto(new ProductoHigiene(
                    "Shampoo Anticaspa", 3500, "Higiene", TipoDeUso.PERSONAL, 10));
            this.servicio.agregarProducto(new ProductoHigiene(
                    "Detergente Líquido", 2800, "Higiene", TipoDeUso.DOMESTICO, 11));
        }
        if (this.servicio.getClientes().isEmpty()) {
            this.servicio.agregarCliente(new Cliente(
                    "Juan Pérez", 35111222, Cliente.getTotalclientes()));
            this.servicio.agregarCliente(new ClienteVIP(
                    "Ana Gómez", 38333444, Cliente.getTotalclientes(), 15));
        }
    }

    // --- Consultas al Servicio para alimentar la Vista ---

    public List<Cliente> obtenerClientes() {
        return servicio.getClientes();
    }

    public List<Producto> obtenerProductosDisponibles() {
        return servicio.getProductosDisponibles();
    }

    public List<Producto> obtenerCarrito() {
        return carrito;
    }

    // --- Gestión del Carrito de Compras ---

    public boolean agregarAlCarrito(Producto producto) {
        if (producto != null) {
            return carrito.add(producto);
        }
        return false;
    }

    public boolean eliminarDelCarrito(int indice) {
        if (indice >= 0 && indice < carrito.size()) {
            carrito.remove(indice);
            return true;
        }
        return false;
    }

    public void vaciarCarrito() {
        carrito.clear();
    }

    // --- Cálculos de Montos de la Venta ---

    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (Producto p : carrito) {
            subtotal += p.getPrecio();
        }
        return subtotal;
    }

    public double calcularTotal(Cliente cliente) {
        double total = 0.0;
        for (Producto p : carrito) {
            double precioFinalProducto = p.calcularPrecioFinal();// polimorfismo

            // Si el cliente es VIP, aplicamos la estimación de descuento para visualizar en
            // pantalla
            if (cliente instanceof ClienteVIP) {
                precioFinalProducto = precioFinalProducto * 0.85; // 15% desc. estimado
            }
            total += precioFinalProducto;
        }
        return total;
    }

    // --- Transacción: Procesar y Finalizar Venta ---

    public void finalizarVenta(Cliente cliente)
            throws ClienteNoRegistradoException, ProductoNoDisponibleException, IllegalStateException {

        if (cliente == null) {
            throw new ClienteNoRegistradoException("Debe seleccionar un cliente para realizar la venta.");
        }
        if (carrito.isEmpty()) {
            throw new IllegalStateException("El carrito de compras está vacío. Agregue productos antes de cobrar.");
        }

        // Ejecuta la compra en el servicio (descuenta stock en memoria y asigna compras
        // al cliente)
        servicio.registrarCompra(cliente, carrito);

        // Si la compra fue exitosa, vaciamos el carrito temporal
        vaciarCarrito();
    }

    // --- Navegación ---

    public void volverAlMenu(FrmVenta vistaVenta) {
        if (vistaVenta != null) {
            vistaVenta.dispose();
        }
    }
}
