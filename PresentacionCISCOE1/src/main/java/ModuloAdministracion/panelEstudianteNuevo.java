/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.CarreraDTO;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import Utilidades.ContraseniaUtil;
import java.awt.BorderLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Knocmare
 */
public class panelEstudianteNuevo extends javax.swing.JPanel {

    private IEstudianteNegocio estudianteNegocio;
    private ICarreraNegocio carreraNegocio;

    java.util.Random x = new java.util.Random();

    /**
     * Creates new form panelListadoEstudiantes
     *
     * @param estudianteNegocio
     * @param carreraNegocio
     */
    public panelEstudianteNuevo(IEstudianteNegocio estudianteNegocio, ICarreraNegocio carreraNegocio) {
        this.estudianteNegocio = estudianteNegocio;
        this.carreraNegocio = carreraNegocio;
        initComponents();
        this.cargarCarreras();
        this.cboCarrera.setSelectedIndex(-1);
    }

    private void guardarEstudiante() throws NegocioException {
        int randomNumber = 100000 + x.nextInt(900000);
        String idInstitucional = String.format("%011d", randomNumber);

        EstudianteDTOGuardar estudianteDTO = new EstudianteDTOGuardar();
        
        if ("".equals(txtNombre.getText())) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Nombre vacio", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            estudianteDTO.setNombre(txtNombre.getText());
        }
        
        if ("".equals(txtApellidoPaterno.getText())) {
            JOptionPane.showMessageDialog(null, "El apellido paterno no puede estar vacio", "Apellido paterno vacio", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            estudianteDTO.setApellidoPaterno(txtApellidoPaterno.getText());
        }
        
        if ("".equals(txtApellidoMaterno.getText())) {
            JOptionPane.showMessageDialog(null, "El apellido materno no puede estar vacio", "Apellido materno vacio", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            estudianteDTO.setApellidoMaterno(txtApellidoMaterno.getText());
        }
        
        if (cboCarrera.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debes elegir una carrera", "Sin carrera", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            estudianteDTO.setCarreraDTO((CarreraDTO) cboCarrera.getSelectedItem());
        }
        
        
        estudianteDTO.setIdInstitucional(idInstitucional);  // Establecer el idInstitucional formateado

        if (verificarContrasenias(this.contraseniaFIeld, this.confirmarContraseniaField)) {
            String contrasenia = new String(this.contraseniaFIeld.getPassword());
            String contraseniaEncriptada = ContraseniaUtil.encriptar(contrasenia);
            estudianteDTO.setContrasena(contraseniaEncriptada);
            estudianteDTO.setContrasena(contraseniaEncriptada);

            try {
                EstudianteDTO resultado = estudianteNegocio.guardar(estudianteDTO);
                JOptionPane.showMessageDialog(this, "Estudiante guardado con éxito con el id institucional: " + resultado.getIdInstitucional());
                this.regresar();
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el estudiante: " + e.getMessage());
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
    
    private void regresar() {
        panelEstudiantesListado panelEstudiante = new panelEstudiantesListado(estudianteNegocio, carreraNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelEstudiante, BorderLayout.CENTER);
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
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cboCarrera = new javax.swing.JComboBox<>();
        contraseniaFIeld = new javax.swing.JPasswordField();
        confirmarContraseniaField = new javax.swing.JPasswordField();
        btnRegresar = new javax.swing.JButton();

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
        jLabel2.setText("Ingrese los datos");

        btnAgregar.setBackground(new java.awt.Color(27, 143, 40));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("+ Agregar Estudiante");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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
        jLabel5.setText("Contraseña");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Confirmar Contraseña");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        txtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        txtApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        btnLimpiar.setBackground(new java.awt.Color(255, 0, 0));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Carrera");

        cboCarrera.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        contraseniaFIeld.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        confirmarContraseniaField.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        btnRegresar.setBackground(new java.awt.Color(246, 255, 0));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(309, 309, 309)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(68, 68, 68)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(contraseniaFIeld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(confirmarContraseniaField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 785, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(contraseniaFIeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarContraseniaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnLimpiar))
                .addGap(83, 83, 83))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            this.guardarEstudiante();
        } catch (NegocioException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        cboCarrera.setSelectedIndex(-1);
        contraseniaFIeld.setText("");
        confirmarContraseniaField.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<CarreraDTO> cboCarrera;
    private javax.swing.JPasswordField confirmarContraseniaField;
    private javax.swing.JPasswordField contraseniaFIeld;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
