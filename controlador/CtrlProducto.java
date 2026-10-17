package com.example.controlador;

import com.example.vista.FrmPrincipal;
import com.example.vista.FrmProducto;

public class CtrlProducto {

    // Recibe "vistaProdActual" (que es FrmProducto) para poder cerrarla
    public void volverAlMenu(FrmProducto vistaProdActual) {
        
        // 1. Creamos la instancia de FrmPrincipal (con FALSE)
        FrmPrincipal menu = new FrmPrincipal(null, false);
        
        // 2. La centramos y la mostramos
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        
        // 3. Cerramos FrmProducto para que solo quede FrmPrincipal
        vistaProdActual.dispose();
    }
}