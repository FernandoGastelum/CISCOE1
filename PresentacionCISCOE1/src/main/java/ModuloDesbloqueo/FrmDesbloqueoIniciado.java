/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloDesbloqueo;

import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.ReservaDTO;
import DTOs.ReservaDTOEditar;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloReservas.Interfaz.IReservaNegocio;
import Utilidades.ColorUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author gaspa
 */
public class FrmDesbloqueoIniciado extends javax.swing.JFrame {
    private IReservaNegocio reservaNegocio;
    private EstudianteDTO estudianteDTO;
    private ComputadoraDTO computadoraDTO;  
    private ReservaDTO reservaDTO;
    /**
     * Creates new form FrmDesbloqueoIniciado
     */
    public FrmDesbloqueoIniciado(IReservaNegocio reservaNegocio ,ComputadoraDTO computadoraDTO, ReservaDTO reservaDTO) {
        this.reservaNegocio = reservaNegocio;
        this.computadoraDTO = computadoraDTO;
        this.reservaDTO = reservaDTO;
        initComponents();
        this.cargarFondo();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        SwingUtilities.invokeLater(() -> cargarComputadora(computadoraDTO));
        this.cargarMinutos();
    }
    private void cargarFondo(){
        Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/images/Background.png")).getImage().getScaledInstance(1920, 1080, 0));
        lblBackground.setIcon(icono);
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
    private void cargarMinutos() {
        Calendar horaInicio = reservaDTO.getHoraInicio();
        Calendar horaActual = Calendar.getInstance();

        // Convertir a minutos desde medianoche
        int minutosInicio = horaInicio.get(Calendar.HOUR_OF_DAY) * 60 + horaInicio.get(Calendar.MINUTE);
        int minutosActual = horaActual.get(Calendar.HOUR_OF_DAY) * 60 + horaActual.get(Calendar.MINUTE);

        int duracionMinutos = reservaDTO.getMinutos();
        int minutosLimite = minutosInicio + duracionMinutos;

        // Convertimos minutos límite en un Calendar para mostrar la hora límite
        Calendar horaLimite = (Calendar) horaInicio.clone();
        horaLimite.set(Calendar.HOUR_OF_DAY, minutosLimite / 60);
        horaLimite.set(Calendar.MINUTE, minutosLimite % 60);

        System.out.println("Hora de inicio: " + horaInicio.getTime());
        System.out.println("Duración minutos: " + duracionMinutos);
        System.out.println("Hora límite: " + horaLimite.getTime());
        System.out.println("Hora actual: " + horaActual.getTime());

        if (minutosActual >= minutosLimite) {
            JOptionPane.showMessageDialog(this, "El tiempo de la reserva ya ha expirado. Se liberará la computadora.");
            this.liberarReserva();
        }else{
            timer(minutosLimite, minutosActual);
        }
        
    }
    private void timer(int minutosLimite, int minutosActual){
        int segundosRestantes = (minutosLimite - minutosActual) * 60;

        Timer timer = new Timer(1000, new ActionListener() {
            int segundos = segundosRestantes;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos > 0) {
                    segundos--;
                    int min = segundos / 60;
                    int seg = segundos % 60;
                    tiempoLabel.setText(String.format("Tiempo Restante: %02d:%02d", min, seg));
                } else {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Tiempo agotado. Se liberará la computadora.");
                    liberarReserva();
                }
            }
        });

        timer.start();
    }
    private void cerrar(){
        this.dispose();
    }
    public void liberarReserva() {
        reservaDTO.setHoraFin(Calendar.getInstance());
        ReservaDTOEditar reservaDTOEditar = new ReservaDTOEditar(reservaDTO);
        try {
            ReservaDTO resultado = reservaNegocio.actualizar(reservaDTOEditar);
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            String soloHora = formatoHora.format(resultado.getHoraFin().getTime());
            JOptionPane.showMessageDialog(this, "Reserva liberada con éxito con hora de fin: " + soloHora);
            this.dispose();
        } catch (NegocioException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            this.dispose(); 
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        numeroComputadoraLabel = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        imagenLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tiempoLabel = new javax.swing.JLabel();
        desbloquearBTN = new javax.swing.JButton();
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

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sesion Iniciada");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 232, -1, -1));

        tiempoLabel.setBackground(new java.awt.Color(0, 0, 0));
        tiempoLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tiempoLabel.setForeground(new java.awt.Color(255, 255, 255));
        tiempoLabel.setText("Tiempo Restante:");
        getContentPane().add(tiempoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 337, -1, -1));

        desbloquearBTN.setBackground(new java.awt.Color(51, 51, 51));
        desbloquearBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        desbloquearBTN.setForeground(new java.awt.Color(255, 255, 255));
        desbloquearBTN.setText("Finalizar Sesion");
        desbloquearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desbloquearBTNActionPerformed(evt);
            }
        });
        getContentPane().add(desbloquearBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 715, 331, -1));
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1920, 920));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void desbloquearBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desbloquearBTNActionPerformed
        this.liberarReserva();
    }//GEN-LAST:event_desbloquearBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel colorPanel;
    private javax.swing.JButton desbloquearBTN;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel numeroComputadoraLabel;
    private javax.swing.JLabel tiempoLabel;
    // End of variables declaration//GEN-END:variables
}
