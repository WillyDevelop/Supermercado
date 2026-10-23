package com.example.vista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Dashboard Ejecutivo / Panel de Control Minimalista para Supermercado.
 * Diseño 100% tipográfico y limpio inspirado en Stripe / Linear / Vercel.
 */
public class FrmPrincipal extends JFrame {

    private final com.example.controlador.CtrlPrincipal ctrl = new com.example.controlador.CtrlPrincipal();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPrincipal.class.getName());

    // Variables de componentes para compatibilidad
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton4;
    private JButton jButton5_empleados;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public FrmPrincipal() {
        this(null, false);
    }

    public FrmPrincipal(java.awt.Frame parent, boolean modal) {
        super();
        initComponents();
    }

    private void initComponents() {
        // 1. Configuración de Ventana Completa (Minimizar, Maximizar, Cerrar)
        setTitle("Supermercado - Panel de Control & Operaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1024, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);

        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(241, 245, 249)); // #F1F5F9

        // =====================================================================
        // 2. HEADER / BARRA SUPERIOR EJECUTIVA (NORTH) - Altura 80px, #0F172A
        // =====================================================================
        jPanel1 = new JPanel(new BorderLayout());
        jPanel1.setPreferredSize(new Dimension(0, 80));
        jPanel1.setBackground(new Color(15, 23, 42)); // Slate/Carbón oscuro #0F172A
        jPanel1.setBorder(new EmptyBorder(0, 48, 0, 48));

        // Título y Subtítulo a la izquierda
        JPanel pnlHeaderLeft = new JPanel();
        pnlHeaderLeft.setOpaque(false);
        pnlHeaderLeft.setLayout(new BoxLayout(pnlHeaderLeft, BoxLayout.Y_AXIS));

        jLabel1 = new JLabel("SUPERMERCADO");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jLabel1.setForeground(Color.WHITE);

        JLabel lblSubheader = new JLabel("Panel de Control & Operaciones");
        lblSubheader.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubheader.setForeground(new Color(148, 163, 184)); // Gris azulado #94A3B8

        pnlHeaderLeft.add(Box.createVerticalGlue());
        pnlHeaderLeft.add(jLabel1);
        pnlHeaderLeft.add(Box.createVerticalStrut(3));
        pnlHeaderLeft.add(lblSubheader);
        pnlHeaderLeft.add(Box.createVerticalGlue());

        jPanel1.add(pnlHeaderLeft, BorderLayout.WEST);

        // Indicador Badge a la derecha: "● Sistema Activo" en verde esmeralda
        JPanel pnlHeaderRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 24));
        pnlHeaderRight.setOpaque(false);

        JPanel pnlBadge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(6, 78, 59, 180));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(new Color(16, 185, 129, 140));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        pnlBadge.setOpaque(false);
        pnlBadge.setBorder(new EmptyBorder(6, 14, 6, 14));

        JLabel lblBadge = new JLabel("● Sistema Activo");
        lblBadge.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBadge.setForeground(new Color(16, 185, 129)); // Verde esmeralda #10B981
        pnlBadge.add(lblBadge);

        pnlHeaderRight.add(pnlBadge);
        jPanel1.add(pnlHeaderRight, BorderLayout.EAST);

        getContentPane().add(jPanel1, BorderLayout.NORTH);

        // =====================================================================
        // 3. CUERPO PRINCIPAL DEL DASHBOARD (CENTER) - Fondo #F1F5F9
        // =====================================================================
        JPanel pnlCenterWrapper = new JPanel(new GridBagLayout());
        pnlCenterWrapper.setBackground(new Color(241, 245, 249)); // #F1F5F9

        JPanel pnlDashboard = new JPanel(new BorderLayout(0, 28));
        pnlDashboard.setOpaque(false);

        // Sección de Bienvenida (Arriba)
        JPanel pnlWelcome = new JPanel();
        pnlWelcome.setOpaque(false);
        pnlWelcome.setLayout(new BoxLayout(pnlWelcome, BoxLayout.Y_AXIS));

        JLabel lblWelcomeTitle = new JLabel("Módulos del Sistema");
        lblWelcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblWelcomeTitle.setForeground(new Color(30, 41, 59)); // Texto oscuro #1E293B

        JLabel lblWelcomeDesc = new JLabel("Selecciona un módulo operativo para gestionar el inventario, clientes, caja o personal.");
        lblWelcomeDesc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblWelcomeDesc.setForeground(new Color(100, 116, 139)); // Gris medio #64748B

        pnlWelcome.add(lblWelcomeTitle);
        pnlWelcome.add(Box.createVerticalStrut(6));
        pnlWelcome.add(lblWelcomeDesc);
        pnlDashboard.add(pnlWelcome, BorderLayout.NORTH);

        // Grilla de Módulos (Abajo) - GridLayout(2, 2, 32, 32)
        JPanel pnlGrid = new JPanel(new GridLayout(2, 2, 32, 32));
        pnlGrid.setOpaque(false);

        // 1. PRODUCTOS (Eyebrow Verde Esmeralda)
        jButton1 = new BentoCardButton(
                "MÓDULO 01 · INVENTARIO",
                "Productos e Inventario",
                "Control de catálogo, precios, categorías y stock disponible.",
                new Color(5, 150, 105) // #059669
        );
        jButton1.addActionListener(this::jButton1ActionPerformed);

        // 2. CLIENTES (Eyebrow Violeta/Índigo)
        jButton2 = new BentoCardButton(
                "MÓDULO 02 · FIDELIZACIÓN",
                "Clientes y Socios VIP",
                "Administración de padrón de clientes y descuentos exclusivos.",
                new Color(124, 58, 237) // #7C3AED
        );
        jButton2.addActionListener(this::jButton2ActionPerformed);

        // 3. VENTAS (Eyebrow Naranja/Ámbar)
        jButton4 = new BentoCardButton(
                "MÓDULO 03 · FACTURACIÓN",
                "Caja y Punto de Venta",
                "Facturación rápida, carga de carrito y cobro de operaciones.",
                new Color(217, 119, 6) // #D97706
        );
        jButton4.addActionListener(this::jButton4ActionPerformed);

        // 4. EMPLEADOS (Eyebrow Azul Petróleo)
        jButton5_empleados = new BentoCardButton(
                "MÓDULO 04 · PERSONAL",
                "Recursos Humanos y Salarios",
                "Gestión de personal, cargos, bonos por ventas y liquidación.",
                new Color(2, 132, 199) // #0284C7
        );
        jButton5_empleados.addActionListener(this::jButton5_empleadosActionPerformed);

        pnlGrid.add(jButton1);
        pnlGrid.add(jButton2);
        pnlGrid.add(jButton4);
        pnlGrid.add(jButton5_empleados);
        pnlDashboard.add(pnlGrid, BorderLayout.CENTER);

        // Centrado responsivo con márgenes generosos
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(36, 48, 48, 48);
        pnlCenterWrapper.add(pnlDashboard, gbc);

        getContentPane().add(pnlCenterWrapper, BorderLayout.CENTER);

        pack();
    }

    // =========================================================================
    // ACCIONES DE NAVEGACIÓN (INTACTAS)
    // =========================================================================
    private void jButton1ActionPerformed(ActionEvent evt) {
        ctrl.abrirProductos(this);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        ctrl.abrirClientes();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        ctrl.abrirVentas();
    }

    private void jButton5_empleadosActionPerformed(ActionEvent evt) {
        ctrl.abrirEmpleados();
    }

    // =========================================================================
    // COMPONENTE TARJETA BENTO 100% TIPOGRÁFICA Y MINIMALISTA (SIN ÍCONOS)
    // =========================================================================
    public static class BentoCardButton extends JButton {

        private final String eyebrow;
        private final String cardTitle;
        private final String cardSubtitle;
        private final Color accentColor;
        private boolean isHovered = false;

        public BentoCardButton(String eyebrow, String cardTitle, String cardSubtitle, Color accentColor) {
            this.eyebrow = eyebrow;
            this.cardTitle = cardTitle;
            this.cardSubtitle = cardSubtitle;
            this.accentColor = accentColor;

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(480, 210));
            setMinimumSize(new Dimension(360, 190));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // 1. Fondo de la tarjeta (Blanco puro #FFFFFF / Hover #F8FAFC)
            g2.setColor(isHovered ? new Color(248, 250, 252) : Color.WHITE);
            g2.fillRoundRect(2, 2, w - 4, h - 4, 18, 18);

            // 2. Borde (Normal: 1px #E2E8F0 | Hover: 2px color de acento)
            if (isHovered) {
                g2.setColor(accentColor);
                g2.setStroke(new BasicStroke(2.0f));
            } else {
                g2.setColor(new Color(226, 232, 240)); // #E2E8F0
                g2.setStroke(new BasicStroke(1.0f));
            }
            g2.drawRoundRect(2, 2, w - 5, h - 5, 18, 18);

            // 3. Barra de acento superior sutil (4px)
            g2.setColor(accentColor);
            g2.fillRoundRect(30, 2, 44, 4, 2, 2);

            int padX = 32;
            int padY = 34;

            // 4. Etiqueta superior Eyebrow/Tag en mayúsculas
            g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
            g2.drawString(eyebrow, padX, padY + 12);

            // 5. Flecha interactiva "→" en la esquina derecha
            int arrowX = isHovered ? w - padX - 10 : w - padX - 14;
            g2.setColor(isHovered ? accentColor : new Color(148, 163, 184)); // #94A3B8
            g2.setFont(new Font("Segoe UI", Font.BOLD, 22));
            g2.drawString("→", arrowX, padY + 36);

            // 6. Título del módulo (22px Bold, #0F172A)
            g2.setColor(new Color(15, 23, 42)); // #0F172A
            g2.setFont(new Font("Segoe UI", Font.BOLD, 22));
            int titleY = padY + 46;
            g2.drawString(cardTitle, padX, titleY);

            // 7. Descripción del módulo (14px Regular, #64748B)
            g2.setColor(new Color(100, 116, 139)); // #64748B
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            drawMultilineText(g2, cardSubtitle, padX, titleY + 26, w - (padX * 2) - 20);

            g2.dispose();
        }

        private void drawMultilineText(Graphics2D g2, String text, int x, int y, int maxWidth) {
            FontMetrics fm = g2.getFontMetrics();
            String[] words = text.split(" ");
            StringBuilder currentLine = new StringBuilder();
            int lineY = y;
            int lineHeight = fm.getHeight() + 3;

            for (String word : words) {
                String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
                if (fm.stringWidth(testLine) > maxWidth && currentLine.length() > 0) {
                    g2.drawString(currentLine.toString(), x, lineY);
                    lineY += lineHeight;
                    currentLine = new StringBuilder(word);
                } else {
                    currentLine = new StringBuilder(testLine);
                }
            }
            if (currentLine.length() > 0) {
                g2.drawString(currentLine.toString(), x, lineY);
            }
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            FrmPrincipal dialog = new FrmPrincipal(new JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
}
