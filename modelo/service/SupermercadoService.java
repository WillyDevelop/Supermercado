package com.example.modelo.service;

import com.example.modelo.excepciones.ClienteNoRegistradoException;
import com.example.modelo.excepciones.ProductoNoDisponibleException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SupermercadoService {
    // Patron Singleton
    private static SupermercadoService instancia;

    public static SupermercadoService getInstancia() {
        if (instancia == null) {
            instancia = new SupermercadoService();
        }
        return instancia;
    }

    // almacena catalogo productos y datos del personal
    private List<Producto> productosDisponibles;
    private List<Cliente> clientes;
    private List<Empleado> empleados;

    public SupermercadoService() {
        this.productosDisponibles = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.empleados = new ArrayList<>();
        instancia = this; // Vincula la instancia creada (por ejemplo desde Main) como singleton accesible
    }

    // CREAR

    public boolean agregarProducto(Producto producto) {
        if (producto != null) {
            return productosDisponibles.add(producto);
        }
        return false;
    }

    public boolean agregarCliente(Cliente cliente) {
        if (cliente != null) {
            return clientes.add(cliente);
        }
        return false;
    }

    public boolean agregarEmpleado(Empleado empleado) {
        if (empleado != null) {
            return empleados.add(empleado);
        }
        return false;
    }

    // LEER Y BUSCAR

    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    // --- Buscar producto por id ---
    public Producto buscarProducto(UUID codigo) {
        if (codigo == null)
            return null;
        for (Producto p : productosDisponibles) {
            if (codigo.equals(p.getCodigo())) {
                return p;
            }
        }
        return null;
    }

    // --- Buscar cliente por dni ---
    public Cliente buscarCliente(int dni) {
        for (Cliente c : clientes) {
            if (c.getDni() == dni) {
                return c;
            }
        }
        return null;
    }

    // --- Buscar empleado por id ---
    public Empleado buscarEmpleado(int idEmpleado) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado() == idEmpleado) {
                return e;
            }
        }
        return null;
    }

    // --- Actualizar producto por id ---
    public boolean actualizarProducto(UUID codigo, String nuevoNombre, double nuevoPrecio) {
        Producto p = buscarProducto(codigo);
        if (p != null) {
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                p.setNombre(nuevoNombre.trim());
            }
            p.setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    // SOBRECARGA DE METODOS PARA EDITAR

    // --- Editar producto por id solo cambia el nombre y el precio ---
    public boolean editarProducto(UUID codigo, String nuevoNombre, double nuevoPrecio) {
        return actualizarProducto(codigo, nuevoNombre, nuevoPrecio);
    }

    // --- Editar producto por objeto - Reemplaza el objeto completo ---
    public boolean editarProducto(Producto productoModificado) {
        if (productoModificado == null || productoModificado.getCodigo() == null)
            return false;
        for (int i = 0; i < productosDisponibles.size(); i++) {
            if (productosDisponibles.get(i).getCodigo().equals(productoModificado.getCodigo())) {
                productosDisponibles.set(i, productoModificado);
                return true;
            }
        }
        return false;
    }

    // --- Actualizar cliente por dni ---
    public boolean actualizarCliente(int dni, String nuevoNombre) {
        Cliente c = buscarCliente(dni);
        if (c != null) {
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                c.setNombre(nuevoNombre.trim());
            }
            return true;
        }
        return false;
    }

    // --- Editar cliente por dni solo cambia el nombre ---
    public boolean editarCliente(int dni, String nuevoNombre) {
        return actualizarCliente(dni, nuevoNombre);
    }

    // --- Editar cliente por objeto - Reemplaza el objeto completo ---
    public boolean editarCliente(Cliente clienteModificado) {
        if (clienteModificado == null)
            return false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDni() == clienteModificado.getDni()) {
                clientes.set(i, clienteModificado);
                return true;
            }
        }
        return false;
    }

    // --- Actualizar empleado por id ---
    public boolean actualizarEmpleado(int idEmpleado, String nuevoNombre, double nuevoSalarioBase) {
        Empleado e = buscarEmpleado(idEmpleado);
        if (e != null) {
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                e.setNombre(nuevoNombre.trim());
            }
            e.setSalarioBase(nuevoSalarioBase);
            return true;
        }
        return false;
    }

    // --- Editar empleado por id solo cambia nombre y salario base ---
    public boolean editarEmpleado(int idEmpleado, String nuevoNombre, double nuevoSalarioBase) {
        return actualizarEmpleado(idEmpleado, nuevoNombre, nuevoSalarioBase);
    }

    // --- Editar empleado por objeto - Reemplaza el objeto completo ---
    public boolean editarEmpleado(Empleado empleadoModificado) {
        if (empleadoModificado == null)
            return false;
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado() == empleadoModificado.getIdEmpleado()) {
                empleados.set(i, empleadoModificado);
                return true;
            }
        }
        return false;
    }

    // --- Eliminar producto por id ---
    public boolean eliminarProducto(UUID codigo) {
        Producto p = buscarProducto(codigo);
        if (p != null) {
            return productosDisponibles.remove(p);
        }
        return false;
    }

    // --- Eliminar cliente por dni ---
    public boolean eliminarCliente(int dni) {
        Cliente c = buscarCliente(dni);
        if (c != null) {
            return clientes.remove(c);
        }
        return false;
    }

    // --- Eliminar empleado por id ---
    public boolean eliminarEmpleado(int idEmpleado) {
        Empleado e = buscarEmpleado(idEmpleado);
        if (e != null) {
            return empleados.remove(e);
        }
        return false;
    }

    // --- Registrar compra ---
    public void registrarCompra(Cliente cliente, Producto producto)
            throws ClienteNoRegistradoException, ProductoNoDisponibleException {

        // Validar cliente registrado
        if (!clientes.contains(cliente)) {
            throw new ClienteNoRegistradoException(
                    "Error: El cliente " + (cliente != null ? cliente.getNombre() : "desconocido")
                            + " no esta registrado en el sistema.");
        }

        // Validar producto disponible y con stock
        if (!productosDisponibles.contains(producto) || producto.getStock() <= 0) {
            throw new ProductoNoDisponibleException(
                    "Error: El producto " + (producto != null ? producto.getNombre() : "desconocido")
                            + " no tiene stock disponible en el inventario.");
        }

        // Realiza la compra y actualiza el inventario
        cliente.comprar(producto);
        producto.setStock(producto.getStock() - 1);
    }

    // Sobrecarga para registrar la compra de una lista de productos (carrito)
    public void registrarCompra(Cliente cliente, List<Producto> productos)
            throws ClienteNoRegistradoException, ProductoNoDisponibleException {
        if (productos == null || productos.isEmpty()) {
            return;
        }

        // Contar unidades solicitadas de cada producto en el carrito
        java.util.Map<Producto, Integer> cantidades = new java.util.HashMap<>();
        for (Producto p : productos) {
            if (!productosDisponibles.contains(p)) {
                throw new ProductoNoDisponibleException(
                        "Error: El producto " + (p != null ? p.getNombre() : "desconocido")
                                + " no esta disponible en el inventario.");
            }
            cantidades.put(p, cantidades.getOrDefault(p, 0) + 1);
        }

        // Verificar que haya suficiente stock para cada producto
        for (java.util.Map.Entry<Producto, Integer> entry : cantidades.entrySet()) {
            Producto p = entry.getKey();
            int cantidadPedida = entry.getValue();
            if (p.getStock() < cantidadPedida) {
                throw new ProductoNoDisponibleException(
                        "Error: Stock insuficiente para '" + p.getNombre()
                                + "'. Stock actual: " + p.getStock()
                                + ", solicitado: " + cantidadPedida + ".");
            }
        }

        // Si todos están disponibles y con stock suficiente, procesamos cada compra
        for (Producto p : productos) {
            registrarCompra(cliente, p);
        }
    }
}
