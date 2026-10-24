package com.example.controlador;

import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.service.SupermercadoService;
import com.example.vista.FrmCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlCliente implements ActionListener {
    
    private FrmCliente vista;
    private SupermercadoService servicio;

    public CtrlCliente(FrmCliente vista) {
        this(vista, SupermercadoService.getInstancia());
    }

    public CtrlCliente(FrmCliente vista, SupermercadoService servicio) {
        this.vista = vista;
        this.servicio = (servicio != null) ? servicio : SupermercadoService.getInstancia();
        
        // Asociar eventos a los botones de la vista
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
        
        // Evento del clic en la tabla para cargar los datos en el formulario
        this.vista.getTablaClientes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarCamposDesdeTabla();
            }
        });

        // Si la lista esta vacia,carg datos para simular
        if (this.servicio.getClientes().isEmpty()) {
            this.servicio.agregarCliente(new Cliente("Juan Pérez", 35111222, Cliente.getTotalclientes()));
            this.servicio.agregarCliente(new ClienteVIP("Ana Gómez", 38333444, Cliente.getTotalclientes(), 15));
        }

        // Cargar los datos iniciales en la tabla al abrir la ventana
        listarClientes();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getBtnRegistrar()) {
            registrarCliente();
        } else if (source == vista.getBtnEditar()) {
            editarCliente();
        } else if (source == vista.getBtnEliminar()) {
            eliminarCliente();
        } else if (source == vista.getBtnVolver()) {
            vista.dispose();
        }
    }

    private void registrarCliente() {
        try {
            String dniStr = vista.getTxtDni().getText().trim();
            String nombre = vista.getTxtNombre().getText().trim();
            
            if (dniStr.isEmpty() || nombre.isEmpty()) {
                vista.mostrarError("Por favor, complete todos los campos obligatorios.");
                return;
            }

            int dni = Integer.parseInt(dniStr);
            String tipoCliente = vista.getCbTipoCliente().getSelectedItem().toString();
            
            // Validar si ya existe el DNI
            if (servicio.buscarCliente(dni) != null) {
                vista.mostrarError("Ya existe un cliente registrado con el DNI: " + dni);
                return;
            }

            Cliente nuevoCliente;
            if (tipoCliente.equals("Cliente VIP")) {
                String descStr = vista.getTxtDescuentoVip().getText().trim();
                if (descStr.isEmpty()) {
                    vista.mostrarError("Debe ingresar el porcentaje de descuento para el cliente VIP.");
                    return;
                }
                int descuentoVIP = Integer.parseInt(descStr);
                nuevoCliente = new ClienteVIP(nombre, dni, Cliente.getTotalclientes(), descuentoVIP);
            } else {
                nuevoCliente = new Cliente(nombre, dni, Cliente.getTotalclientes());
            }

            boolean registrado = servicio.agregarCliente(nuevoCliente);
            if (registrado) {
                vista.mostrarMensaje("¡Cliente registrado con éxito!");
                listarClientes();
                vista.limpiarCampos();
            } else {
                vista.mostrarError("No se pudo registrar el cliente.");
            }

        } catch (NumberFormatException ex) {
            vista.mostrarError("El DNI y el descuento deben ser valores numéricos válidos.");
        }
    }

    private void editarCliente() {
        try {
            String dniStr = vista.getTxtDni().getText().trim();
            if (dniStr.isEmpty()) {
                vista.mostrarError("Por favor, seleccione un cliente de la tabla para editar.");
                return;
            }

            int dni = Integer.parseInt(dniStr);
            Cliente clienteExistente = servicio.buscarCliente(dni);
            if (clienteExistente == null) {
                vista.mostrarError("No se encontró el cliente con DNI " + dni);
                return;
            }

            String nuevoNombre = vista.getTxtNombre().getText().trim();
            if (nuevoNombre.isEmpty()) {
                vista.mostrarError("El nombre no puede estar vacío.");
                return;
            }

            String tipoCliente = vista.getCbTipoCliente().getSelectedItem().toString();
            Cliente clienteActualizado;

            if (tipoCliente.equals("Cliente VIP")) {
                int descuentoVIP = Integer.parseInt(vista.getTxtDescuentoVip().getText().trim());
                clienteActualizado = new ClienteVIP(nuevoNombre, dni, Cliente.getTotalclientes(), descuentoVIP);
            } else {
                clienteActualizado = new Cliente(nuevoNombre, dni, Cliente.getTotalclientes());
            }

            boolean editado = servicio.editarCliente(clienteActualizado);
            if (editado) {
                listarClientes();
                vista.limpiarCampos();
                vista.mostrarMensaje("¡Cliente actualizado con éxito!");
            } else {
                vista.mostrarError("No se pudo actualizar el cliente.");
            }

        } catch (NumberFormatException ex) {
            vista.mostrarError("Verifique que los campos numéricos sean válidos.");
        }
    }

    private void eliminarCliente() {
        int filaSeleccionada = vista.getTablaClientes().getSelectedRow();
        if (filaSeleccionada == -1) {
            vista.mostrarError("Por favor, seleccione un cliente de la tabla para eliminar.");
            return;
        }

        int dni = Integer.parseInt(vista.getTablaClientes().getValueAt(filaSeleccionada, 0).toString());
        
        int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de eliminar al cliente con DNI: " + dni + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = servicio.eliminarCliente(dni);
            if (eliminado) {
                vista.mostrarMensaje("Cliente eliminado correctamente.");
                listarClientes();
                vista.limpiarCampos();
            } else {
                vista.mostrarError("No se pudo eliminar el cliente.");
            }
        }
    }

    private void cargarCamposDesdeTabla() {
        int fila = vista.getTablaClientes().getSelectedRow();
        if (fila >= 0) {
            String dni = vista.getTablaClientes().getValueAt(fila, 0).toString();
            String nombre = vista.getTablaClientes().getValueAt(fila, 1).toString();
            
            vista.getTxtDni().setText(dni);
            vista.getTxtNombre().setText(nombre);
            
            Cliente c = servicio.buscarCliente(Integer.parseInt(dni));
            if (c instanceof ClienteVIP) {
                vista.getCbTipoCliente().setSelectedItem("Cliente VIP");
                vista.getTxtDescuentoVip().setText(String.valueOf(((ClienteVIP) c).getDescuentoVIP()));
            } else {
                vista.getCbTipoCliente().setSelectedItem("Cliente Regular");
                vista.getTxtDescuentoVip().setText("");
            }
            
            // Bloquear el DNI para evitar alterar la clave unica durante la edicion
            vista.getTxtDni().setEditable(false);
        }
    }

    public void listarClientes() {
        List<Cliente> lista = servicio.getClientes();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaClientes().getModel();
        modelo.setRowCount(0); // Limpiar tabla

        for (Cliente c : lista) {
            Object[] fila = new Object[3];
            fila[0] = c.getDni();
            fila[1] = c.getNombre();
            
            if (c instanceof ClienteVIP) {
                ClienteVIP vip = (ClienteVIP) c;
                fila[2] = vip.getDescuentoVIP() + "%"; 
            } else {
                fila[2] = "Regular";
            }
            
            modelo.addRow(fila);
        }
    }
}