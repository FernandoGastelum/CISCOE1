/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.CarreraDTO;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOEditar;
import DTOs.EstudianteDTOGuardar;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import Utilidades.ContraseniaUtil;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Knocmare
 */
public class panelEstudianteEditar extends javax.swing.JPanel {

    private IEstudianteNegocio estudianteNegocio;
    private ICarreraNegocio carreraNegocio;
    private Long idEstudiante;
    private EstudianteDTO estudianteDTO;

    /**
     * Creates new form panelListadoEstudiantes
     */
    public panelEstudianteEditar(IEstudianteNegocio estudianteNegocio, ICarreraNegocio carreraNegocio, Long ID) throws NegocioException {
        this.estudianteNegocio = estudianteNegocio;
        this.carreraNegocio = carreraNegocio;
        idEstudiante = ID;
        initComponents();
        this.cargarCarreras();
        
        estudianteDTO = estudianteNegocio.obtenerPorID(ID);
        
        lblID.setText(Long.toString(ID));
        txtNombre.setText(estudianteDTO.getNombre());
        txtApellidoPaterno.setText(estudianteDTO.getApellidoPaterno());
        txtApellidoMaterno.setText(estudianteDTO.getApellidoMaterno());
        cboCarrera.setSelectedItem(estudianteDTO.getCarrera());
        
        if (estudianteDTO.getEstatusInscripcion()) {
            chkBoxInscrito.setSelected(true);
        } else {
            chkBoxInscrito.setSelected(false);
        }
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
    
    private void editarEstudiante() throws NegocioException {
        EstudianteDTOEditar estudianteEditado = new EstudianteDTOEditar();
        estudianteEditado.setNombre(txtNombre.getText());
        estudianteEditado.setApellidoPaterno(txtApellidoPaterno.getText());
        estudianteEditado.setApellidoMaterno(txtApellidoMaterno.getText());
        estudianteEditado.setCarreraDTO((CarreraDTO) cboCarrera.getSelectedItem());
        
        if (chkBoxInscrito.isSelected()) {
            estudianteEditado.setEstatusInscripcion(true);
        } else {
            estudianteEditado.setEstatusInscripcion(false);
        }
        if (verificarContrasenias(this.contraseniaFIeld, this.confirmarContraseniaField)) {
            String contrasenia = new String(this.contraseniaFIeld.getPassword());
            String contraseniaEncriptada = ContraseniaUtil.encriptar(contrasenia);
            estudianteEditado.setContrasena(contraseniaEncriptada);
            estudianteEditado.setContrasena(contraseniaEncriptada);
            try {
                EstudianteDTO resultado = estudianteNegocio.editar(idEstudiante, estudianteEditado);
                JOptionPane.showMessageDialog(this, "Estudiante editado con éxito con el id institucional: " + resultado.getIdInstitucional());
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al editar el estudiante: " + e.getMessage());
            }
        }

    }

    private boolean verificarContrasenias(JPasswordField contrasenia, JPasswordField confirmarContrasenia) {
        String pass1 = new String(contrasenia.getPassword());
        String pass2 = new String(confirmarContrasenia.getPassword());

        if (pass1.isEmpty() || pass2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llena ambos campos de contraseña.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error de coincidencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (pass1.length() < 8) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres.", "Contraseña muy corta", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!pass1.matches(".*[!@#$%^&*()_+\\-={}:;\"'?<>,.\\[\\]\\\\/].*")) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un símbolo especial.", "Contraseña insegura", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
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
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        btnRestaurar = new javax.swing.JButton();
        chkBoxInscrito = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        cboCarrera = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        contraseniaFIeld = new javax.swing.JPasswordField();
        confirmarContraseniaField = new javax.swing.JPasswordField();

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Datos del Estudiante");

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
        jLabel3.setText("Apellido Paterno");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido Materno");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nueva Contraseña");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Confirmar Contraseña");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombré");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        txtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        txtApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        btnRestaurar.setBackground(new java.awt.Color(255, 0, 0));
        btnRestaurar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRestaurar.setForeground(new java.awt.Color(255, 255, 255));
        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        chkBoxInscrito.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        chkBoxInscrito.setForeground(new java.awt.Color(255, 255, 255));
        chkBoxInscrito.setText("Inscrito");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID: ");

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));

        cboCarrera.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Carrera");

        btnRegresar.setBackground(new java.awt.Color(246, 255, 0));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        contraseniaFIeld.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        confirmarContraseniaField.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6))
                                        .addGap(68, 68, 68)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(lblID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                                                .addGap(69, 69, 69)
                                                .addComponent(chkBoxInscrito))
                                            .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cboCarrera, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(confirmarContraseniaField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                                .addComponent(contraseniaFIeld, javax.swing.GroupLayout.Alignment.LEADING)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 563, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(415, 415, 415)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnRegresar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(chkBoxInscrito)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(contraseniaFIeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(confirmarContraseniaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnRestaurar))
                .addGap(83, 83, 83))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            this.editarEstudiante();
        } catch (NegocioException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        lblID.setText(Long.toString(idEstudiante));
        txtNombre.setText(estudianteDTO.getNombre());
        txtApellidoPaterno.setText(estudianteDTO.getApellidoPaterno());
        txtApellidoMaterno.setText(estudianteDTO.getApellidoMaterno());
        cboCarrera.setSelectedItem(estudianteDTO.getCarrera());
        
        if (estudianteDTO.getEstatusInscripcion()) {
            chkBoxInscrito.setSelected(true);
        } else {
            chkBoxInscrito.setSelected(false);
        }
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        panelEstudiantesListado panelEstudiante = new panelEstudiantesListado(estudianteNegocio, carreraNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelEstudiante, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JComboBox<CarreraDTO> cboCarrera;
    private javax.swing.JCheckBox chkBoxInscrito;
    private javax.swing.JPasswordField confirmarContraseniaField;
    private javax.swing.JPasswordField contraseniaFIeld;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
