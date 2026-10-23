package com.example.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Sistema de diseño y estilos UI/UX Web Dashboard Minimalista para Swing.
 * Paleta de colores SaaS moderna: Header oscuro (#0F172A), Fondo (#F1F5F9), Superficies (#FFFFFF),
 * Bordes (#E2E8F0), Inputs (#CBD5E1), Textos (#0F172A / #475569).
 */
public final class UITheme {

    // Paleta de colores estricta
    public static final Color COLOR_HEADER_BG = new Color(15, 23, 42);       // #0F172A (Slate/Carbón oscuro)
    public static final Color COLOR_HEADER_SUB = new Color(148, 163, 184);   // #94A3B8 (Gris azulado)
    public static final Color COLOR_BG_CANVAS = new Color(241, 245, 249);    // #F1F5F9 (Gris suave cálido)
    public static final Color COLOR_SURFACE = new Color(255, 255, 255);      // #FFFFFF (Blanco puro)
    public static final Color COLOR_BORDER = new Color(226, 232, 240);       // #E2E8F0 (Borde sutil)
    public static final Color COLOR_BORDER_INPUT = new Color(203, 213, 225); // #CBD5E1 (Borde input)

    // Tipografía y textos
    public static final Color COLOR_TEXT_MAIN = new Color(15, 23, 42);       // #0F172A (Gris carbón oscuro)
    public static final Color COLOR_TEXT_MUTED = new Color(71, 85, 105);     // #475569 (Gris medio secundario)
    public static final Color COLOR_TEXT_LIGHT = new Color(148, 163, 184);   // #94A3B8

    // Acciones y estados
    public static final Color COLOR_PRIMARY_DARK = new Color(15, 23, 42);    // #0F172A (Botón principal oscuro)
    public static final Color COLOR_PRIMARY_DARK_HOVER = new Color(30, 41, 59); // #1E293B
    public static final Color COLOR_SUCCESS = new Color(16, 185, 129);       // #10B981 (Verde esmeralda)
    public static final Color COLOR_SUCCESS_HOVER = new Color(5, 150, 105);  // #059669
    public static final Color COLOR_DANGER_BG = new Color(254, 242, 242);    // #FEF2F2 (Rojo suave)
    public static final Color COLOR_DANGER_TEXT = new Color(220, 38, 38);    // #DC2626 (Rojo coral)
    public static final Color COLOR_DANGER_BORDER = new Color(254, 202, 202);// #FECACA
    public static final Color COLOR_DANGER_HOVER = new Color(254, 226, 226); // #FEE2E2
    public static final Color COLOR_NEUTRAL_BTN = new Color(241, 245, 249);  // #F1F5F9 (Gris neutro)
    public static final Color COLOR_NEUTRAL_HOVER = new Color(226, 232, 240);// #E2E8F0
    public static final Color COLOR_NEUTRAL_TEXT = new Color(15, 23, 42);    // #0F172A

    // Fuentes
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_CARD_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TABLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 12);

    private UITheme() {}

    /**
     * Configuración base de ventana estándar JFrame para todas las vistas.
     */
    public static void setupWindow(JFrame frame, String title) {
        frame.setTitle(title);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(1024, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(COLOR_BG_CANVAS);
    }

    /**
     * Genera la Barra Superior Ejecutiva unificada (#0F172A, altura 75px).
     * @param title Título principal (ej: "SUPERMERCADO · Productos e Inventario")
     * @param subtitle Subtítulo descriptivo (ej: "Gestión de catálogo, precios y control de stock")
     * @param btnVolver Botón Volver opcional para integrarlo en el extremo derecho
     */
    public static JPanel createHeader(String title, String subtitle, JButton btnVolver) {
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 75));
        header.setBackground(COLOR_HEADER_BG);
        header.setBorder(new EmptyBorder(0, 40, 0, 40));

        // Título y Subtítulo a la izquierda
        JPanel pnlLeft = new JPanel();
        pnlLeft.setOpaque(false);
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(FONT_TITLE);
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(FONT_SUBTITLE);
        lblSubtitle.setForeground(COLOR_HEADER_SUB);

        pnlLeft.add(Box.createVerticalGlue());
        pnlLeft.add(lblTitle);
        pnlLeft.add(Box.createVerticalStrut(3));
        pnlLeft.add(lblSubtitle);
        pnlLeft.add(Box.createVerticalGlue());

        header.add(pnlLeft, BorderLayout.WEST);

        // Botón Volver a la derecha
        if (btnVolver != null) {
            styleHeaderBackButton(btnVolver);
            JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 18));
            pnlRight.setOpaque(false);
            pnlRight.add(btnVolver);
            header.add(pnlRight, BorderLayout.EAST);
        }

        return header;
    }

    /**
     * Panel contenedor estilo "Card" blanco con borde sutil de 1px y padding interno.
     */
    public static JPanel createCardPanel(int padTop, int padLeft, int padBottom, int padRight) {
        JPanel card = new JPanel();
        card.setBackground(COLOR_SURFACE);
        card.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(padTop, padLeft, padBottom, padRight)
        ));
        return card;
    }

    public static JPanel createCardPanel() {
        return createCardPanel(28, 28, 28, 28);
    }

    /**
     * Botón de Acción Principal oscuro (#0F172A) con texto blanco.
     */
    public static void stylePrimaryButton(JButton btn) {
        styleBaseButton(btn, COLOR_PRIMARY_DARK, Color.WHITE, COLOR_PRIMARY_DARK_HOVER, null);
    }

    /**
     * Botón Principal con color de acento personalizado (ej. para agregar al carrito).
     */
    public static void styleAccentButton(JButton btn, Color bg, Color hoverBg) {
        styleBaseButton(btn, bg, Color.WHITE, hoverBg, null);
    }

    /**
     * Botón de Confirmación Crítica (Cobrar Venta en Verde Esmeralda #10B981).
     */
    public static void styleSuccessButton(JButton btn) {
        styleBaseButton(btn, COLOR_SUCCESS, Color.WHITE, COLOR_SUCCESS_HOVER, null);
    }

    /**
     * Botón Destructivo (Eliminar, Quitar Seleccionado - #FEF2F2 con texto #DC2626).
     */
    public static void styleDangerButton(JButton btn) {
        styleBaseButton(btn, COLOR_DANGER_BG, COLOR_DANGER_TEXT, COLOR_DANGER_HOVER,
                new LineBorder(COLOR_DANGER_BORDER, 1, true));
    }

    /**
     * Botón Secundario (Editar, Confirmar Cambios, Calcular Salario - #F1F5F9 con texto #0F172A).
     */
    public static void styleSecondaryButton(JButton btn) {
        styleBaseButton(btn, COLOR_NEUTRAL_BTN, COLOR_NEUTRAL_TEXT, COLOR_NEUTRAL_HOVER,
                new LineBorder(COLOR_BORDER_INPUT, 1, true));
    }

    /**
     * Botón Volver estilizado para el Header con fondo blanco y letra negra de alta legibilidad.
     */
    public static void styleHeaderBackButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK); // Letra negra para máxima legibilidad
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 38));
        btn.setBorder(new CompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true), // #CBD5E1
                new EmptyBorder(6, 16, 6, 16)
        ));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btn.isEnabled()) {
                    btn.setBackground(new Color(241, 245, 249)); // #F1F5F9
                    btn.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        });
    }

    private static void styleBaseButton(JButton btn, Color bg, Color fg, Color hoverBg, Border border) {
        btn.setFont(FONT_BUTTON);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (border != null) {
            btn.setBorder(new CompoundBorder(border, new EmptyBorder(8, 16, 8, 16)));
        } else {
            btn.setBorderPainted(false);
            btn.setBorder(new EmptyBorder(9, 18, 9, 18));
        }

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btn.isEnabled()) {
                    btn.setBackground(hoverBg);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bg);
            }
        });
    }

    /**
     * Estilizado de campos de texto (JTextField).
     */
    public static void styleTextField(JTextField txt) {
        txt.setFont(FONT_INPUT);
        txt.setBackground(COLOR_SURFACE);
        txt.setForeground(COLOR_TEXT_MAIN);
        txt.setCaretColor(COLOR_TEXT_MAIN);
        txt.setPreferredSize(new Dimension(txt.getPreferredSize().width, 38));
        txt.setMinimumSize(new Dimension(50, 38));
        txt.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER_INPUT, 1, true),
                new EmptyBorder(6, 12, 6, 12)
        ));
    }

    /**
     * Estilizado de ComboBox (JComboBox).
     */
    public static void styleComboBox(JComboBox<?> cmb) {
        cmb.setFont(FONT_INPUT);
        cmb.setBackground(COLOR_SURFACE);
        cmb.setForeground(COLOR_TEXT_MAIN);
        cmb.setPreferredSize(new Dimension(cmb.getPreferredSize().width, 38));
        cmb.setMinimumSize(new Dimension(50, 38));
        cmb.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER_INPUT, 1, true),
                new EmptyBorder(4, 8, 4, 8)
        ));
    }

    /**
     * Estilizado de JSpinner.
     */
    public static void styleSpinner(JSpinner sp) {
        sp.setFont(FONT_INPUT);
        sp.setBackground(COLOR_SURFACE);
        sp.setPreferredSize(new Dimension(sp.getPreferredSize().width, 38));
        sp.setMinimumSize(new Dimension(50, 38));
        sp.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER_INPUT, 1, true),
                new EmptyBorder(4, 6, 4, 6)
        ));
        JComponent editor = sp.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setBackground(COLOR_SURFACE);
            tf.setForeground(COLOR_TEXT_MAIN);
            tf.setFont(FONT_INPUT);
            tf.setBorder(null);
        }
    }

    /**
     * Estilizado de etiquetas descriptivas secundarias (Labels de formularios).
     */
    public static void styleLabel(JLabel lbl) {
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_TEXT_MUTED); // #475569
    }

    /**
     * Estilizado completo de tablas (JTable + JScrollPane) tipo SaaS / Dashboard Web:
     * Cabecera #F8FAFC, texto #475569 bold, altura 42px.
     * Filas 38px, líneas horizontales #F1F5F9, sin grilla vertical, selección #EFF6FF con #1E293B.
     */
    public static void styleTable(JTable table, JScrollPane scroll) {
        table.setFont(FONT_TABLE);
        table.setForeground(COLOR_TEXT_MAIN);
        table.setBackground(COLOR_SURFACE);
        table.setRowHeight(38);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(241, 245, 249)); // #F1F5F9 líneas suaves
        table.setSelectionBackground(new Color(239, 246, 255)); // #EFF6FF
        table.setSelectionForeground(new Color(30, 41, 59)); // #1E293B
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        // Cabecera plana estilo web
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_TABLE_HEADER);
        header.setBackground(new Color(248, 250, 252)); // #F8FAFC
        header.setForeground(new Color(71, 85, 105));   // #475569
        header.setPreferredSize(new Dimension(0, 42));
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                lbl.setFont(FONT_TABLE_HEADER);
                lbl.setBackground(new Color(248, 250, 252));
                lbl.setForeground(new Color(71, 85, 105));
                lbl.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDER),
                        new EmptyBorder(0, 16, 0, 16)
                ));
                return lbl;
            }
        });

        // Renderer de celdas con padding y líneas alternadas
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    ((JLabel) c).setBorder(new EmptyBorder(0, 16, 0, 16));
                }
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? COLOR_SURFACE : new Color(248, 250, 252));
                }
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, cellRenderer);

        // Estilizado del JScrollPane
        scroll.setBackground(COLOR_SURFACE);
        scroll.getViewport().setBackground(COLOR_SURFACE);
        scroll.setBorder(new LineBorder(COLOR_BORDER, 1, true));
    }
}
