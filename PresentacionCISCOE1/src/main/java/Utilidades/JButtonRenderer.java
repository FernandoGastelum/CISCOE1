package Utilidades;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JButtonRenderer implements TableCellRenderer {

    private final String textoFijo;

    public JButtonRenderer(String textoFijo) {
        this.textoFijo = textoFijo;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JButton button = new JButton();

        // Usar texto de la celda si no hay texto fijo
        if (textoFijo == null || textoFijo.trim().isEmpty()) {
            button.setText(value != null ? value.toString() : "");
        } else {
            button.setText(textoFijo);
        }

        return button;
    }
}
