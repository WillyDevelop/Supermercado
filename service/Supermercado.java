package com.example.service;

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

    public Supermercado() {
        this.productosDisponibles = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.empleados = new ArrayList<>();
    }

    
    // CREAR 
   

    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productosDisponibles.add(producto);
            System.out.println("producto " + producto.getNombre() + " agregado con exito");
        } else {
            System.out.println("el producto no es valido");
        }
    }

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

    
    // LEER 
    

    // ---Listas completas
    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    // --por nombre (Tal vez sea conveniente agregar otras para buscar por id)
    public Producto buscarProductoPorNombre(String nombre) {
        if (nombre == null) return null;
        for (Producto p : productosDisponibles) {
            if (p.getNombre() != null && p.getNombre().trim().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    public Cliente buscarClientePorNombre(String nombre) {
        if (nombre == null) return null;
        for (Cliente c : clientes) {
            if (c.getNombre() != null && c.getNombre().trim().equalsIgnoreCase(nombre.trim())) {
                return c;
            }
        }
        return null;
    }

    public Empleado buscarEmpleadoPorNombre(String nombre) {
        if (nombre == null) return null;
        for (Empleado e : empleados) {
            if (e.getNombre() != null && e.getNombre().trim().equalsIgnoreCase(nombre.trim())) {
                return e;
            }
        }
        return null;
    }

    // Estos metodos tal vez habria q borrarlo ya q servia para la consola unicamente ahora con vistas no tiene sentido
    public void mostrarInventario() {
        System.out.println("Inventario de los productos disponibles");
        if (productosDisponibles.isEmpty()) {
            System.out.println("No hay productos disponibles");
            return;
        }
        for (Producto p : productosDisponibles) {
            System.out.println("- " + p.mostrarInformacion());
        }
    }

    public void mostrarClientes() {
        System.out.println("Clientes registrados");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println("- " + c.getNombre() + " (DNI: " + c.getDni() + ")");
        }
    }

    public void mostrarSalariosEmpleados() {
        System.out.println("Salario de los Empleados");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
            return;
        }
        for (Empleado emp : empleados) {
            System.out.println("ID: " + emp.getIdEmpleado() + " | Empleado: " + emp.getNombre() + " | Salario: $" + emp.calcularSalario());
        }
    }

    // ACTUALIZAR los hice por nombre aunque aun no se si sea mejor por UUID/DNI/ID

    public boolean actualizarProducto(String nombre, String nuevoNombre, double nuevoPrecio) {
        Producto p = buscarProductoPorNombre(nombre);
        if (p != null) {
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                p.setNombre(nuevoNombre.trim());
            }
            p.setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    public boolean actualizarCliente(String nombre, String nuevoNombre, int nuevoDni) {
        Cliente c = buscarClientePorNombre(nombre);
        if (c != null) {
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                c.setNombre(nuevoNombre.trim());
            }
            c.setDni(nuevoDni);
            return true;
        }
        return false;
    }

 

    public boolean actualizarEmpleado(String nombre, String nuevoNombre, double nuevoSalarioBase) {
        Empleado e = buscarEmpleadoPorNombre(nombre);
        if (e != null) {
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                e.setNombre(nuevoNombre.trim());
            }
            e.setSalarioBase(nuevoSalarioBase);
            return true;
        }
        return false;
    }

    
    //BORRAR  los hice por nombre aunque aun no se si sea mejor por UUID/DNI/ID

    public boolean eliminarProductoPorNombre(String nombre) {
        Producto p = buscarProductoPorNombre(nombre);
        if (p != null) {
            return productosDisponibles.remove(p);
        }
        return false;
    }

    public boolean eliminarClientePorNombre(String nombre) {
        Cliente c = buscarClientePorNombre(nombre);
        if (c != null) {
            return clientes.remove(c);
        }
        return false;
    }

    public boolean eliminarEmpleadoPorNombre(String nombre) {
        Empleado e = buscarEmpleadoPorNombre(nombre);
        if (e != null) {
            return empleados.remove(e);
        }
        return false;
    }

    // Compras

    public void registrarCompra(Cliente cliente, Producto producto)
            throws ClienteNoRegistradoException, ProductoNoDisponibleException {

        // cliente registrado
        if (!clientes.contains(cliente)) {
            throw new ClienteNoRegistradoException(
                    "Error: El cliente " + (cliente != null ? cliente.getNombre() : "desconocido") + " no esta registrado en el sistema."
            );
        }

        // producto disponible
        if (!productosDisponibles.contains(producto)) {
            throw new ProductoNoDisponibleException(
                    "Error: El producto " + (producto != null ? producto.getNombre() : "desconocido") + " no esta disponible en el inventario."
            );
        }

        // realiza la compra y actualiza el inventario
        cliente.comprar(producto);
        productosDisponibles.remove(producto);
        System.out.println("Compra registrada con exito para " + cliente.getNombre() + ": " + producto.getNombre());
    }
}
