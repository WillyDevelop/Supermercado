package com.example;

import com.example.excepciones.ClienteNoRegistradoException;
import com.example.excepciones.ProductoNoDisponibleException;
import com.example.modelo.Supermercado;
import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.empleados.Cajero;
import com.example.modelo.empleados.Empleado;
import com.example.modelo.empleados.Gerente;
import com.example.modelo.productos.Producto;
import com.example.modelo.productos.ProductoAlimenticio;
import com.example.modelo.productos.ProductoElectronico;
import com.example.modelo.productos.ProductoHigiene;
import com.example.modelo.productos.TipoDeUso;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("\n---Inicio de prueba de funcionamiento de codigo en general---\n");
        System.out.println("\nañadir productos\n");
        ProductoAlimenticio prodAlim = new ProductoAlimenticio(
                " Arroz", 1500, "Alimenticio", LocalDate.now().plusDays(3), true);
        ProductoAlimenticio prodAlim2 = new ProductoAlimenticio(
                " Lentejas",  1000, "Alimenticio", LocalDate.now().plusDays(3), true);
        ProductoElectronico prodElec = new ProductoElectronico(
                " Auriculares ", 25000, "Electrónico", 3);
        ProductoElectronico prodElec2 = new ProductoElectronico(
                " Mini PC ",  2005000, "Electrónico", 3);
        ProductoHigiene prodHig = new ProductoHigiene(
                " Shampoo Head&shoulders", 3500, "Higiene", TipoDeUso.PERSONAL);

        supermercado.agregarProducto(prodAlim);
        supermercado.agregarProducto(prodAlim2);
        supermercado.agregarProducto(prodElec);
        supermercado.agregarProducto(prodElec2);
        supermercado.agregarProducto(prodHig);

        System.out.println("\nregistro de clientes\n");
        Cliente cliente1 = new Cliente("Juan ppap", 99111222, Cliente.getTotalclientes());
        ClienteVIP clienteVip = new ClienteVIP("Ana Gi", 99333444, Cliente.getTotalclientes(), 15);
        Cliente clienteNoRegistrado = new Cliente("Carlos NoRegistrado", 99999999, Cliente.getTotalclientes());

        supermercado.agregarCliente(cliente1);
        supermercado.agregarCliente(clienteVip);
        supermercado.mostrarClientes();

        //llamo al metodo mostrarInventario de la clase Supermercado
        System.out.println("\nStock inicial\n");
        supermercado.mostrarInventario();

        System.out.println("\ncompras y descuento a los vip\n");
        try {
            supermercado.registrarCompra(cliente1, prodAlim);

            clienteVip.aplicarDescuentoVIP(prodElec);
            supermercado.registrarCompra(clienteVip, prodElec);
        } catch (ClienteNoRegistradoException | ProductoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }

        //llamo al meotodo mostrarInventario() de la clase Supermercado
        System.out.println("\nStock actualizado (luegfo de la comprfa)\n");
        supermercado.mostrarInventario();

        System.out.println("\nExcepciones \n");
        try {
            supermercado.registrarCompra(cliente1, prodAlim);
        } catch (ClienteNoRegistradoException | ProductoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }

        try {
            supermercado.registrarCompra(clienteNoRegistrado, prodHig);
        } catch (ClienteNoRegistradoException | ProductoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nREGISTRO Y CÁLCULO DE SALARIOS DE EMPLEADOS\n");
        Cajero cajero = new Cajero("Lucas Díaz", 1, 450000, 20);
        Gerente gerente = new Gerente("Laura Méndez", 2, 850000, 150000);

        supermercado.agregarEmpleado(cajero);
        supermercado.agregarEmpleado(gerente);
        supermercado.mostrarSalariosEmpleados();


        supermercado.agregarProducto(prodAlim);
        supermercado.agregarProducto(prodElec);
        supermercado.agregarProducto(prodAlim);
        supermercado.agregarProducto(prodElec);
        System.out.println("\n");
        System.out.println("\n---Fin de la prueba de funcionamiento---\n");

        int opcion;
        do {
            System.out.println("\n--- Menú de operaciones ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Registrar Cliente");
            System.out.println("3. Calcular Salarios");
            System.out.println("4. Control Stock Productos");
            System.out.println("5. Realizar Compra");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Seleccione tipo: 1. Alimenticio | 2. Electrónico | 3. Higiene");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre del Producto: ");
                    String nombProd = scanner.nextLine();
                    System.out.print("Precio base: ");
                    int precio = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo == 1) {
                        System.out.print("Días para caducar: ");
                        int dias = scanner.nextInt();
                        System.out.print("¿Es perecedero? (true/false): ");
                        boolean perecedero = scanner.nextBoolean();
                        supermercado.agregarProducto(new ProductoAlimenticio(
                                nombProd, precio, "Alimentación", LocalDate.now().plusDays(dias), perecedero));
                    } else if (tipo == 2) {
                        System.out.print("Años de garantía: ");
                        int garantia = scanner.nextInt();
                        supermercado.agregarProducto(new ProductoElectronico(
                                nombProd,  precio, "Electrónica", garantia));
                    } else if (tipo == 3) {
                        System.out.println("Seleccione tipo de uso: 1. Personal | 2. Doméstico");
                        int opcionUso = Integer.parseInt(scanner.nextLine());
                        TipoDeUso tipoDeUso = (opcionUso == 1) ? TipoDeUso.PERSONAL : TipoDeUso.DOMESTICO;

                        supermercado.agregarProducto(new ProductoHigiene(
                                nombProd,  precio, "Higiene", tipoDeUso));
                    } else {
                        System.out.println("Tipo no válido.");
                    }
                    break;

                case 2:
                    System.out.print("Nombre del Cliente: ");
                    String nombCli = scanner.nextLine();
                    System.out.print("DNI: ");
                    int dni = scanner.nextInt();
                    System.out.print("¿Es Cliente VIP? (true/false): ");
                    boolean esVip = scanner.nextBoolean();

                    if (esVip) {
                        System.out.print("Porcentaje de descuento VIP: ");
                        int desc = scanner.nextInt();
                        supermercado.agregarCliente(new ClienteVIP(nombCli, dni, Cliente.getTotalclientes(), desc));
                    } else {
                        supermercado.agregarCliente(new Cliente(nombCli, dni, Cliente.getTotalclientes()));
                    }
                    break;

                case 3:
                    //la logica lo maneja la clase supermercado, aca solo llamo al meotodo
                    supermercado.mostrarSalariosEmpleados();

                    break;

                case 4:
                    supermercado.mostrarInventario();

                    break;

                case 5:
                    if (supermercado.getClientes().isEmpty() || supermercado.getProductosDisponibles().isEmpty()) {
                        System.out.println("Debe haber clientes y productos cargados para realizar una compra.");
                        break;
                    }
                    System.out.println("Seleccione el número de cliente:");
                    for (int i = 0; i < supermercado.getClientes().size(); i++) {
                        System.out.println(i + ". " + supermercado.getClientes().get(i).getNombre());
                    }
                    int idxCli = scanner.nextInt();

                    System.out.println("Seleccione el número de producto:");
                    for (int i = 0; i < supermercado.getProductosDisponibles().size(); i++) {
                        System.out.println(i + ". " + supermercado.getProductosDisponibles().get(i).getNombre());
                    }
                    int idxProd = scanner.nextInt();

                    Cliente cliSeleccionado = (idxCli >= 0 && idxCli < supermercado.getClientes().size())
                            ? supermercado.getClientes().get(idxCli) : null;
                    Producto prodSeleccionado = (idxProd >= 0 && idxProd < supermercado.getProductosDisponibles().size())
                            ? supermercado.getProductosDisponibles().get(idxProd) : null;

                    try {
                        // El Supermercado registra la compra, si el cliente resulta ser VIP
                        // su metodo comprar() aplica el descuento
                        supermercado.registrarCompra(cliSeleccionado, prodSeleccionado);
                    } catch (ClienteNoRegistradoException | ProductoNoDisponibleException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Saliendo.");
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}