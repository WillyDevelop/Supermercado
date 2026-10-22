package com.example.modelo;

import com.example.excepciones.ClienteNoRegistradoException;
import com.example.excepciones.ProductoNoDisponibleException;
import com.example.modelo.clientes.Cliente;
import com.example.modelo.empleados.Empleado;
import com.example.modelo.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class Supermercado {
    private List<Producto> productosDisponibles;
    private List<Cliente> clientes;
    private List<Empleado> empleados;

    public Supermercado () {
        this.productosDisponibles = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.empleados = new ArrayList<>();
    }

    //los metodos para alta y registros
    public void agregarProducto(Producto producto) {
        if (producto != null){
            productosDisponibles.add(producto);
            System.out.println("producto " +producto.getNombre()+ " agregado con exito");
        } else {
            System.out.println("el producto no es valido");
        }
    };

    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
            System.out.println("Cliente " + cliente.getNombre() + " registrado con exito");
        } else {
            System.out.println("El cliente no es valido");
        }
    }

    public void agregarEmpleado(Empleado empleado) {
        if (empleado != null) {
            empleados.add(empleado);
            System.out.println("Empleado " + empleado.getNombre() + " agregado al sistema");
        } else {
            System.out.println("El empleado no es valido");
        }
    }

    //Esto es para registrar compra con control de excepciones
    public void registrarCompra(Cliente cliente, Producto producto)
            throws ClienteNoRegistradoException, ProductoNoDisponibleException {

        //cliente registrado
        if (!clientes.contains(cliente)) {
            throw new ClienteNoRegistradoException(
                    "Error: El cliente " + (cliente != null ? cliente.getNombre() : "desconocido") + " no esta registrado en el sistema."
            );
        }

        //cliente disponible
        if (!productosDisponibles.contains(producto)) {
            throw new ProductoNoDisponibleException(
                    "Error: El producto " + (producto != null ? producto.getNombre() : "desconocido") + " no esta disponible en el inventario."
            );
        }

        //realiza la compra y actualiza el inventario
        cliente.comprar(producto);
        productosDisponibles.remove(producto);
        System.out.println("Compra registrada con exito para " + cliente.getNombre() + ": " + producto.getNombre());
    }

    //metodo mostrar inventario disponible
    public void mostrarInventario(){
        System.out.println("Inventario de los productos disponibles");
        if (productosDisponibles.isEmpty()){
            System.out.println("No hay productos disponibles");
            return;
        }
        for (Producto p : productosDisponibles) {
            System.out.println("- " + p.mostrarInformacion());
        }
    }

    //metodo mostrar clientes registrados
    public void mostrarClientes(){
        System.out.println("Clientes registrados");
        if (clientes.isEmpty()){
            System.out.println("No hay clientes registrados");
            return;
        }
        for (Cliente c : clientes){
            System.out.println("- " + c.getNombre() + " (DNI: " + c.getDni() + ")");
        }
    }

    public void mostrarSalariosEmpleados(){
        System.out.println("Salario de los Empleados");
        if (empleados.isEmpty()){
            System.out.println("No hay empleados registrados");
            return;
        }
        for (Empleado emp : empleados){
            System.out.println("ID: " + emp.getIdEmpleado() + " | Empleado: " + emp.getNombre() + " | Salario: $" + emp.calcularSalario());
        }
    }

    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}
