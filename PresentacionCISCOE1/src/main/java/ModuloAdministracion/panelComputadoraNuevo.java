/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.CarreraDTO;
import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import DTOs.LaboratorioDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import ModuloReservas.Interfaz.IReservaNegocio;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Knocmare
 */
public class panelComputadoraNuevo extends javax.swing.JPanel {

    private final IComputadoraNegocio computadoraNegocio;
    private final ICarreraNegocio carreraNegocio;
    private final ILaboratorioNegocio laboratorioNegocio;
    private final IReservaNegocio reservaNegocio;

    /**
     * Creates new form panelListadoEstudiantes
     * @param computadoraNegocio
     * @param carreraNegocio
     * @param laboratorioNegocio
     * @param reservaNegocio
     */
    public panelComputadoraNuevo(IComputadoraNegocio computadoraNegocio, ICarreraNegocio carreraNegocio, ILaboratorioNegocio laboratorioNegocio, IReservaNegocio reservaNegocio) {
        this.computadoraNegocio = computadoraNegocio;
        this.carreraNegocio = carreraNegocio;
        this.laboratorioNegocio = laboratorioNegocio;
        this.reservaNegocio = reservaNegocio;
        initComponents();
        this.cargarCarreras();
        this.cargarLaboratorios();
        this.restaurarCampos();
    }

    private void cargarCarreras() {
        try {
            List<CarreraDTO> listaCarreras = carreraNegocio.obtener();
            if (listaCarreras != null) {
                for (CarreraDTO listaCarrera : listaCarreras) {
                    this.cboCarrera.addItem(listaCarrera);
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
                    this.cboLaboratorio.addItem(listaLaboratorio);
                }
            } else {
                throw new NegocioException("No hay laboratorios registrados");
            }
        } catch (NegocioException ex) {
            System.out.println("Error al cargar las combo boxes " + ex.getMessage());
        }
    }

    private void guardarComputadora() {
        ComputadoraDTOGuardar computadoraDTO = new ComputadoraDTOGuardar();
        
        if ("".equals(txtIP.getText())) {
            JOptionPane.showMessageDialog(null, "La direccion IP no puede estar vacio", "Direccion IP vacia", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            computadoraDTO.setDireccionIp(txtIP.getText());
        }
        
        if ("".equals(txtNumeroMaquina.getText())) {
            JOptionPane.showMessageDialog(null, "El numero de maquina no puede estar vacio", "Numero de maquina vacia", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            computadoraDTO.setNumeroMaquina(Integer.valueOf(txtNumeroMaquina.getText()));
        }
        
        computadoraDTO.setEstatus(true);
        
        if (tipoComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debes elegir un tipo", "Sin tipo", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
          computadoraDTO.setTipo((String) tipoComboBox.getSelectedItem());
        }
        
        if (cboCarrera.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debes elegir una carrera", "Sin carrera", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
          computadoraDTO.setCarreraDTO((CarreraDTO) cboCarrera.getSelectedItem());
        }
        
        if (cboLaboratorio.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debes elegir un laboratorio", "Sin laboratorio", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
          computadoraDTO.setLaboratorioDTO((LaboratorioDTO) cboLaboratorio.getSelectedItem());
        }
        
        try {
            ComputadoraDTO resultado = computadoraNegocio.guardar(computadoraDTO);
            JOptionPane.showMessageDialog(this, "Computadora guardada con éxito con el numero: " + resultado.getNumeroMaquina());
            this.regresar();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la computadora: " + e.getMessage());
        }
    }
    private void restaurarCampos(){
        this.txtIP.setText("");
        this.txtNumeroMaquina.setText("");
        this.cboCarrera.setSelectedItem(-1);
        this.cboLaboratorio.setSelectedItem(-1);
        this.tipoComboBox.setSelectedItem(-1);
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
        btnAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumeroMaquina = new javax.swing.JTextField();
        txtIP = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboLaboratorio = new javax.swing.JComboBox<>();
        cboCarrera = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tipoComboBox = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Computadoras");

        btnRegresar.setBackground(new java.awt.Color(246, 255, 0));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPantallaLayout = new javax.swing.GroupLayout(jPanelPantalla);
        jPanelPantalla.setLayout(jPanelPantallaLayout);
        jPanelPantallaLayout.setHorizontalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPantallaLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanelPantallaLayout.setVerticalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPantallaLayout.createSequentialGroup()
                .addGroup(jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPantallaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(jPanelPantallaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnRegresar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese los datos");

        btnAgregar.setBackground(new java.awt.Color(27, 143, 40));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+ Agregar Computadora");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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

        btnLimpiar.setBackground(new java.awt.Color(255, 0, 0));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Carrera");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Laboratorio");

        cboLaboratorio.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        cboCarrera.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo");

        tipoComboBox.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        tipoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion", "Comun" }));
        tipoComboBox.setSelectedIndex(-1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(55, 55, 55)
                                .addComponent(txtNumeroMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)))
                                .addGap(158, 158, 158)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboLaboratorio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIP, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                    .addComponent(cboCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tipoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumeroMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cboLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnLimpiar)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        this.guardarComputadora();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.restaurarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<CarreraDTO> cboCarrera;
    private javax.swing.JComboBox<LaboratorioDTO> cboLaboratorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JComboBox<String> tipoComboBox;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtNumeroMaquina;
    // End of variables declaration//GEN-END:variables
}
