/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloAdministracion;

import DTOs.InstitutoDTO;
import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOEditar;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IInstitutoNegocio;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import Utilidades.ContraseniaUtil;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Knocmare
 */
public class panelLaboratorioEditar extends javax.swing.JPanel {

    private final ILaboratorioNegocio laboratorioNegocio;
    private final IInstitutoNegocio institutoNegocio;
    private Long idLaboratorio;
    private LaboratorioDTO laboratorioDTO;

    /**
     * Creates new form panelListadoEstudiantes
     */
    public panelLaboratorioEditar(ILaboratorioNegocio laboratorioNegocio, IInstitutoNegocio institutoNegocio, Long ID) throws NegocioException {
        this.laboratorioNegocio = laboratorioNegocio;
        this.institutoNegocio = institutoNegocio;
        idLaboratorio = ID;
        initComponents();
        this.cargarInstituto();
        this.cargarHoras();

        laboratorioDTO = laboratorioNegocio.obtenerPorID(ID);

        this.restaurarDatos();
    }

    private void cargarInstituto() {
        try {
            List<InstitutoDTO> listaInstitutos = institutoNegocio.obtener();
            if (listaInstitutos != null) {
                for (InstitutoDTO listaInstituto : listaInstitutos) {
                    this.cboInstituto.addItem(listaInstituto);
                }
            } else {
                throw new NegocioException("No hay institutos registrados");
            }
        } catch (NegocioException ex) {
            System.out.println("Error al cargar las combo boxes " + ex.getMessage());
        }
    }

    private void editarLaboratorio() throws NegocioException, ParseException {
        LaboratorioDTOEditar laboratorioEditado = new LaboratorioDTOEditar();

        laboratorioEditado.setNombre(txtNombre.getText());
        laboratorioEditado.setInstitutoDTO((InstitutoDTO) cboInstituto.getSelectedItem());

        if (!((String) cboApertura.getSelectedItem()).matches("^\\d{2}:\\d{2}$")
                || !((String) cboCierre.getSelectedItem()).matches("^\\d{2}:\\d{2}$")) {
            JOptionPane.showMessageDialog(this, "Las horas deben tener el formato HH:mm", "Formato incorrecto", JOptionPane.WARNING_MESSAGE);
        } else {
            Calendar horaApertura = parseHora((String) cboApertura.getSelectedItem());
            Calendar horaCierre = parseHora((String) cboCierre.getSelectedItem());

            laboratorioEditado.setHoraApertura(horaApertura);
            laboratorioEditado.setHoraCierre(horaCierre);
        }

        if (verificarContrasenias(this.contraseniaFIeld, this.confirmarContraseniaField)) {
            String contrasenia = new String(this.contraseniaFIeld.getPassword());
            String contraseniaEncriptada = ContraseniaUtil.encriptar(contrasenia);
            laboratorioEditado.setContrasenaMaestra(contraseniaEncriptada);
            laboratorioEditado.setContrasenaMaestra(contraseniaEncriptada);
        }

        try {
            LaboratorioDTO resultado = laboratorioNegocio.editar(idLaboratorio, laboratorioEditado);
            JOptionPane.showMessageDialog(this, "Laboratorio editado con éxito con el nombre: " + resultado.getNombre());
            this.regresar();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el laboratorio: " + e.getMessage());
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

    private Calendar parseHora(String horaTexto) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = sdf.parse(horaTexto);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private void cargarHoras() {
        String[] horas = {
            "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
            "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
            "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
            "20:30", "21:00", "21:30"
        };

        for (String hora : horas) {
            cboApertura.addItem(hora);
            cboCierre.addItem(hora);
        }
    }
    
    private void regresar() {
        panelLaboratoriosListado panelLaboratorio = new panelLaboratoriosListado(laboratorioNegocio, institutoNegocio);
        this.setLayout(new BorderLayout());
        this.removeAll();
        this.add(panelLaboratorio, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void restaurarDatos() {
        lblID.setText(Long.toString(idLaboratorio));
        txtNombre.setText(laboratorioDTO.getNombre());
        cboApertura.setSelectedItem(laboratorioDTO.getHoraApertura());
        cboCierre.setSelectedItem(laboratorioDTO.getHoraCierre());
        cboInstituto.setSelectedItem(laboratorioDTO.getInstituto());
        contraseniaFIeld.setText("");
        confirmarContraseniaField.setText("");
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
        btnRestaurar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cboInstituto = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        contraseniaFIeld = new javax.swing.JPasswordField();
        confirmarContraseniaField = new javax.swing.JPasswordField();
        cboApertura = new javax.swing.JComboBox<>();
        cboCierre = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Laboratorios");

        javax.swing.GroupLayout jPanelPantallaLayout = new javax.swing.GroupLayout(jPanelPantalla);
        jPanelPantalla.setLayout(jPanelPantallaLayout);
        jPanelPantallaLayout.setHorizontalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel2.setText("Datos del Laboratorio");

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
        jLabel3.setText("Hora Apertura");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña Maestra");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirmar Contraseña");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Instituto");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        btnRestaurar.setBackground(new java.awt.Color(255, 0, 0));
        btnRestaurar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRestaurar.setForeground(new java.awt.Color(255, 255, 255));
        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Hora Cierre");

        cboInstituto.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID: ");

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));

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

        cboApertura.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        cboCierre.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(396, 396, 396)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboInstituto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(confirmarContraseniaField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                .addComponent(contraseniaFIeld, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboApertura, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107)
                                .addComponent(jLabel8)
                                .addGap(69, 69, 69)
                                .addComponent(cboCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(336, 336, 336))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
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
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(cboApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(contraseniaFIeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(confirmarContraseniaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboInstituto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnRestaurar))
                .addGap(83, 83, 83))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        this.restaurarDatos();
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            this.editarLaboratorio();
            this.regresar();
        } catch (NegocioException | ParseException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JComboBox<String> cboApertura;
    private javax.swing.JComboBox<String> cboCierre;
    private javax.swing.JComboBox<InstitutoDTO> cboInstituto;
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
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
