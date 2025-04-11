/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloReservas;

import DTOs.BloqueoDTO;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.LaboratorioDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
import ModuloAdministracion.Interfaz.IBloqueoNegocio;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.IHorarioNegocio;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import ModuloAdministracion.Negocio.BloqueoNegocio;
import ModuloAdministracion.Negocio.ComputadoraNegocio;
import ModuloAdministracion.Negocio.EstudianteNegocio;
import ModuloAdministracion.Negocio.HorarioNegocio;
import ModuloAdministracion.Negocio.LaboratorioNegocio;
import ModuloAdministracion.Persistencia.BloqueoDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Interfaz.IReservaNegocio;
import ModuloReservas.Negocio.ReservaNegocio;
import ModuloReservas.Persistencia.ReservaDAO;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ilian Gastelum,
 */
public class FrmLoginReservas extends javax.swing.JFrame {
    private final IEstudianteNegocio estudianteNegocio;
    private final IComputadoraNegocio computadoraNegocio;
    private final IReservaNegocio reservaNegocio;
    private final IHorarioNegocio horarioNegocio;
    private final IBloqueoNegocio bloqueoNegocio;
    private LaboratorioDTO laboratorioDTO;
    private final ILaboratorioNegocio laboratorioNegocio;
    
    /**
     * 
     * @param estudianteNegocio
     * @param computadoraNegocio
     * @param reservaNegocio
     * @param horarioNegocio 
     */
    public FrmLoginReservas(IEstudianteNegocio estudianteNegocio, IComputadoraNegocio computadoraNegocio,IReservaNegocio reservaNegocio,IHorarioNegocio horarioNegocio,ILaboratorioNegocio laboratorioNegocio,IBloqueoNegocio bloqueoNegocio) {
        this.estudianteNegocio = estudianteNegocio;
        this.computadoraNegocio = computadoraNegocio;
        this.reservaNegocio = reservaNegocio;
        this.horarioNegocio = horarioNegocio;
        this.bloqueoNegocio = bloqueoNegocio;
        this.laboratorioNegocio=laboratorioNegocio;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.cargarLaboratorio();
    }
//    private boolean validarUsuario(String id){
//        try {
//            List<EstudianteDTO> listaEstudiantes = estudianteNegocio.obtener();
//            for (EstudianteDTO listaEstudiante : listaEstudiantes) {
//                if(listaEstudiante.getIdInstitucional().equals(id)){
//                    return listaEstudiante.getEstatusInscripcion()==true;
//                }
//            }
//        } catch (NegocioException ex) {
//            System.out.println("Error "+ex.getMessage());
//        }
//        
//        return false;
//    }
    private boolean tieneBloqueo(Long idEstudiante){
        Boolean bloqueo = false;
        try {
            List<BloqueoDTO> listaBloqueos = bloqueoNegocio.obtener();
            if(listaBloqueos==null){
                return bloqueo;
            }
            for (BloqueoDTO listaBloqueo : listaBloqueos) {
                if(listaBloqueo.getEstudiante().getIdEstudiante().equals(idEstudiante)){
                    if(listaBloqueo.getFechaLiberacion()==null){
                        bloqueo = true;
                    }
                }
            }
        } catch (NegocioException ex) {
            System.out.println("Error: "+ ex.getMessage());
        }
        return bloqueo;
    }
    private void cargarLaboratorio(){
        try {
            String ipLocal = this.obtenerIpLocal();
            List<ComputadoraDTO> listaComputadoras = computadoraNegocio.obtener();
            for (ComputadoraDTO listaComputadora : listaComputadoras) {
                if(listaComputadora.getDireccionIp().equals(ipLocal)&&listaComputadora.getTipo().equals("Seleccion")){
                    this.laboratorioDTO = this.obtenerLab(listaComputadora.getLaboratorio().getIdLaboratorio());
                }
            }
            if(this.laboratorioDTO==null){
                JOptionPane.showMessageDialog(this, "No se encontro una computadora de seleccion con la ipLocal");
                throw new NegocioException("No se encontro una computadora Apropiada");
            }
        } catch (NegocioException | UnknownHostException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
    private LaboratorioDTO obtenerLab(Long id) throws NegocioException{
        return laboratorioNegocio.obtenerPorID(id);
    }
    private String obtenerIpLocal() throws UnknownHostException{
        try {
            InetAddress direccion = InetAddress.getLocalHost();
            System.out.println("Dirección IP: " + direccion.getHostAddress());
            return direccion.getHostAddress();
        } catch (UnknownHostException ex) {
            throw new UnknownHostException("No se pudo obtener la IP");
        }
    }
    private EstudianteDTO obtenerEstudiantePorIdInstitucional(String id) {
        try {
            List<EstudianteDTO> listaEstudiantes = estudianteNegocio.obtener();
            for (EstudianteDTO estudiante : listaEstudiantes) {
                if (estudiante.getIdInstitucional().equals(id)) {
                    return estudiante;
                }
            }
        } catch (NegocioException ex) {
            System.out.println("Error al obtener estudiante: " + ex.getMessage());
        }
        return null;
    }
    private void Login(){
        String idInstitucional = usuarioTextField.getText();
        EstudianteDTO estudiante = obtenerEstudiantePorIdInstitucional(idInstitucional);

        if (estudiante != null && estudiante.getEstatusInscripcion()) {
            if (tieneBloqueo(estudiante.getIdEstudiante())) {
                JOptionPane.showMessageDialog(rootPane, "El estudiante con ID " + idInstitucional + " tiene un bloqueo activo y no puede realizar reservas.");
                return;
            }

            this.dispose();
            FrmReservas frmReserva = new FrmReservas(
                computadoraNegocio,
                estudianteNegocio,
                horarioNegocio,
                idInstitucional,
                reservaNegocio,
                laboratorioDTO
            );
            frmReserva.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario con el ID: " + idInstitucional + " no existe o no está inscrito.");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usuarioTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        LoginBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(72, 72, 72));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1980, 1080));
        setPreferredSize(new java.awt.Dimension(1980, 1080));

        jPanel1.setBackground(new java.awt.Color(86, 86, 86));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccion de equipo CISCO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(685, 685, 685)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(625, 625, 625))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(47, 47, 47));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario");

        usuarioTextField.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        usuarioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Laboratorio");

        LoginBTN.setBackground(new java.awt.Color(102, 102, 102));
        LoginBTN.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        LoginBTN.setForeground(new java.awt.Color(255, 255, 255));
        LoginBTN.setText("Login");
        LoginBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(828, 828, 828))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioTextField)
                    .addComponent(LoginBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
                .addGap(716, 716, 716))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel3)
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(LoginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioTextFieldActionPerformed

    private void LoginBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBTNActionPerformed
        this.Login();
    }//GEN-LAST:event_LoginBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IEntityManager entityManager = new EntityManagerDAO();
        IEstudianteDAO estudianteDAO = new EstudianteDAO(entityManager);
                IComputadoraDAO computadoraDAO = new ComputadoraDAO(entityManager);
                IReservaDAO reservaDAO = new ReservaDAO(entityManager);
                IEstudianteNegocio estudianteNegocio = new EstudianteNegocio(estudianteDAO);
                IComputadoraNegocio computadoraNegocio = new ComputadoraNegocio(computadoraDAO);
                IHorarioDAO horarioDAO = new HorarioDAO(entityManager);
                IHorarioNegocio horarioNegocio = new HorarioNegocio(horarioDAO);
                IReservaNegocio reservaNegocio = new ReservaNegocio(reservaDAO);
                ILaboratorioDAO laboratorioDAO = new LaboratorioDAO(entityManager);
                ILaboratorioNegocio laboratorioNegocio = new LaboratorioNegocio(laboratorioDAO);
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
            java.util.logging.Logger.getLogger(FrmLoginReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLoginReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLoginReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLoginReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new FrmLoginReservas(estudianteNegocio, computadoraNegocio,reservaNegocio,horarioNegocio,laboratorioNegocio,bloqueoNegocio).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField usuarioTextField;
    // End of variables declaration//GEN-END:variables
}
