package com.example.vista;

import com.example.controlador.CtrlVenta;
import com.example.modelo.excepciones.ClienteNoRegistradoException;
import com.example.modelo.excepciones.ProductoNoDisponibleException;
import com.example.modelo.clientes.Cliente;
import com.example.modelo.clientes.ClienteVIP;
import com.example.modelo.productos.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmVenta extends javax.swing.JFrame {

    // controlador y listas
    private CtrlVenta ctrlVenta = new CtrlVenta();
    private List<Cliente> listaClientes;
    private List<Producto> listaProductosDisponibles;
    private DefaultTableModel modeloTabla;

    public FrmVenta() {
        this(null, false);
    }

    public FrmVenta(java.awt.Frame parent, boolean modal) {
        super();
        initComponents();
        configurarPersonalizado();
        aplicarEstiloModerno();
        cargarDatos();
    }

    private void configurarPersonalizado() {
        setLocationRelativeTo(null);
        setTitle("Supermercado - Caja y Ventas");
        setResizable(false);

        // Modelo de la tabla del carrito
        modeloTabla = new DefaultTableModel(
                new Object[] { "#", "Producto", "Categoría", "Precio Unit.", "Precio Final" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCarrito.setModel(modeloTabla);
    }

    private void cargarDatos() {
        cargarClientes();
        cargarProductos();
        actualizarTotales();
    }

    // vista pide los datos al controlador y este los obtiene del servicio
    private void cargarClientes() {
        cbClientes.removeAllItems();
        listaClientes = ctrlVenta.obtenerClientes();
        if (listaClientes == null || listaClientes.isEmpty()) {
            cbClientes.addItem("-- No hay clientes registrados --");
        } else {
            for (Cliente c : listaClientes) {
                String etiqueta = c.getNombre() + " (DNI: " + c.getDni() + ")"
                        + (c instanceof ClienteVIP ? " [Socio VIP]" : "");
                cbClientes.addItem(etiqueta);
            }
        }
        actualizarEtiquetaCliente();
    }

    private void cargarProductos() {
        cbProductos.removeAllItems();
        listaProductosDisponibles = ctrlVenta.obtenerProductosDisponibles();
        if (listaProductosDisponibles == null || listaProductosDisponibles.isEmpty()) {
            cbProductos.addItem("-- Sin productos en inventario --");
        } else {
            for (Producto p : listaProductosDisponibles) {
                cbProductos.addItem(p.getNombre() + " - $" + p.getPrecio() + " [Stock: " + p.getStock() + "] ("
                        + p.getCategoria() + ")");
            }
        }
    }

    private Cliente getClienteSeleccionado() {
        int idx = cbClientes.getSelectedIndex();
        if (listaClientes != null && idx >= 0 && idx < listaClientes.size()) {
            return listaClientes.get(idx);
        }
        return null;
    }

    private Producto getProductoSeleccionado() {
        int idx = cbProductos.getSelectedIndex();
        if (listaProductosDisponibles != null && idx >= 0 && idx < listaProductosDisponibles.size()) {
            return listaProductosDisponibles.get(idx);
        }
        return null;
    }

    private void actualizarEtiquetaCliente() {
        Cliente c = getClienteSeleccionado();
        if (c instanceof ClienteVIP) {
            lblTipoCliente.setText("Tipo: Socio VIP (Aplica desc. especial)");
            lblTipoCliente.setForeground(new java.awt.Color(0, 128, 0));
        } else if (c != null) {
            lblTipoCliente.setText("Tipo: Cliente Regular");
            lblTipoCliente.setForeground(java.awt.Color.DARK_GRAY);
        } else {
            lblTipoCliente.setText("Tipo: Sin cliente");
            lblTipoCliente.setForeground(java.awt.Color.GRAY);
        }
        actualizarTotales();
    }

    private void actualizarTotales() {
        Cliente c = getClienteSeleccionado();
        double subtotal = ctrlVenta.calcularSubtotal();
        double total = ctrlVenta.calcularTotal(c);

        lblSubtotal.setText(String.format("Subtotal: $%.2f", subtotal));
        lblTotal.setText(String.format("TOTAL: $%.2f", total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCabecera = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        cbClientes = new javax.swing.JComboBox<>();
        lblTipoCliente = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        cbProductos = new javax.swing.JComboBox<>();
        lblCantidad = new javax.swing.JLabel();
        spCantidad = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        panelPie = new javax.swing.JPanel();
        btnEliminarItem = new javax.swing.JButton();
        lblSubtotal = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnFinalizarVenta = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Supermercado - Caja y Ventas");
        setResizable(false);

        panelCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder("Punto de Venta / Cargar Carrito"));

        lblCliente.setText("Cliente:");

        cbClientes.addActionListener(this::cbClientesActionPerformed);

        lblTipoCliente.setText("Tipo: Regular");

        lblProducto.setText("Producto:");

        lblCantidad.setText("Cant:");

        spCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));

        btnAgregar.setText("+ Agregar al Carrito");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
        panelCabecera.setLayout(panelCabeceraLayout);
        panelCabeceraLayout.setHorizontalGroup(
                panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblCliente)
                                        .addComponent(lblProducto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                                .addComponent(cbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                                .addComponent(cbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblCantidad)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(14, Short.MAX_VALUE)));
        panelCabeceraLayout.setVerticalGroup(
                panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCliente)
                                        .addComponent(cbClientes, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTipoCliente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblProducto)
                                        .addComponent(cbProductos, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCantidad)
                                        .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAgregar))
                                .addContainerGap(14, Short.MAX_VALUE)));

        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "#", "Producto", "Categoría", "Precio Unit.", "Precio Final"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCarrito);

        btnEliminarItem.setText("Quitar Seleccionado");
        btnEliminarItem.addActionListener(this::btnEliminarItemActionPerformed);

        lblSubtotal.setText("Subtotal: $0.00");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 102, 0));
        lblTotal.setText("TOTAL: $0.00");

        btnFinalizarVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFinalizarVenta.setText("Cobrar Venta");
        btnFinalizarVenta.addActionListener(this::btnFinalizarVentaActionPerformed);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        javax.swing.GroupLayout panelPieLayout = new javax.swing.GroupLayout(panelPie);
        panelPie.setLayout(panelPieLayout);
        panelPieLayout.setHorizontalGroup(
                panelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPieLayout.createSequentialGroup()
                                .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16,
                                        Short.MAX_VALUE)
                                .addComponent(btnFinalizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        panelPieLayout.setVerticalGroup(
                panelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSubtotal)
                                .addComponent(lblTotal)
                                .addComponent(btnFinalizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(panelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1)
                                        .addComponent(panelPie, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(16, 16, 16)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelPie, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbClientesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbClientesActionPerformed
        actualizarEtiquetaCliente();
    }// GEN-LAST:event_cbClientesActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarActionPerformed
        Producto p = getProductoSeleccionado();
        Cliente c = getClienteSeleccionado();

        if (p == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto válido.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // validación de stock
        int yaEnCarrito = 0;
        for (Producto prodCarrito : ctrlVenta.obtenerCarrito()) {
            if (prodCarrito.getCodigo().equals(p.getCodigo())) {
                yaEnCarrito++;
            }
        }

        int cantidad = (Integer) spCantidad.getValue();
        if (yaEnCarrito + cantidad > p.getStock()) {
            JOptionPane.showMessageDialog(this,
                    "Stock insuficiente para '" + p.getNombre() + "'.\n"
                            + "Stock disponible: " + p.getStock() + "\n"
                            + "Ya en carrito: " + yaEnCarrito + "\n"
                            + "Cantidad solicitada: " + cantidad,
                    "Stock Insuficiente",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // agrego productos al carrito
        for (int i = 0; i < cantidad; i++) { // si quiero compro 3 unidades, esto se repite 3 veces
            ctrlVenta.agregarAlCarrito(p);
            modeloTabla.addRow(new Object[] {
                    modeloTabla.getRowCount() + 1,
                    p.getNombre(),
                    p.getCategoria(),
                    "$" + String.format("%.2f", p.getPrecio()),
                    "$" + String.format("%.2f", p.calcularPrecioFinal())
            });
        }
        actualizarTotales();
    }// GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarItemActionPerformed
        int fila = tablaCarrito.getSelectedRow();
        if (fila >= 0) {
            ctrlVenta.eliminarDelCarrito(fila);
            modeloTabla.removeRow(fila);

            // Renumerar columnas de orden
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                modeloTabla.setValueAt(i + 1, i, 0);
            }
            actualizarTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto del carrito para quitar.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_btnEliminarItemActionPerformed

    private void btnFinalizarVentaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFinalizarVentaActionPerformed
        Cliente cliente = getClienteSeleccionado();
        try {
            // 1. delega la logica al controlador
            ctrlVenta.finalizarVenta(cliente);

            // 2. muestra mensaje de exito
            JOptionPane.showMessageDialog(this,
                    "¡Venta realizada con éxito!\nSe actualizaron las compras del cliente y el stock del inventario.",
                    "Venta Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            // Limpiamos la tabla visual
            modeloTabla.setRowCount(0);

            // Recargamos los productos disponibles porque los vendidos se descontaron
            cargarProductos();
            actualizarTotales();

        } catch (ClienteNoRegistradoException | ProductoNoDisponibleException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en Venta", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnFinalizarVentaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnVolverActionPerformed
        ctrlVenta.volverAlMenu(this);
    }// GEN-LAST:event_btnVolverActionPerformed

    private void aplicarEstiloModerno() {
        UITheme.setupWindow(this, "Supermercado - Punto de Venta y Caja");

        // Estilos de botones
        UITheme.stylePrimaryButton(btnAgregar);
        btnAgregar.setPreferredSize(new java.awt.Dimension(180, 40));

        UITheme.styleSuccessButton(btnFinalizarVenta);
        btnFinalizarVenta.setPreferredSize(new java.awt.Dimension(160, 42));

        UITheme.styleDangerButton(btnEliminarItem);
        btnEliminarItem.setPreferredSize(new java.awt.Dimension(180, 40));

        // Estilos de inputs
        UITheme.styleComboBox(cbClientes);
        UITheme.styleComboBox(cbProductos);
        UITheme.styleSpinner(spCantidad);

        // Estilos de labels
        UITheme.styleLabel(lblCliente);
        UITheme.styleLabel(lblProducto);
        UITheme.styleLabel(lblCantidad);

        lblTipoCliente.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        lblTipoCliente.setForeground(new java.awt.Color(71, 85, 105)); // #475569

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        lblSubtotal.setForeground(new java.awt.Color(100, 116, 139)); // #64748B

        lblTotal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        lblTotal.setForeground(new java.awt.Color(5, 150, 105)); // #059669

        // Estilo de tabla
        UITheme.styleTable(tablaCarrito, jScrollPane1);

        // --- REESTRUCTURAR LAYOUT RESPONSIVO ---
        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().setBackground(UITheme.COLOR_BG_CANVAS);

        // 1. Header / Barra Superior Ejecutiva (#0F172A, 75px) con botón Volver
        // integrado
        javax.swing.JPanel pnlTop = UITheme.createHeader(
                "SUPERMERCADO · Caja y Punto de Venta",
                "Registro de operaciones, carrito y facturación",
                btnVolver);
        getContentPane().add(pnlTop, java.awt.BorderLayout.NORTH);

        // 2. Cuerpo Principal (#F1F5F9 con padding de 32px a 40px)
        javax.swing.JPanel pnlCanvas = new javax.swing.JPanel(new java.awt.BorderLayout(0, 20));
        pnlCanvas.setBackground(UITheme.COLOR_BG_CANVAS);
        pnlCanvas.setBorder(new javax.swing.border.EmptyBorder(32, 40, 36, 40));

        // --- Tarjeta Superior (Cargar Carrito) ---
        javax.swing.JPanel cardCabecera = UITheme.createCardPanel(24, 28, 24, 28);
        cardCabecera.setLayout(new java.awt.BorderLayout(0, 16));

        javax.swing.JLabel lblCabeceraTitle = new javax.swing.JLabel("Cargar Productos al Carrito");
        lblCabeceraTitle.setFont(UITheme.FONT_CARD_TITLE);
        lblCabeceraTitle.setForeground(UITheme.COLOR_TEXT_MAIN);
        cardCabecera.add(lblCabeceraTitle, java.awt.BorderLayout.NORTH);

        javax.swing.JPanel pnlGrid = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlGrid.setOpaque(false);
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(6, 8, 6, 8);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        // Fila 0: Cliente
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        pnlGrid.add(lblCliente, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        cbClientes.setPreferredSize(new java.awt.Dimension(0, 38));
        pnlGrid.add(cbClientes, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        pnlGrid.add(lblTipoCliente, gbc);

        // Fila 1: Producto, Cantidad y Agregar
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        pnlGrid.add(lblProducto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.6;
        cbProductos.setPreferredSize(new java.awt.Dimension(0, 38));
        pnlGrid.add(cbProductos, gbc);

        javax.swing.JPanel pnlCantYBtn = new javax.swing.JPanel(
                new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 0));
        pnlCantYBtn.setOpaque(false);
        pnlCantYBtn.add(lblCantidad);
        spCantidad.setPreferredSize(new java.awt.Dimension(70, 38));
        pnlCantYBtn.add(spCantidad);
        pnlCantYBtn.add(btnAgregar);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.4;
        pnlGrid.add(pnlCantYBtn, gbc);

        cardCabecera.add(pnlGrid, java.awt.BorderLayout.CENTER);
        pnlCanvas.add(cardCabecera, java.awt.BorderLayout.NORTH);

        // --- Tarjeta Central (Tabla del Carrito) ---
        javax.swing.JPanel cardTabla = UITheme.createCardPanel(24, 28, 24, 28);
        cardTabla.setLayout(new java.awt.BorderLayout(0, 14));

        javax.swing.JLabel lblTablaTitle = new javax.swing.JLabel("Detalle del Carrito de Compras");
        lblTablaTitle.setFont(UITheme.FONT_CARD_TITLE);
        lblTablaTitle.setForeground(UITheme.COLOR_TEXT_MAIN);
        cardTabla.add(lblTablaTitle, java.awt.BorderLayout.NORTH);
        cardTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlCanvas.add(cardTabla, java.awt.BorderLayout.CENTER);

        // --- Tarjeta Inferior (Barra de Totales y Cobro) ---
        javax.swing.JPanel cardPie = UITheme.createCardPanel(20, 28, 20, 28);
        cardPie.setLayout(new java.awt.BorderLayout(16, 0));

        // Izquierda: Quitar seleccionado
        cardPie.add(btnEliminarItem, java.awt.BorderLayout.WEST);

        // Centro/Derecha: Subtotal, TOTAL grande y Cobrar Venta
        javax.swing.JPanel pnlTotales = new javax.swing.JPanel(
                new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 24, 0));
        pnlTotales.setOpaque(false);
        pnlTotales.add(lblSubtotal);
        pnlTotales.add(lblTotal);
        pnlTotales.add(btnFinalizarVenta);

        cardPie.add(pnlTotales, java.awt.BorderLayout.EAST);
        pnlCanvas.add(cardPie, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlCanvas, java.awt.BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnFinalizarVenta;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbClientes;
    private javax.swing.JComboBox<String> cbProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTipoCliente;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelPie;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JTable tablaCarrito;
    // End of variables declaration//GEN-END:variables
}
