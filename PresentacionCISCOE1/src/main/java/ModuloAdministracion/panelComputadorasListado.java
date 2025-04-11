/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraTablaDTO;
import DTOs.ReservaDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import ModuloReservas.Interfaz.IReservaNegocio;
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
public class panelComputadorasListado extends javax.swing.JPanel {

    private final IComputadoraNegocio computadoraNegocio;
    private final ILaboratorioNegocio laboratorioNegocio;
    private final ICarreraNegocio carreraNegocio;
    private final IReservaNegocio reservaNegocio;

    /**
     * Creates new form panelListadoEstudiantes
     */
    public panelComputadorasListado(IComputadoraNegocio computadoraNegocio, ICarreraNegocio carreraNegocio, ILaboratorioNegocio laboratorioNegocio,IReservaNegocio reservaNegocio) {
        this.computadoraNegocio = computadoraNegocio;
        this.laboratorioNegocio = laboratorioNegocio;
        this.carreraNegocio = carreraNegocio;
        this.reservaNegocio = reservaNegocio;
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
        TableColumnModel modeloColumnas = this.tablaComputadoras.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar", onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar
                eliminar();
            }
        };
        int indiceColumnaEliminar = 6;
        modeloColumnas = this.tablaComputadoras.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private void editar() {
        Long id = this.getIdSeleccionadoTabla();
        panelComputadoraEditar panel = new panelComputadoraEditar(computadoraNegocio, laboratorioNegocio, carreraNegocio, id,reservaNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        
    }

    private void eliminar() {
        Boolean computadoraLigada = false;
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de que deseas eliminar esta computadora?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                Long id = this.getIdSeleccionadoTabla();
                if (id != 0L) {
                    List<ReservaDTO> listaReserva = reservaNegocio.obtener();
                    ComputadoraDTO computadoraDTO = computadoraNegocio.obtenerPorID(id);

                    if (listaReserva != null && !listaReserva.isEmpty()) {
                        for (ReservaDTO reservaDTO : listaReserva) {
                            if (reservaDTO.getComputadora().getIdComputadora()
                                    .equals(computadoraDTO.getIdComputadora())) {
                                computadoraLigada = true;
                                break; 
                            }
                        }
                    }

                    if (!computadoraLigada) {
                        computadoraNegocio.eliminar(id);
                        JOptionPane.showMessageDialog(this, "Computadora eliminada con éxito.");
                        this.metodosIniciales(); 
                    } else {
                        JOptionPane.showMessageDialog(this, "La computadora está ligada a una reserva.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Selecciona una computadora válida.");
                }
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo borrar la computadora: " + ex.getMessage());
            }
        }
    }

    private Long getIdSeleccionadoTabla() {
        int indiceFilaSeleccionada = this.tablaComputadoras.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tablaComputadoras.getModel();
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
            List<ComputadoraTablaDTO> computadorasTablaLista = this.computadoraNegocio.obtenerTabla();
            DefaultTableModel modelo = (DefaultTableModel) this.tablaComputadoras.getModel();
            modelo.setRowCount(0);
            this.agregarRegistrosTabla(computadorasTablaLista);
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void agregarRegistrosTabla(List<ComputadoraTablaDTO> computadorasLista) {
        if (computadorasLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaComputadoras.getModel();
        computadorasLista.forEach(row -> {
            Object[] fila = new Object[5];
            fila[0] = row.getIdComputadora();
            fila[1] = row.getNumeroMaquina();
            fila[2] = row.getDireccionIp();
            fila[3] = row.getEstatus();
            fila[4] = row.getTipo();

            modeloTabla.addRow(fila);
        });
    }
    private void agregar(){
        panelComputadoraNuevo panelComputadora = new panelComputadoraNuevo(computadoraNegocio,carreraNegocio, laboratorioNegocio,reservaNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelComputadora, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
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
        tablaComputadoras = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();

        setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Computadoras");

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

        tablaComputadoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Número Máquina", "IP", "Estatus", "Tipo", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaComputadoras);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista de Computadoras");

        btnAgregar.setBackground(new java.awt.Color(27, 54, 143));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+ Agregar Computadora");
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
        this.agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaComputadoras;
    // End of variables declaration//GEN-END:variables
}
