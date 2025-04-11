/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloDesbloqueo;

import DTOs.BloqueoDTO;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.ReservaDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
import ModuloAdministracion.Interfaz.IBloqueoNegocio;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloAdministracion.Negocio.BloqueoNegocio;
import ModuloAdministracion.Negocio.ComputadoraNegocio;
import ModuloAdministracion.Negocio.EstudianteNegocio;
import ModuloAdministracion.Persistencia.BloqueoDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Interfaz.IReservaNegocio;
import ModuloReservas.Negocio.ReservaNegocio;
import ModuloReservas.Persistencia.ReservaDAO;
import Utilidades.ColorUtil;
import Utilidades.ContraseniaUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ilian Gastelum
 */
public class FrmDesbloqueo extends javax.swing.JFrame {
    private IEstudianteNegocio estudianteNegocio;
    private IReservaNegocio reservaNegocio;
    private IComputadoraNegocio computadoraNegocio;
    private IBloqueoNegocio bloqueoNegocio;
    private EstudianteDTO estudianteDTO;
    private ComputadoraDTO computadoraDTO;
    private ReservaDTO reservaDTO;
    /**
     * Creates new form FrmDesbloqueo
     */
    public FrmDesbloqueo(IEstudianteNegocio estudianteNegocio,IReservaNegocio reservaNegocio,IComputadoraNegocio computadoraNegocio,IBloqueoNegocio bloqueoNegocio) throws NegocioException {
        this.estudianteNegocio = estudianteNegocio;
        this.reservaNegocio = reservaNegocio;
        this.computadoraNegocio = computadoraNegocio;
        this.bloqueoNegocio = bloqueoNegocio;
        this.estudianteDTO = this.cargarAlumno();
        initComponents();
        this.cargarFondo();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        SwingUtilities.invokeLater(() -> cargarIconoComputadora());
    }
    private void cargarFondo(){
        Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/images/Background.png")).getImage().getScaledInstance(1920, 1080, 0));
        lblBackground.setIcon(icono);
    }
    private void cargarIconoComputadora() {
        try {
            List<ReservaDTO> listaReservasDTO = reservaNegocio.obtener();
            if (listaReservasDTO == null) return;

            for (ReservaDTO reservaDTO : listaReservasDTO) {
                if (reservaDTO == null || reservaDTO.getEstudiante() == null) continue;

                if (reservaDTO.getEstudiante().getIdInstitucional().equals(estudianteDTO.getIdInstitucional())) {
                    if (reservaDTO.getHoraFin() == null) {
                        computadoraDTO = computadoraNegocio.obtenerPorID(reservaDTO.getComputadora().getIdComputadora());
                        this.reservaDTO = reservaDTO;
                        this.cargarComputadora(computadoraDTO);
                        this.cargarDatosEstudiante(estudianteDTO);
                    }
                }
            }
        } catch (NegocioException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    private EstudianteDTO cargarAlumno() throws NegocioException {
        List<ReservaDTO> listaReservasDTO = reservaNegocio.obtener();
        if (listaReservasDTO == null) {
            listaReservasDTO = new ArrayList<>();
        }

        List<BloqueoDTO> listaBloqueosDTO = bloqueoNegocio.obtener(); 
        if (listaBloqueosDTO == null) {
            listaBloqueosDTO = new ArrayList<>();
        }

        while (true) {
            String alumno = JOptionPane.showInputDialog(this, "Ingrese el ID institucional del alumno:");

            if (alumno == null || alumno.trim().isEmpty()) {
                int opcion = JOptionPane.showConfirmDialog(this, "¿Desea cancelar el proceso de desbloqueo?", "Cancelar", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    dispose();
                    throw new NegocioException("Proceso cancelado por el usuario.");
                }
                continue;
            }

            try {
                EstudianteDTO estudiante = estudianteNegocio.obtenerPorIdInstitucional(alumno.trim());

                boolean tieneReservaActiva = false;
                for (ReservaDTO reservaDTO : listaReservasDTO) {
                    if (reservaDTO == null || reservaDTO.getEstudiante() == null) continue;

                    if (alumno.equals(reservaDTO.getEstudiante().getIdInstitucional()) &&
                        reservaDTO.getHoraFin() == null) {
                        tieneReservaActiva = true;
                        break;
                    }
                }

                if (!tieneReservaActiva) {
                    JOptionPane.showMessageDialog(this, "El estudiante no tiene una reserva activa.");
                    continue;
                }

                boolean tieneBloqueoActivo = false;
                for (BloqueoDTO bloqueoDTO : listaBloqueosDTO) {
                    if (bloqueoDTO == null || bloqueoDTO.getEstudiante() == null) continue;

                    if (alumno.equals(bloqueoDTO.getEstudiante().getIdInstitucional()) &&
                        bloqueoDTO.getFechaLiberacion() == null) {
                        tieneBloqueoActivo = true;
                        break;
                    }
                }

                if (tieneBloqueoActivo) {
                    JOptionPane.showMessageDialog(this, "El estudiante tiene un bloqueo activo.");
                    continue;
                }

                return estudiante;

            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, "No se encontró un estudiante con el ID: " + alumno.trim(), "ID inválido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private void cargarComputadora(ComputadoraDTO computadoraDTO){
        Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/images/pcGif.gif")).getImage().getScaledInstance(207, 220, 0));
        imagenLabel.setIcon(icono);
        this.numeroComputadoraLabel.setText("Equipo numero "+computadoraDTO.getNumeroMaquina().toString());
        
        Color color = ColorUtil.parseColor(computadoraDTO.getCarrera().getColor());
        this.colorPanel.setBackground(color);
        this.colorPanel.setPreferredSize(new Dimension(231, 290));
        this.colorPanel.setMaximumSize(new Dimension(231, 290));
        this.colorPanel.setMinimumSize(new Dimension(231, 290));
        
    }
    private void cargarDatosEstudiante(EstudianteDTO estudiante){
        String nombre = estudiante.getNombre()+
                " "+estudiante.getApellidoPaterno()+
                " "+estudiante.getApellidoMaterno();
        lblNombreEstudiante.setText(nombre);
        lblIdEstudiante.setText(estudiante.getIdInstitucional());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombreEstudiante = new javax.swing.JLabel();
        lblIdEstudiante = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        contraseniaPasswordField = new javax.swing.JPasswordField();
        desbloquearBTN = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        numeroComputadoraLabel = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        imagenLabel = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 161));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 80)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sistema de laboratorio CISCO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(418, 418, 418)
                .addComponent(jLabel1)
                .addContainerGap(457, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inicio de Sesion");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 232, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Equipo reservado por:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 337, -1, -1));

        lblNombreEstudiante.setBackground(new java.awt.Color(0, 0, 0));
        lblNombreEstudiante.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNombreEstudiante.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblNombreEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 410, -1, -1));

        lblIdEstudiante.setBackground(new java.awt.Color(0, 0, 0));
        lblIdEstudiante.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblIdEstudiante.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblIdEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 483, -1, -1));

        lblContrasena.setBackground(new java.awt.Color(0, 0, 0));
        lblContrasena.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasena.setText("Contraseña");
        getContentPane().add(lblContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 556, -1, -1));

        contraseniaPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        getContentPane().add(contraseniaPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 606, 331, -1));

        desbloquearBTN.setBackground(new java.awt.Color(51, 51, 51));
        desbloquearBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        desbloquearBTN.setForeground(new java.awt.Color(255, 255, 255));
        desbloquearBTN.setText("Desbloquear");
        desbloquearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desbloquearBTNActionPerformed(evt);
            }
        });
        getContentPane().add(desbloquearBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 715, 331, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setMaximumSize(new java.awt.Dimension(174, 222));
        jPanel2.setMinimumSize(new java.awt.Dimension(174, 222));

        numeroComputadoraLabel.setForeground(new java.awt.Color(255, 255, 255));
        numeroComputadoraLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        imagenLabel.setMaximumSize(new java.awt.Dimension(500, 500));
        imagenLabel.setMinimumSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numeroComputadoraLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(numeroComputadoraLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 260, 230, 290));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1920, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void desbloquearBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desbloquearBTNActionPerformed
        ContraseniaUtil util = new ContraseniaUtil();
    
        String contraseniaIngresada = new String(contraseniaPasswordField.getPassword());

        if (util.verificar(contraseniaIngresada, estudianteDTO.getContrasena())) {
            FrmDesbloqueoIniciado frmDesbloqueoIniciado = new FrmDesbloqueoIniciado(reservaNegocio, computadoraDTO,reservaDTO);
            this.dispose();
            frmDesbloqueoIniciado.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_desbloquearBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IEntityManager entityManager = new EntityManagerDAO();
        
        IEstudianteDAO estudianteDAO = new EstudianteDAO(entityManager);
        IEstudianteNegocio estudianteNegocio = new EstudianteNegocio(estudianteDAO);
        
        IReservaDAO reservaDAO = new ReservaDAO(entityManager);
        IReservaNegocio reservaNegocio = new ReservaNegocio(reservaDAO);
        
        IComputadoraDAO computadoraDAO = new ComputadoraDAO(entityManager);
        IComputadoraNegocio computadoraNegocio = new ComputadoraNegocio(computadoraDAO);
        
        IBloqueoDAO bloqueoDAO = new BloqueoDAO(entityManager);
        IBloqueoNegocio bloqueoNegocio = new BloqueoNegocio(bloqueoDAO);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmDesbloqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDesbloqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDesbloqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDesbloqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    new FrmDesbloqueo(estudianteNegocio, reservaNegocio, computadoraNegocio,bloqueoNegocio).setVisible(true);
                } catch (NegocioException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel colorPanel;
    private javax.swing.JPasswordField contraseniaPasswordField;
    private javax.swing.JButton desbloquearBTN;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblIdEstudiante;
    private javax.swing.JLabel lblNombreEstudiante;
    private javax.swing.JLabel numeroComputadoraLabel;
    // End of variables declaration//GEN-END:variables
}
