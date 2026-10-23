package com.example.util;

import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.empleados.Cajero;
import com.example.modelo.empleados.Gerente;
import com.example.modelo.productos.ProductoAlimenticio;
import com.example.modelo.productos.ProductoElectronico;
import com.example.modelo.productos.ProductoHigiene;
import com.example.modelo.productos.TipoDeUso;
import com.example.service.SupermercadoService;
import java.time.LocalDate;

public class CargaDatosPrueba {

    public static void cargar(SupermercadoService supermercado) {
        if (supermercado == null) {
            return;
        }

        // ==========================================
        // 1. CARGA DE 15 PRODUCTOS ALIMENTICIOS
        // ==========================================
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Arroz Gallo Oro 1kg", 1500, "Alimenticio", LocalDate.now().plusDays(30), true, 25));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Lentejas La Abuela 500g", 1100, "Alimenticio", LocalDate.now().plusDays(45), true, 20));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Fideos Matarazzo Guisero 500g", 1250, "Alimenticio", LocalDate.now().plusDays(60), true, 30));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Aceite Cocinero Girasol 1.5L", 2400, "Alimenticio", LocalDate.now().plusDays(90), true, 18));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Leche La Serenísima Entera 1L", 1300, "Alimenticio", LocalDate.now().plusDays(10), true, 40));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Yogur Ilolay Frutilla 1L", 950, "Alimenticio", LocalDate.now().plusDays(7), true, 22));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Queso Cremoso La Paulina 1kg", 6800, "Alimenticio", LocalDate.now().plusDays(15), true, 12));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Galletitas Chocolinas 250g", 1400, "Alimenticio", LocalDate.now().plusDays(40), true, 35));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Harina Blancaflor Leudante 1kg", 1150, "Alimenticio", LocalDate.now().plusDays(50), true, 28));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Azúcar Ledesma Clásica 1kg", 980, "Alimenticio", LocalDate.now().plusDays(120), false, 50));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Yerba Mate Playadito 1kg", 4200, "Alimenticio", LocalDate.now().plusDays(180), false, 30));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Café La Virginia Molido 500g", 3900, "Alimenticio", LocalDate.now().plusDays(90), true, 15));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Atún La Campagnola Desmenuzado 170g", 2100, "Alimenticio", LocalDate.now().plusDays(365), false, 25));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Puré de Tomates De La Huerta 520g", 850, "Alimenticio", LocalDate.now().plusDays(60), true, 45));
        supermercado.agregarProducto(new ProductoAlimenticio(
                "Mermelada BC Frutos Rojos 390g", 1950, "Alimenticio", LocalDate.now().plusDays(75), true, 20));

        // ==========================================
        // 2. CARGA DE 15 PRODUCTOS ELECTRÓNICOS
        // ==========================================
        supermercado.agregarProducto(new ProductoElectronico(
                "Auriculares Bluetooth Sony", 28000, "Electrónico", 1, 15));
        supermercado.agregarProducto(new ProductoElectronico(
                "Monitor Gamer Samsung 24", 195000, "Electrónico", 2, 8));
        supermercado.agregarProducto(new ProductoElectronico(
                "Teclado Mecánico Redragon", 45000, "Electrónico", 1, 20));
        supermercado.agregarProducto(new ProductoElectronico(
                "Mouse Logitech G203", 26000, "Electrónico", 2, 25));
        supermercado.agregarProducto(new ProductoElectronico(
                "Smart TV Noblex 43 FHD", 320000, "Electrónico", 1, 6));
        supermercado.agregarProducto(new ProductoElectronico(
                "Parlante Portátil JBL Flip 6", 145000, "Electrónico", 1, 10));
        supermercado.agregarProducto(new ProductoElectronico(
                "Microondas BGH 20L Digital", 160000, "Electrónico", 2, 7));
        supermercado.agregarProducto(new ProductoElectronico(
                "Pava Eléctrica Philips 1.7L", 38000, "Electrónico", 1, 18));
        supermercado.agregarProducto(new ProductoElectronico(
                "Cafetera Nespresso Essenza Mini", 125000, "Electrónico", 2, 9));
        supermercado.agregarProducto(new ProductoElectronico(
                "Planchita de Pelo GA.MA Ceramic", 42000, "Electrónico", 1, 14));
        supermercado.agregarProducto(new ProductoElectronico(
                "Cargador Rápido Motorola 33W USB-C", 18500, "Electrónico", 1, 30));
        supermercado.agregarProducto(new ProductoElectronico(
                "Pendrive Kingston 64GB USB 3.2", 7500, "Electrónico", 3, 50));
        supermercado.agregarProducto(new ProductoElectronico(
                "Tablet Lenovo Tab M9 64GB", 175000, "Electrónico", 1, 11));
        supermercado.agregarProducto(new ProductoElectronico(
                "Ventilador de Pie Liliana 20", 65000, "Electrónico", 1, 16));
        supermercado.agregarProducto(new ProductoElectronico(
                "Aspiradora Philips PowerPro 1800W", 140000, "Electrónico", 2, 5));

        // ==========================================
        // 3. CARGA DE 15 PRODUCTOS DE HIGIENE
        // ==========================================
        supermercado.agregarProducto(new ProductoHigiene(
                "Shampoo Pantene Restauración 400ml", 3600, "Higiene", TipoDeUso.PERSONAL, 20));
        supermercado.agregarProducto(new ProductoHigiene(
                "Acondicionador Dove Hidratación 400ml", 3400, "Higiene", TipoDeUso.PERSONAL, 18));
        supermercado.agregarProducto(new ProductoHigiene(
                "Jabón Líquido Corporal Rexona 250ml", 1900, "Higiene", TipoDeUso.PERSONAL, 30));
        supermercado.agregarProducto(new ProductoHigiene(
                "Dentífrico Colgate Total 12 90g", 2200, "Higiene", TipoDeUso.PERSONAL, 40));
        supermercado.agregarProducto(new ProductoHigiene(
                "Desodorante Nivea Men Roll-on 50ml", 2800, "Higiene", TipoDeUso.PERSONAL, 25));
        supermercado.agregarProducto(new ProductoHigiene(
                "Cepillo Dental Oral-B Pro Doble Acción", 1600, "Higiene", TipoDeUso.PERSONAL, 35));
        supermercado.agregarProducto(new ProductoHigiene(
                "Papel Higiénico Higienol Max 4u", 3100, "Higiene", TipoDeUso.PERSONAL, 50));
        supermercado.agregarProducto(new ProductoHigiene(
                "Detergente Ala Concentrado Limón 750ml", 2100, "Higiene", TipoDeUso.DOMESTICO, 45));
        supermercado.agregarProducto(new ProductoHigiene(
                "Lavandina Ayudín Común 1L", 1500, "Higiene", TipoDeUso.DOMESTICO, 60));
        supermercado.agregarProducto(new ProductoHigiene(
                "Limpiador de Pisos Poett Primavera 900ml", 1850, "Higiene", TipoDeUso.DOMESTICO, 35));
        supermercado.agregarProducto(new ProductoHigiene(
                "Jabón en Polvo Skip Bio-Enzimas 800g", 4200, "Higiene", TipoDeUso.DOMESTICO, 22));
        supermercado.agregarProducto(new ProductoHigiene(
                "Suavizante Vivere Clásico 900ml", 3300, "Higiene", TipoDeUso.DOMESTICO, 28));
        supermercado.agregarProducto(new ProductoHigiene(
                "Desengrasante Cif Antigrasa Gatillo 500ml", 2700, "Higiene", TipoDeUso.DOMESTICO, 20));
        supermercado.agregarProducto(new ProductoHigiene(
                "Lustramuebles Blem Aerosol Lavanda", 3100, "Higiene", TipoDeUso.DOMESTICO, 15));
        supermercado.agregarProducto(new ProductoHigiene(
                "Esponja Mortimer Multiuso 3u", 1400, "Higiene", TipoDeUso.DOMESTICO, 40));

        // ==========================================
        // 4. CARGA DE 10 CLIENTES (Regulares y VIP)
        // ==========================================
        supermercado.agregarCliente(new Cliente(
                "Juan Pérez", 35111222, Cliente.getTotalclientes()));
        supermercado.agregarCliente(new ClienteVIP(
                "Ana Gómez", 38333444, Cliente.getTotalclientes(), 15));
        supermercado.agregarCliente(new Cliente(
                "Carlos Rodríguez", 32444555, Cliente.getTotalclientes()));
        supermercado.agregarCliente(new ClienteVIP(
                "María Fernández", 29888999, Cliente.getTotalclientes(), 20));
        supermercado.agregarCliente(new Cliente(
                "Lucas Martínez", 41222333, Cliente.getTotalclientes()));
        supermercado.agregarCliente(new ClienteVIP(
                "Sofía Benítez", 36777888, Cliente.getTotalclientes(), 15));
        supermercado.agregarCliente(new Cliente(
                "Martín López", 39111444, Cliente.getTotalclientes()));
        supermercado.agregarCliente(new ClienteVIP(
                "Valeria Romero", 34555666, Cliente.getTotalclientes(), 10));
        supermercado.agregarCliente(new Cliente(
                "Diego Torres", 40123789, Cliente.getTotalclientes()));
        supermercado.agregarCliente(new ClienteVIP(
                "Lucía Castro", 37654321, Cliente.getTotalclientes(), 15));

        // ==========================================
        // 5. CARGA DE 5 EMPLEADOS (Cajeros y Gerentes)
        // ==========================================
        supermercado.agregarEmpleado(new Cajero(
                "Lucas Díaz", 101, 450000, 20));
        supermercado.agregarEmpleado(new Gerente(
                "Laura Méndez", 102, 850000, 150000));
        supermercado.agregarEmpleado(new Cajero(
                "Agustina Silva", 103, 460000, 35));
        supermercado.agregarEmpleado(new Cajero(
                "Marcos Medina", 104, 455000, 18));
        supermercado.agregarEmpleado(new Gerente(
                "Gonzalo Morales", 105, 900000, 180000));
    }
}
