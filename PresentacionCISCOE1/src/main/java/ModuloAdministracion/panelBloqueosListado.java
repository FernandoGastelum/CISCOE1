/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.BloqueoTablaDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IBloqueoNegocio;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import Utilidades.JButtonCellEditor;
import Utilidades.JButtonRenderer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Knocmare
 */
public class panelBloqueosListado extends javax.swing.JPanel {
    private final IBloqueoNegocio bloqueoNegocio;
    private final IEstudianteNegocio estudianteNegocio;

    /**
     * Creates new form panelListadoEstudiantes
     */
    public panelBloqueosListado(IBloqueoNegocio bloqueoNegocio, IEstudianteNegocio estudianteNegocio) {
        this.bloqueoNegocio = bloqueoNegocio;
        this.estudianteNegocio = estudianteNegocio;
        initComponents();
        this.metodosIniciales();
    }

    private void metodosIniciales() {
        this.configuracionInicialTabla();
        this.buscarTabla();
    }

    private void configuracionInicialTabla() {
        ActionListener onEditarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar
                editar();
            }
        };
        int indiceColumnaEditar = 5;
        TableColumnModel modeloColumnas = this.tablaBloqueos.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar", onEditarClickListener));

        ActionListener onLiberarEliminarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaBloqueos.getSelectedRow();
                String accion = (String) tablaBloqueos.getValueAt(fila, 6);

                if ("Liberar".equals(accion)) {
                    liberar();
                } else if ("Eliminar".equals(accion)) {
                    eliminar();
                }
            }
        };
        int indiceColumnaEliminar = 6;
        modeloColumnas.getColumn(indiceColumnaEliminar)
            .setCellRenderer(new JButtonRenderer("")); 
        modeloColumnas.getColumn(indiceColumnaEliminar)
            .setCellEditor(new JButtonCellEditor(onLiberarEliminarClickListener)); 
    }

    private void editar() {
        Long id = this.getIdSeleccionadoTabla();
        panelBloqueoEditar panel = new panelBloqueoEditar(bloqueoNegocio, id, estudianteNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        
    }
    private void liberar(){
        Long id = this.getIdSeleccionadoTabla();
        panelBloqueoLiberar panel = new panelBloqueoLiberar(bloqueoNegocio, estudianteNegocio, id);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    private void eliminar() {
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de que deseas eliminar este bloqueo?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                Long id = getIdSeleccionadoTabla(); 
                if (id != 0L) {
                    bloqueoNegocio.eliminar(id);
                    JOptionPane.showMessageDialog(this, "Bloqueo eliminado con éxito.");
                    this.metodosIniciales(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Selecciona un bloqueo válido.");
                }
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el bloqueo: " + e.getMessage());
            }
        }
    }

    private Long getIdSeleccionadoTabla() {
        int indiceFilaSeleccionada = this.tablaBloqueos.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaBloqueos.getModel();
            int indiceColumnaId = 0;
            Long idSeleccionado = (Long) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSeleccionado;
        } else {
            return 0L;
        }
    }

    private void buscarTabla() {
        try {
            List<BloqueoTablaDTO> bloqueosTablaLista = this.bloqueoNegocio.obtenerTabla();
            this.agregarRegistrosTabla(bloqueosTablaLista);
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void agregarRegistrosTabla(List<BloqueoTablaDTO> bloqueosLista) {
        if (bloqueosLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaBloqueos.getModel();
        modeloTabla.setRowCount(0); 

        for (BloqueoTablaDTO row : bloqueosLista) {
            Object[] fila = new Object[7];
            fila[0] = row.getIdBloqueo();
            Calendar calendarioBloqueo = row.getFechaBloqueo();
            Calendar calendarioLiberacion = row.getFechaLiberacion();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fechaBloqueo = formatoFecha.format(calendarioBloqueo.getTime());
            if(calendarioLiberacion==null){
                fila[4] = row.getFechaLiberacion();
            }else{
                String fechaLiberacion = formatoFecha.format(calendarioLiberacion.getTime());
                fila[4] = fechaLiberacion;
            }
            fila[1] = fechaBloqueo;
            fila[2] = row.getMotivo();
            fila[3] = row.getIdInstitucional();
            
            fila[5] = "Editar";
            fila[6] = (row.getFechaLiberacion() == null) ? "Liberar" : "Eliminar";

            modeloTabla.addRow(fila);
        }
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
        tablaBloqueos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();

        setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bloqueos");

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

        tablaBloqueos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha de Bloqueo", "Motivo", "ID Estudiante", "Fecha de Liberación", "Editar", "Liberar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaBloqueos);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista de Bloqueos");

        btnAgregar.setBackground(new java.awt.Color(27, 54, 143));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+ Agregar Bloqueo");
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
        panelBloqueoNuevo panelBloqueo = new panelBloqueoNuevo(bloqueoNegocio, estudianteNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelBloqueo, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaBloqueos;
    // End of variables declaration//GEN-END:variables
}
