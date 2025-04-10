/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.CarreraDTO;
import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOEditar;
import DTOs.ComputadoraDTOGuardar;
import DTOs.LaboratorioDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import ModuloReservas.Interfaz.IReservaNegocio;
import java.awt.BorderLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Knocmare
 */
public class panelComputadoraEditar extends javax.swing.JPanel {

    private final IComputadoraNegocio computadoraNegocio;
    private final ILaboratorioNegocio laboratorioNegocio;
    private final ICarreraNegocio carreraNegocio;
    private final IReservaNegocio reservaNegocio;
    private final Long idComputadora;
    private ComputadoraDTO computadoraDTO;

    /**
     * Creates new form panelListadoEstudiantes
     */
    public panelComputadoraEditar(IComputadoraNegocio computadoraNegocio,ILaboratorioNegocio laboratorioNegocio,ICarreraNegocio carreraNegocio,Long idComputadora, IReservaNegocio reservaNegocio) {
        this.computadoraNegocio = computadoraNegocio;
        this.carreraNegocio = carreraNegocio;
        this.laboratorioNegocio = laboratorioNegocio;
        this.idComputadora=idComputadora;
        this.reservaNegocio = reservaNegocio;
        initComponents();
        this.cargarCarreras();
        this.cargarLaboratorios();
        this.cargarDatos();
        
    }
    private ComputadoraDTO obtenerComputadora(Long id) throws NegocioException{
        return computadoraNegocio.obtenerPorID(id);
    }
    private void cargarDatos(){
        try {
            computadoraDTO = this.obtenerComputadora(this.idComputadora);
        } catch (NegocioException ex) {
            System.out.println("Error: "+ ex.getMessage());
        }
        this.lblID.setText(this.idComputadora.toString());
        this.txtIP.setText(computadoraDTO.getDireccionIp());
        this.txtNumeroMaquina.setText(computadoraDTO.getNumeroMaquina().toString());
        this.carreraComboBox.setSelectedItem(computadoraDTO.getCarrera());
        this.laboratorioComboBox.setSelectedItem(computadoraDTO.getLaboratorio());
        this.tipoComboBox.setSelectedItem(computadoraDTO.getTipo());
    }
    private void cargarCarreras() {
        try {
            List<CarreraDTO> listaCarreras = carreraNegocio.obtener();
            if (listaCarreras != null) {
                for (CarreraDTO listaCarrera : listaCarreras) {
                    this.carreraComboBox.addItem(listaCarrera);
                }
            } else {
                throw new NegocioException("No hay carreras registradas");
            }
        } catch (NegocioException ex) {
            System.out.println("Error al cargar las combo boxes " + ex.getMessage());
        }
    }

    private void cargarLaboratorios() {
        try {
            List<LaboratorioDTO> listaLaboratorios = laboratorioNegocio.obtener();
            if (listaLaboratorios != null) {
                for (LaboratorioDTO listaLaboratorio : listaLaboratorios) {
                    this.laboratorioComboBox.addItem(listaLaboratorio);
                }
            } else {
                throw new NegocioException("No hay laboratorios registrados");
            }
        } catch (NegocioException ex) {
            System.out.println("Error al cargar las combo boxes " + ex.getMessage());
        }
    }
    private void actualizarComputadora() {
        ComputadoraDTOEditar computadoraDTOEditar = new ComputadoraDTOEditar();
        computadoraDTOEditar.setId(this.idComputadora);
        computadoraDTOEditar.setDireccionIp(txtIP.getText());
        computadoraDTOEditar.setNumeroMaquina(Integer.valueOf(txtNumeroMaquina.getText()));
        computadoraDTOEditar.setEstatus(true);
        computadoraDTOEditar.setTipo((String) tipoComboBox.getSelectedItem());
        computadoraDTOEditar.setCarreraDTO((CarreraDTO) carreraComboBox.getSelectedItem());
        computadoraDTOEditar.setLaboratorioDTO((LaboratorioDTO) laboratorioComboBox.getSelectedItem());
        try {
            ComputadoraDTO resultado = computadoraNegocio.actualizar(computadoraDTOEditar);
            JOptionPane.showMessageDialog(this, "Computadora actualizada con éxito con el numero: " + resultado.getNumeroMaquina());
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la computadora: " + e.getMessage());
        }
    }
    private void restaurarCampos(){
        this.cargarDatos();
    }
    private void regresar(){
        panelComputadorasListado panel = new panelComputadorasListado(computadoraNegocio, carreraNegocio, laboratorioNegocio,reservaNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panel, BorderLayout.CENTER);
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
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumeroMaquina = new javax.swing.JTextField();
        txtIP = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        laboratorioComboBox = new javax.swing.JComboBox<>();
        carreraComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tipoComboBox = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));
        jPanelPantalla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Computadoras");
        jPanelPantalla.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 710, -1));

        btnRegresar.setBackground(new java.awt.Color(246, 255, 0));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanelPantalla.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 40, 320, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Datos de La Computadora");

        btnGuardar.setBackground(new java.awt.Color(27, 143, 40));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dirección IP");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Número Máquina");

        txtNumeroMaquina.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        txtIP.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Restaurar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID: ");

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Carrera");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Laboratorio");

        laboratorioComboBox.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        carreraComboBox.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo");

        tipoComboBox.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        tipoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion", "Comun" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumeroMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)))
                        .addGap(158, 158, 158)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIP, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                            .addComponent(carreraComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(laboratorioComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumeroMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(laboratorioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(carreraComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(71, 71, 71))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.actualizarComputadora();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       this.restaurarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<CarreraDTO> carreraComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JComboBox<LaboratorioDTO> laboratorioComboBox;
    private javax.swing.JLabel lblID;
    private javax.swing.JComboBox<String> tipoComboBox;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtNumeroMaquina;
    // End of variables declaration//GEN-END:variables
}
