package com.example;

import com.example.excepciones.ClienteNoRegistradoException;
import com.example.excepciones.ProductoNoDisponibleException;
import com.example.service.SupermercadoService;
import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.empleados.Cajero;
import com.example.modelo.empleados.Gerente;
import com.example.modelo.productos.Producto;
import com.example.modelo.productos.ProductoAlimenticio;
import com.example.modelo.productos.ProductoElectronico;
import com.example.modelo.productos.ProductoHigiene;
import com.example.modelo.productos.TipoDeUso;
import com.example.vista.FrmPrincipal;
import com.example.vista.FrmVenta;

import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
         // 1. Instanciamos el servicio principal y cargamos los datos de prueba
        SupermercadoService supermercado = new SupermercadoService();
        // --- Carga de Productos ---
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Arroz Gallo Oro", 1500, "Alimenticio", LocalDate.now().plusDays(30), true));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Lentejas", 1000, "Alimenticio", LocalDate.now().plusDays(45), true));
        supermercado.agregarProducto(new ProductoElectronico(
                "Auriculares Bluetooth", 25000, "Electrónico", 1));
        supermercado.agregarProducto(new ProductoElectronico(
                "Monitor 24 pulgadas", 185000, "Electrónico", 2));
        supermercado.agregarProducto(new ProductoHigiene(
                "Shampoo Anticaspa", 3500, "Higiene", TipoDeUso.PERSONAL));
        supermercado.agregarProducto(new ProductoHigiene(
                "Detergente Líquido", 2800, "Higiene", TipoDeUso.DOMESTICO));
        // --- Carga de Clientes ---
        supermercado.agregarCliente(new Cliente(
                "Juan Pérez", 35111222, Cliente.getTotalclientes()));
        supermercado.agregarCliente(new ClienteVIP(
                "Ana Gómez", 38333444, Cliente.getTotalclientes(), 15)); // 15% desc.
        // --- Carga de Empleados ---
        supermercado.agregarEmpleado(new Cajero(
                "Lucas Díaz", 101, 450000, 20));
        supermercado.agregarEmpleado(new Gerente(
                "Laura Méndez", 102, 850000, 150000));
        System.out.println(">>> Datos de prueba cargados correctamente en memoria.");
        // 2. Lanzamos la interfaz gráfica en el hilo de eventos de Swing (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Aplica el estilo visual nativo del sistema operativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // --- PRUEBA DIRECTA DEL MÓDULO DE VENTAS ---
            FrmVenta venta = new FrmVenta(null, true);
            venta.setLocationRelativeTo(null); // Centra la ventana
            venta.setVisible(true);            // Muestra la ventana de ventas

            // NOTA: Para volver a abrir el Menú Principal en lugar de Ventas directamente,
            // comenta las líneas de FrmVenta y descomenta las siguientes:
            // FrmPrincipal principal = new FrmPrincipal(null, false);
            // principal.setLocationRelativeTo(null);
            // principal.setVisible(true);
        });
    }
}
