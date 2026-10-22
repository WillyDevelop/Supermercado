package com.example.controlador;

import com.example.modelo.productos.Producto;
import com.example.modelo.productos.ProductoAlimenticio;
import com.example.modelo.productos.ProductoElectronico;
import com.example.modelo.productos.ProductoHigiene;
import com.example.modelo.productos.TipoDeUso;
import com.example.service.SupermercadoService;
import com.example.vista.FrmPrincipal;
import com.example.vista.FrmProducto;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CtrlProducto {

    private SupermercadoService service = SupermercadoService.getInstancia();

    public CtrlProducto() {
        if (service.getProductosDisponibles().isEmpty()) {
            service.agregarProducto(new ProductoAlimenticio(
                    "Arroz Gallo Oro", 1500, "Alimenticio", LocalDate.now().plusDays(30), true, 20));
            service.agregarProducto(new ProductoAlimenticio(
                    "Lentejas", 1000, "Alimenticio", LocalDate.now().plusDays(45), true, 20));
            service.agregarProducto(new ProductoElectronico(
                    "Auriculares Bluetooth", 25000, "Electrónico", 1, 15));
            service.agregarProducto(new ProductoElectronico(
                    "Monitor 24 pulgadas", 185000, "Electrónico", 2, 25));
            service.agregarProducto(new ProductoHigiene(
                    "Shampoo Anticaspa", 3500, "Higiene", TipoDeUso.PERSONAL, 10));
            service.agregarProducto(new ProductoHigiene(
                    "Detergente Líquido", 2800, "Higiene", TipoDeUso.DOMESTICO, 11));
        }
    }

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
