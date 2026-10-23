/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controlador;

import com.example.modelo.empleados.Empleado;
import com.example.modelo.empleados.Gerente;
import com.example.modelo.empleados.Cajero;
import com.example.vista.FrmEmpleado;
import com.example.service.SupermercadoService;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Controlador para la gestión de Empleados (MVC)
 */
public class CtrlEmpleado implements ActionListener {
    private FrmEmpleado vista;
    private SupermercadoService servicio;
    
    // Variable auxiliar para guardar el ID del empleado que se está editando actualmente
    private int idEmpleadoSeleccionado = -1;

    public CtrlEmpleado(FrmEmpleado vista) {
        this(vista, SupermercadoService.getInstancia());
    }

    public CtrlEmpleado(FrmEmpleado vista, SupermercadoService servicio) {
        this.vista = vista;
        this.servicio = (servicio != null) ? servicio : SupermercadoService.getInstancia();

        // Escuchadores de los botones
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnCalcularSalario().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);

        // Cambio dinámico de etiqueta según el cargo seleccionado en el ComboBox
        this.vista.getCbTipoEmpleado().addActionListener(e -> {
            String cargo = (String) this.vista.getCbTipoEmpleado().getSelectedItem();
            if ("Cajero".equalsIgnoreCase(cargo)) {
                this.vista.getLblDatoExtra().setText("Ventas Realizadas:");
            } else {
                this.vista.getLblDatoExtra().setText("Bono Gerencial:");
            }
        });

