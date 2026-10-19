package com.example.controlador;

import com.example.vista.FrmCliente;
import com.example.vista.FrmEmpleado;
import com.example.vista.FrmProducto;
import com.example.vista.FrmVenta;
import com.example.vista.FrmPrincipal;

public class CtrlPrincipal {

    // 1. Método que crea la instancia de FrmProducto y la muestra
    public void abrirProductos(FrmPrincipal menuActual) {
        FrmProducto vistaProd = new FrmProducto(null, true);
        vistaProd.setLocationRelativeTo(null); // Centra la ventana
        vistaProd.setVisible(true); // La muestra
        menuActual.dispose(); // 3. Cerramos FrmPrincipal 
    }

    // 2. Método que crea la instancia de FrmCliente y la muestra
    public void abrirClientes() {
        FrmCliente vistaCli = new FrmCliente(null, true);
        vistaCli.setLocationRelativeTo(null);
        vistaCli.setVisible(true);
    }

    // 3. Método que crea la instancia de FrmVenta y la muestra
    public void abrirVentas() {
        FrmVenta vistaVen = new FrmVenta(null, true);
        vistaVen.setLocationRelativeTo(null);
        vistaVen.setVisible(true);
    }

    // 4. Método que crea la instancia de FrmEmpleado y la muestra
   public void abrirEmpleados() {
        FrmEmpleado vistaEmp = new FrmEmpleado(null, true);
        vistaEmp.setLocationRelativeTo(null);
        vistaEmp.setVisible(true);
    }
}