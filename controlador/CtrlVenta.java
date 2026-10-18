package com.example.controlador;

import com.example.excepciones.ClienteNoRegistradoException;
import com.example.excepciones.ProductoNoDisponibleException;
import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.productos.Producto;
import com.example.service.SupermercadoService;
import com.example.vista.FrmVenta;
import java.util.ArrayList;
import java.util.List;

public class CtrlVenta {

    private SupermercadoService servicio;
    private List<Producto> carrito;

    public CtrlVenta() {
        this.servicio = SupermercadoService.getInstancia();
        this.carrito = new ArrayList<>();
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
            double precioFinalProducto = p.calcularPrecioFinal();

            // Si el cliente es VIP, aplicamos la estimación de descuento para visualizar en pantalla
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

        // Ejecuta la compra en el servicio (descuenta stock en memoria y asigna compras al cliente)
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
