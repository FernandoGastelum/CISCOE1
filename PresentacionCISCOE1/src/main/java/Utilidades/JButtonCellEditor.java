package Utilidades;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class JButtonCellEditor implements TableCellEditor {

    private final JButton button;
    private int row;
    private ActionListener actionListener;
    private String textoFijo;

    public JButtonCellEditor(String textoFijo, ActionListener actionListener) {
        this.button = new JButton();
        this.textoFijo = textoFijo;
        this.actionListener = actionListener;

        this.button.addActionListener((ActionEvent evt) -> {
            this.actionListener.actionPerformed(
                new ActionEvent(this.button, ActionEvent.ACTION_PERFORMED, String.valueOf(this.row))
            );
        });
    }

    public JButtonCellEditor(ActionListener actionListener) {
        this(null, actionListener); // Sin texto fijo
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        
        // Si no se especificó texto fijo, usa el valor de la celda
        if (textoFijo == null || textoFijo.trim().isEmpty()) {
            button.setText(value != null ? value.toString() : "");
        } else {
            button.setText(textoFijo);
        }

        return this.button;
    }

    @Override public Object getCellEditorValue() { return true; }
    @Override public boolean isCellEditable(EventObject anEvent) { return true; }
    @Override public boolean shouldSelectCell(EventObject anEvent) { return true; }
    @Override public boolean stopCellEditing() { return true; }
    @Override public void cancelCellEditing() {}
    @Override public void addCellEditorListener(CellEditorListener l) {}
    @Override public void removeCellEditorListener(CellEditorListener l) {}
}
