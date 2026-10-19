package com.example.controlador;

import com.example.modelo.productos.Producto;
import com.example.service.SupermercadoService;
import com.example.vista.FrmPrincipal;
import com.example.vista.FrmProducto;
import java.util.List;
import java.util.UUID;

public class CtrlProducto {

    private SupermercadoService service = SupermercadoService.getInstancia();

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

       //Leer 
        public List<Producto> listarProductos(){
            return service.getProductosDisponibles();
        }
        
        //Crear
        public boolean agregarProducto(Producto prod){
            return service.agregarProducto(prod);
        }
        
        //Delete
        public boolean eliminarProducto(UUID id){
            return service.eliminarProducto(id);
        }
        
        //Update
        public boolean actualizarProducto(Producto modificado){
            return service.editarProducto(modificado);
        }
}