        // Evento para cargar datos en los campos al hacer clic en una fila de la tabla
        this.vista.getTablaEmpleados().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarCamposDesdeTabla();
            }
        });

        // Si la lista está vacía, cargamos los datos de prueba iniciales
        if (this.servicio.getEmpleados().isEmpty()) {
            this.servicio.agregarEmpleado(new Cajero("Lucas Díaz", 101, 450000, 20));
            this.servicio.agregarEmpleado(new Gerente("Laura Méndez", 102, 850000, 150000));
        }

        // Cargamos los datos iniciales en la tabla
        actualizarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.getBtnRegistrar()) {
            registrarEmpleado();
        } else if (source == vista.getBtnCalcularSalario()) {
            calcularSalario();
        } else if (source == vista.getBtnEditar()) {
            editarEmpleado();
        } else if (source == vista.getBtnEliminar()) {
            eliminarEmpleado();
        } else if (source == vista.getBtnVolver()) {
            vista.dispose();
        }
    }

    /**
     * Registra un nuevo empleado con ID autogenerado
     */
    private void registrarEmpleado() {
        try {
            if (camposVacios()) {
                vista.mostrarError("Por favor, complete todos los campos requeridos.");
                return;
            }

            String nombre = vista.getTxtNombre().getText().trim();
            double salarioBase = Double.parseDouble(vista.getTxtSalarioBase().getText().trim());
            String cargo = (String) vista.getCbTipoEmpleado().getSelectedItem();
            double datoExtra = Double.parseDouble(vista.getTxtDatoExtra().getText().trim());

            if (salarioBase < 0 || datoExtra < 0) {
                vista.mostrarError("El salario base y las ventas/bono deben ser valores positivos.");
                return;
            }

            // Generar un ID único automáticamente (buscando el ID máximo actual y sumando 1, o usando un contador)
            int nuevoId = generarIdAutomatico();

            Empleado nuevoEmpleado;
            if ("Cajero".equalsIgnoreCase(cargo)) {
                nuevoEmpleado = new Cajero(nombre, nuevoId, salarioBase, (int) datoExtra);
            } else {
                nuevoEmpleado = new Gerente(nombre, nuevoId, salarioBase, datoExtra);
            }

            servicio.agregarEmpleado(nuevoEmpleado);
            actualizarTabla();
            limpiarTodo();
            vista.mostrarMensaje("¡Empleado registrado con éxito! (ID asignado: " + nuevoId + ")");

        } catch (NumberFormatException ex) {
            vista.mostrarError("Por favor, ingrese valores numéricos válidos en Salario Base y Ventas/Bono.");
        }
    }

    /**
     * Calcula el salario del empleado con los datos en pantalla o de la fila seleccionada
     */
    private void calcularSalario() {
        try {
            String txtSalario = vista.getTxtSalarioBase().getText().trim();
            String txtExtra = vista.getTxtDatoExtra().getText().trim();

            if (!txtSalario.isEmpty() && !txtExtra.isEmpty()) {
                double salarioBase = Double.parseDouble(txtSalario);
                double datoExtra = Double.parseDouble(txtExtra);
                String nombre = vista.getTxtNombre().getText().trim();
                String cargo = (String) vista.getCbTipoEmpleado().getSelectedItem();

                double total;
                String detalleExtra;

                if ("Cajero".equalsIgnoreCase(cargo)) {
                    int ventas = (int) datoExtra;
                    double comision = ventas * 100; // Asumiendo comisión fija por venta
                    total = salarioBase + comision;
                    detalleExtra = "Comisión (" + ventas + " ventas x $100): $" + comision;
                } else {
                    total = salarioBase + datoExtra;
                    detalleExtra = "Bono Gerencial: $" + datoExtra;
                }

                String mensaje = "Cálculo de Salario:"
                        + (nombre.isEmpty() ? "" : "\nEmpleado: " + nombre)
                        + "\nCargo: " + cargo
                        + "\nSalario Base: $" + salarioBase
                        + "\n" + detalleExtra
                        + "\n------------------------------------"
                        + "\nSalario Total: $" + total;

                vista.mostrarMensaje(mensaje);
                return;
            }

            int fila = vista.getTablaEmpleados().getSelectedRow();
            if (fila >= 0) {
                int id = Integer.parseInt(vista.getTablaEmpleados().getValueAt(fila, 0).toString());
                Empleado emp = buscarEmpleadoPorId(id);
                if (emp != null) {
                    vista.mostrarMensaje("El salario total de " + emp.getNombre() + " es: $" + emp.calcularSalario());
                    return;
                }
            }

            vista.mostrarError("Por favor, complete los campos (Salario Base y Ventas/Bono) o seleccione un empleado de la tabla.");

        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese valores numéricos válidos en Salario Base y Ventas/Bono para calcular.");
        }
    }

    /**
     * Edita los datos del empleado seleccionado previamente desde la tabla
     */
    private void editarEmpleado() {
        try {
            if (idEmpleadoSeleccionado == -1) {
                vista.mostrarError("Por favor, seleccione un empleado de la tabla para editar.");
                return;
            }

            if (camposVacios()) {
                vista.mostrarError("Por favor, complete todos los campos antes de editar.");
                return;
            }

            String nuevoNombre = vista.getTxtNombre().getText().trim();
            double nuevoSalarioBase = Double.parseDouble(vista.getTxtSalarioBase().getText().trim());
            String nuevoCargo = (String) vista.getCbTipoEmpleado().getSelectedItem();
            double nuevoDatoExtra = Double.parseDouble(vista.getTxtDatoExtra().getText().trim());

            if (nuevoSalarioBase < 0 || nuevoDatoExtra < 0) {
                vista.mostrarError("El salario base y las ventas/bono no pueden ser negativos.");
                return;
            }

            // Creamos el empleado actualizado conservando su ID original
            Empleado empleadoActualizado;
            if ("Cajero".equalsIgnoreCase(nuevoCargo)) {
                empleadoActualizado = new Cajero(nuevoNombre, idEmpleadoSeleccionado, nuevoSalarioBase, (int) nuevoDatoExtra);
            } else {
                empleadoActualizado = new Gerente(nuevoNombre, idEmpleadoSeleccionado, nuevoSalarioBase, nuevoDatoExtra);
            }

            List<Empleado> lista = servicio.getEmpleados();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getIdEmpleado() == idEmpleadoSeleccionado) {
                    lista.set(i, empleadoActualizado);
                    break;
                }
            }

            actualizarTabla();
            limpiarTodo();
            vista.mostrarMensaje("¡Empleado ID " + idEmpleadoSeleccionado + " editado con éxito!");

        } catch (NumberFormatException ex) {
            vista.mostrarError("Verifique los valores numéricos ingresados.");
        }
    }

    /**
     * Elimina el empleado seleccionado
     */
    private void eliminarEmpleado() {
        int fila = vista.getTablaEmpleados().getSelectedRow();
        int id = -1;

        if (fila >= 0) {
            id = Integer.parseInt(vista.getTablaEmpleados().getValueAt(fila, 0).toString());
        } else if (idEmpleadoSeleccionado != -1) {
            id = idEmpleadoSeleccionado;
        }

        if (id == -1) {
            vista.mostrarError("Por favor, seleccione un empleado de la tabla para eliminar.");
            return;
        }

        Empleado emp = buscarEmpleadoPorId(id);
        String nombre = (emp != null) ? emp.getNombre() : "ID " + id;

        int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de que desea eliminar a: " + nombre + " (ID: " + id + ")?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            final int idFinal = id;
            boolean eliminado = servicio.getEmpleados().removeIf(e -> e.getIdEmpleado() == idFinal);
            if (eliminado) {
                actualizarTabla();
                limpiarTodo();
                vista.mostrarMensaje("Empleado eliminado correctamente.");
            } else {
                vista.mostrarError("No se pudo eliminar el empleado.");
            }
        }
    }

    /**
     * Carga los datos de la fila seleccionada de la tabla a los campos de texto
     */
    private void cargarCamposDesdeTabla() {
        int fila = vista.getTablaEmpleados().getSelectedRow();
        if (fila >= 0) {
            idEmpleadoSeleccionado = Integer.parseInt(vista.getTablaEmpleados().getValueAt(fila, 0).toString());
            vista.getTxtNombre().setText(vista.getTablaEmpleados().getValueAt(fila, 1).toString());
            vista.getTxtSalarioBase().setText(vista.getTablaEmpleados().getValueAt(fila, 2).toString());
            String cargo = vista.getTablaEmpleados().getValueAt(fila, 3).toString();
            vista.getCbTipoEmpleado().setSelectedItem(cargo);
            vista.getTxtDatoExtra().setText(vista.getTablaEmpleados().getValueAt(fila, 4).toString());
        }
    }

    /**
     * Actualiza la JTable con los empleados registrados en el servicio
     */
    public void actualizarTabla() {
        String[] columnas = {"ID", "Nombre", "Salario Base", "Cargo", "Ventas/ Bono", "Salario Total"};

        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Empleado> lista = servicio.getEmpleados();

        if (lista != null) {
            for (Empleado emp : lista) {
                String cargo = (emp instanceof Gerente) ? "Gerente" : "Cajero";
                double salarioFinal = emp.calcularSalario();

                String datoExtraStr;
                if (emp instanceof Cajero) {
                    datoExtraStr = String.valueOf(((Cajero) emp).getVentasRealizadas());
                } else if (emp instanceof Gerente) {
                    datoExtraStr = String.valueOf(((Gerente) emp).getBonoGerencial());
                } else {
                    datoExtraStr = "-";
                }

                Object[] fila = {
                    emp.getIdEmpleado(),
                    emp.getNombre(),
                    emp.getSalarioBase(),
                    cargo,
                    datoExtraStr,
                    salarioFinal
                };
                modeloTabla.addRow(fila);
            }
        }

        vista.getTablaEmpleados().setModel(modeloTabla);
    }

    private Empleado buscarEmpleadoPorId(int id) {
        for (Empleado emp : servicio.getEmpleados()) {
            if (emp.getIdEmpleado() == id) {
                return emp;
            }
        }
        return null;
    }

    /**
     * Genera un ID automático único basado en los IDs existentes
     */
    private int generarIdAutomatico() {
        List<Empleado> lista = servicio.getEmpleados();
        if (lista == null || lista.isEmpty()) {
            return 1; // Si no hay nadie, arranca en 1
        }
        int maxId = 0;
        for (Empleado emp : lista) {
            if (emp.getIdEmpleado() > maxId) {
                maxId = emp.getIdEmpleado();
            }
        }
        return maxId + 1; // Retorna el siguiente número disponible
    }

    private boolean camposVacios() {
        return vista.getTxtNombre().getText().trim().isEmpty()
                || vista.getTxtSalarioBase().getText().trim().isEmpty()
                || vista.getTxtDatoExtra().getText().trim().isEmpty();
    }

    private void limpiarTodo() {
        vista.limpiarCampos();
        idEmpleadoSeleccionado = -1; // Reseteamos la selección
    }
}