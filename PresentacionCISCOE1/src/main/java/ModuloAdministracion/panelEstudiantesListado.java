/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.EstudianteDTO;
import DTOs.EstudianteTablaDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import Utilidades.JButtonCellEditor;
import Utilidades.JButtonRenderer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Knocmare
 */
public class panelEstudiantesListado extends javax.swing.JPanel {

    private IEstudianteNegocio estudianteNegocio;
    private ICarreraNegocio carreraNegocio;
    private List<EstudianteTablaDTO> estudiantesLista;

    /**
     * Creates new form panelListadoEstudiantes
     *
     * @param estudianteNegocio
     */
    public panelEstudiantesListado(IEstudianteNegocio estudianteNegocio, ICarreraNegocio carreraNegocio) {
        this.estudianteNegocio = estudianteNegocio;
        this.carreraNegocio = carreraNegocio;
        initComponents();
        this.metodosIniciales();
    }

    private void metodosIniciales() {
        this.configuracionInicialTabla();
        this.buscarTabla();
    }

    private void configuracionInicialTabla() {
        ActionListener onEditarClickListener = (ActionEvent e) -> {
            try {
                //Metodo para editar
                editar();
            } catch (NegocioException ex) {
                Logger.getLogger(panelEstudiantesListado.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        int indiceColumnaEditar = 3;
        TableColumnModel modeloColumnas = this.tablaEstudiantes.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar", onEditarClickListener));

        ActionListener onEliminarClickListener = (ActionEvent e) -> {
            //Metodo para eliminar
            eliminar();
            metodosIniciales();
        };
        int indiceColumnaEliminar = 4;
        modeloColumnas = this.tablaEstudiantes.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private void editar() throws NegocioException {
        Long id = this.getIdSeleccionadoTabla();
        panelEstudianteEditar panelEstudiante = new panelEstudianteEditar(estudianteNegocio, carreraNegocio, id);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelEstudiante, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void eliminar() {
        Long id = this.getIdSeleccionadoTabla();
        System.out.println("El id que se va a eliminar es " + id);

        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas eliminar el estudiante con el id institucional: " + id + "?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                estudianteNegocio.eliminar(id);
                JOptionPane.showMessageDialog(this, "Estudiante eliminado con éxito con el id institucional: " + id);
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el estudiante: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
        }
    }

    private Long getIdSeleccionadoTabla() {
        int indiceFilaSeleccionada = this.tablaEstudiantes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {

            EstudianteTablaDTO estudianteSeleccionado = estudiantesLista.get(indiceFilaSeleccionada);

            return estudianteSeleccionado.getIdEstudiante();
        } else {
            return 0L;
        }
    }

    private void buscarTabla() {
        try {
            List<EstudianteTablaDTO> estudiantesTablaLista = this.estudianteNegocio.obtenerTabla();
            DefaultTableModel modelo = (DefaultTableModel) this.tablaEstudiantes.getModel();
            modelo.setRowCount(0);
            this.agregarRegistrosTabla(estudiantesTablaLista);
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void agregarRegistrosTabla(List<EstudianteTablaDTO> estudiantesLista) {
       

        this.estudiantesLista = estudiantesLista;

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaEstudiantes.getModel();
        if(!estudiantesLista.isEmpty()){
        estudiantesLista.forEach(row -> {
            Object[] fila = new Object[3];
            fila[0] = row.getIdInstitucional();
            fila[1] = row.getNombreCompleto();
            fila[2] = row.getEstatusInscripcion();

            modeloTabla.addRow(fila);
        });}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPantalla = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstudiantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();

        setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Estudiantes");

        javax.swing.GroupLayout jPanelPantallaLayout = new javax.swing.GroupLayout(jPanelPantalla);
        jPanelPantalla.setLayout(jPanelPantallaLayout);
        jPanelPantallaLayout.setHorizontalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        jPanelPantallaLayout.setVerticalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPantallaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombré", "Estatus", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaEstudiantes);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista de Estudiantes");

        btnAgregar.setBackground(new java.awt.Color(27, 54, 143));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+ Agregar Estudiante");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1668, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregar)
                .addGap(0, 59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        panelEstudianteNuevo panelEstudiante = new panelEstudianteNuevo(estudianteNegocio, carreraNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelEstudiante, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEstudiantes;
    // End of variables declaration//GEN-END:variables
}
