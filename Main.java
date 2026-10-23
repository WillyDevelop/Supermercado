package com.example;

import com.example.service.SupermercadoService;
import com.example.util.CargaDatosPrueba;
import com.example.vista.FrmPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos el servicio principal y cargamos los datos de prueba
        SupermercadoService supermercado = new SupermercadoService();
        CargaDatosPrueba.cargar(supermercado);
        System.out.println(">>> Datos de prueba cargados correctamente en memoria.");

        // 2. Lanzamos la interfaz gráfica en el hilo de eventos de Swing (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Aplica el estilo visual nativo del sistema operativo
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            FrmPrincipal principal = new FrmPrincipal(null, false);
            principal.setLocationRelativeTo(null);
            principal.setVisible(true);
        });
    }
}

