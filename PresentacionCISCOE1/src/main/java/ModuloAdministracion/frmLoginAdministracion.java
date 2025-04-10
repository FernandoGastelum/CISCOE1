/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloAdministracion;

import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
import ModuloAdministracion.Interfaz.IBloqueoNegocio;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Interfaz.IInstitutoNegocio;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import ModuloAdministracion.Negocio.BloqueoNegocio;
import ModuloAdministracion.Negocio.CarreraNegocio;
import ModuloAdministracion.Negocio.ComputadoraNegocio;
import ModuloAdministracion.Negocio.EstudianteNegocio;
import ModuloAdministracion.Negocio.InstitutoNegocio;
import ModuloAdministracion.Negocio.LaboratorioNegocio;
import ModuloAdministracion.Persistencia.BloqueoDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Interfaz.IReservaNegocio;
import ModuloReservas.Negocio.ReservaNegocio;
import ModuloReservas.Persistencia.ReservaDAO;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Knocmare
 */
public class frmLoginAdministracion extends javax.swing.JFrame {
    private final ILaboratorioNegocio laboratorioNegocio;
    private final IInstitutoNegocio institutoNegocio;
    private final IEstudianteNegocio estudianteNegocio;
    private final IComputadoraNegocio computadoraNegocio;
    private final ICarreraNegocio carreraNegocio;
    private final IBloqueoNegocio bloqueoNegocio;
    private final IReservaNegocio reservaNegocio;
    
    /**
     * Creates new form frmLoginAdministracion
     * @param laboratorioDAO
     * @param institutoDAO
     * @param estudianteDAO
     * @param computadoraDAO
     * @param carreraDAO
     * @param bloqueoDAO
     */
    public frmLoginAdministracion(ILaboratorioDAO laboratorioDAO, IInstitutoDAO institutoDAO, 
            IEstudianteDAO estudianteDAO, IComputadoraDAO computadoraDAO, 
            ICarreraDAO carreraDAO, IBloqueoDAO bloqueoDAO,IReservaNegocio reservaNegocio) {
        this.laboratorioNegocio = new LaboratorioNegocio(laboratorioDAO);
        this.institutoNegocio = new InstitutoNegocio(institutoDAO);
        this.estudianteNegocio = new EstudianteNegocio(estudianteDAO);
        this.computadoraNegocio = new ComputadoraNegocio(computadoraDAO);
        this.carreraNegocio = new CarreraNegocio(carreraDAO);
        this.bloqueoNegocio = new BloqueoNegocio(bloqueoDAO);
        this.reservaNegocio = reservaNegocio;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private boolean validarUsuario(Long id){
        try {
            List<LaboratorioDTO> listaLaboratorios = laboratorioNegocio.obtener();
            for (LaboratorioDTO laboratorio : listaLaboratorios) {
                if(laboratorio.getIdLaboratorio().equals(id)){
                    return true;
                }
            }
        } catch (NegocioException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return false;
    }
    
    private boolean validarContrasena(String contrasena){
        try {
            List<LaboratorioDTO> listaLaboratorios = laboratorioNegocio.obtener();
            for (LaboratorioDTO laboratorio : listaLaboratorios) {
                if(laboratorio.getContrasenaMaestra().equals(contrasena)){
                    return true;
                }
            }
        } catch (NegocioException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        return false;
    }
    private void nuevoRegistroLabInst(){
        try {
            List<LaboratorioDTO> laboratorios = laboratorioNegocio.obtener();
            List<InstitutoDTO> institutos = institutoNegocio.obtener();

            if (laboratorios.isEmpty() && institutos.isEmpty()) {
                this.registrarLab();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existen registros de laboratorios e institutos");
            }
        } catch (NegocioException ex) {
            Logger.getLogger(frmLoginAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private InstitutoDTO registrarInst() throws NegocioException{
        InstitutoDTOGuardar instDTO = new InstitutoDTOGuardar("Instituto Tecnologico de Sonora", "ITSON");
        try {
            return institutoNegocio.guardar(instDTO);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    private void registrarLab() throws NegocioException{
        LaboratorioDTOGuardar labDTO = new LaboratorioDTOGuardar("CISCO", this.gethoraInicio(), this.gethoraCierre(), "Maestra12345@", this.registrarInst());
        laboratorioNegocio.guardar(labDTO);
        JOptionPane.showMessageDialog(this, "Registros completados con exito");
    }
    public Calendar gethoraInicio() {
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 10);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }

    public Calendar gethoraCierre() {
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 22);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogin = new javax.swing.JPanel();
        jPanelPantalla = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        nuevoLabBTN = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelLogin.setBackground(new java.awt.Color(35, 35, 35));

        jPanelPantalla.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administración CISCO");

        javax.swing.GroupLayout jPanelPantallaLayout = new javax.swing.GroupLayout(jPanelPantalla);
        jPanelPantalla.setLayout(jPanelPantallaLayout);
        jPanelPantallaLayout.setHorizontalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        jPanelPantallaLayout.setVerticalGroup(
            jPanelPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPantallaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Inicio de Sesión");

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

        btnLogin.setBackground(new java.awt.Color(44, 44, 44));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        nuevoLabBTN.setBackground(new java.awt.Color(44, 44, 44));
        nuevoLabBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nuevoLabBTN.setForeground(new java.awt.Color(255, 255, 255));
        nuevoLabBTN.setText("Nuevo Laboratorio (Primer ingreso)");
        nuevoLabBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoLabBTNActionPerformed(evt);
            }
        });

        txtContrasena.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPantalla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nuevoLabBTN))
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(715, 715, 715)
                        .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuario)
                            .addComponent(jLabel3)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(txtContrasena))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addComponent(jPanelPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jLabel4)
                .addGap(146, 146, 146)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(nuevoLabBTN)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        long valorLong = Long.parseLong(txtUsuario.getText());
        if(validarUsuario(valorLong) && validarContrasena(txtContrasena.getText())){
            this.dispose();
            frmMenuAdministrativo menuAdministrativo = new frmMenuAdministrativo(
                    laboratorioNegocio, institutoNegocio, estudianteNegocio, 
                    computadoraNegocio, carreraNegocio, bloqueoNegocio,reservaNegocio);
            menuAdministrativo.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "El usuario con el id: "+txtUsuario.getText()+" no existe");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void nuevoLabBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoLabBTNActionPerformed
        this.nuevoRegistroLabInst();
    }//GEN-LAST:event_nuevoLabBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IEntityManager entityManager = new EntityManagerDAO();
        ILaboratorioDAO laboratorioDAO = new LaboratorioDAO(entityManager);
                IInstitutoDAO institutoDAO = new InstitutoDAO(entityManager);
                IEstudianteDAO estudianteDAO = new EstudianteDAO(entityManager);
                IComputadoraDAO computadoraNegocio = new ComputadoraDAO(entityManager);
                ICarreraDAO carreraNegocio = new CarreraDAO(entityManager);
                IBloqueoDAO bloqueoNegocio = new BloqueoDAO(entityManager);
                IReservaDAO reservaDAO = new ReservaDAO(entityManager);
                IReservaNegocio reservaNegocio = new ReservaNegocio(reservaDAO);
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
            java.util.logging.Logger.getLogger(frmLoginAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLoginAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLoginAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLoginAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLoginAdministracion(laboratorioDAO, institutoDAO, estudianteDAO, computadoraNegocio, carreraNegocio, bloqueoNegocio,reservaNegocio).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelPantalla;
    private javax.swing.JButton nuevoLabBTN;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
