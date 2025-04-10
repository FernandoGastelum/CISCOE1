/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloReservas;

import Utilidades.ColorUtil;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.HorarioDTO;
import DTOs.ReservaDTO;
import DTOs.ReservaDTOGuardar;
import Excepcion.NegocioException;
import ModuloReservas.Interfaz.IReservaNegocio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author gaspa
 */
public class FrmConfirmarReserva extends javax.swing.JFrame {
    private final ComputadoraDTO computadoraDTO;
    private final EstudianteDTO estudianteDTO;
    private final HorarioDTO horarioDTO;
    private final IReservaNegocio reservaNegocio;
    private final FrmReservas frmReservas;
    private final String minutos;
    
    /**
     * 
     * @param computadoraDTO
     * @param estudianteDTO
     * @param minutos
     * @param frmReservas
     * @param reservaNegocio 
     */
    public FrmConfirmarReserva(ComputadoraDTO computadoraDTO,EstudianteDTO estudianteDTO,HorarioDTO horarioDTO, String minutos,FrmReservas frmReservas,IReservaNegocio reservaNegocio) {
        this.computadoraDTO = computadoraDTO;
        this.estudianteDTO = estudianteDTO;
        this.horarioDTO = horarioDTO;
        this.frmReservas = frmReservas;
        this.reservaNegocio = reservaNegocio;
        this.minutos = minutos;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        initComponents();
        this.computadorasPanel.setLayout(new BoxLayout(this.computadorasPanel, BoxLayout.X_AXIS));
        this.computadorasPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.computadorasPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        this.cargarComputadora();
    }
    
    public void cargarComputadora(){
        
        Icon icono = new ImageIcon(new ImageIcon(getClass().getResource("/images/PcIcon.png")).getImage().getScaledInstance(150, 150, 0));
        imagenLabel.setIcon(icono);
        this.numeroComputadoraLabel.setText(computadoraDTO.getNumeroMaquina().toString());
        Color color = ColorUtil.parseColor(computadoraDTO.getCarrera().getColor());
        this.colorPanel.setBackground(color);
        this.colorPanel.setPreferredSize(new Dimension(150, 150));
        this.colorPanel.setMaximumSize(new Dimension(150, 150));
        this.colorPanel.setMinimumSize(new Dimension(150, 150));     
        
        this.estudianteLabel.setText("Estudiante: "+estudianteDTO.getIdInstitucional());
        this.tiempoLabel.setText("Tiempo: "+minutos+" minutos");
        System.out.println("Minutos:"+minutos);
        this.numeroEquipoLabel.setText("Equipo numero: "+computadoraDTO.getNumeroMaquina().toString());
        this.SoftwareLabel.setText("Software del equipo: "+computadoraDTO.getCarrera().getNombreCarrera());
        this.revalidate();
        this.repaint();
    }
    private void volver() {
        frmReservas.habilitarVentana();
        frmReservas.cargarComputadoras();
        frmReservas.setVentanaReservaAbierta(false);
        frmReservas.cargarMinutosdDisponibles();
        this.dispose();
    }
    private void guardarReserva() {
        if ("".equals(minutos)) {
            JOptionPane.showMessageDialog(this, "Minutos no puede estar vacío");
            this.volver();
            return;
        }

        if (frmReservas.getMinutosDisponibles() < Integer.parseInt(minutos)) {
            JOptionPane.showMessageDialog(this, "El tiempo que tiene disponible no es suficiente.");
            this.volver();
            return;
        }

        boolean reservaActiva = false;
        ReservaDTOGuardar dto = new ReservaDTOGuardar();
        dto.setHoraInicio(Calendar.getInstance());
        dto.setComputadoraDTO(computadoraDTO);
        dto.setEstudianteDTO(estudianteDTO);
        dto.setHorario(horarioDTO);
        dto.setMinutos(Integer.parseInt(minutos));

        try {
            List<ReservaDTO> listaReservas = reservaNegocio.obtener();
            if (listaReservas != null) {
                for (ReservaDTO reserva : listaReservas) {
                    if (reserva == null || reserva.getEstudiante() == null) continue;

                    String idInstitucionalReserva = reserva.getEstudiante().getIdInstitucional();
                    if (idInstitucionalReserva != null && idInstitucionalReserva.equals(estudianteDTO.getIdInstitucional())) {
                        if (reserva.getHoraFin() == null) {
                            reservaActiva = true;
                            JOptionPane.showMessageDialog(this, "El estudiante tiene una reserva activa, primero libere la reserva antes de hacer una nueva");
                            break;
                        }
                    }
                }
            }

            if (!reservaActiva) {
                ReservaDTO resultado = reservaNegocio.guardar(dto);
                JOptionPane.showMessageDialog(this, "Reserva guardada con éxito para equipo " + resultado.getComputadora().getNumeroMaquina());
                this.dispose();
                frmReservas.cerrar();
            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la reserva: " + ex.getMessage());
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
        jPanel2 = new javax.swing.JPanel();
        computadorasPanel = new javax.swing.JPanel();
        numeroComputadoraLabel = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        imagenLabel = new javax.swing.JLabel();
        numeroEquipoLabel = new javax.swing.JLabel();
        tiempoLabel = new javax.swing.JLabel();
        SoftwareLabel = new javax.swing.JLabel();
        estudianteLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        confirmarBTN = new javax.swing.JButton();
        cancelarBTN = new javax.swing.JButton();
        carrerasBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(47, 47, 47));

        jPanel2.setBackground(new java.awt.Color(86, 86, 86));

        computadorasPanel.setBackground(new java.awt.Color(102, 102, 102));

        numeroComputadoraLabel.setForeground(new java.awt.Color(255, 255, 255));
        numeroComputadoraLabel.setText("0");

        imagenLabel.setMaximumSize(new java.awt.Dimension(500, 500));
        imagenLabel.setMinimumSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(imagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout computadorasPanelLayout = new javax.swing.GroupLayout(computadorasPanel);
        computadorasPanel.setLayout(computadorasPanelLayout);
        computadorasPanelLayout.setHorizontalGroup(
            computadorasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, computadorasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, computadorasPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(numeroComputadoraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        computadorasPanelLayout.setVerticalGroup(
            computadorasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computadorasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numeroComputadoraLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        numeroEquipoLabel.setBackground(new java.awt.Color(255, 255, 255));
        numeroEquipoLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        numeroEquipoLabel.setForeground(new java.awt.Color(255, 255, 255));
        numeroEquipoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroEquipoLabel.setText("Equipo Numero: ");

        tiempoLabel.setBackground(new java.awt.Color(255, 255, 255));
        tiempoLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tiempoLabel.setForeground(new java.awt.Color(255, 255, 255));
        tiempoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tiempoLabel.setText("Tiempo:");

        SoftwareLabel.setBackground(new java.awt.Color(255, 255, 255));
        SoftwareLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SoftwareLabel.setForeground(new java.awt.Color(255, 255, 255));
        SoftwareLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SoftwareLabel.setText("Software del equipo:");

        estudianteLabel.setBackground(new java.awt.Color(255, 255, 255));
        estudianteLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        estudianteLabel.setForeground(new java.awt.Color(255, 255, 255));
        estudianteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estudianteLabel.setText("Estudiante:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SoftwareLabel)
                    .addComponent(tiempoLabel)
                    .addComponent(numeroEquipoLabel)
                    .addComponent(estudianteLabel))
                .addGap(278, 278, 278)
                .addComponent(computadorasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(tiempoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numeroEquipoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SoftwareLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(estudianteLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(computadorasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 80)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Confirme su seleccion");

        confirmarBTN.setBackground(new java.awt.Color(86, 86, 86));
        confirmarBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        confirmarBTN.setForeground(new java.awt.Color(255, 255, 255));
        confirmarBTN.setText("Confirmar");
        confirmarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarBTNActionPerformed(evt);
            }
        });

        cancelarBTN.setBackground(new java.awt.Color(86, 86, 86));
        cancelarBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cancelarBTN.setForeground(new java.awt.Color(255, 255, 255));
        cancelarBTN.setText("Cancelar");
        cancelarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBTNActionPerformed(evt);
            }
        });

        carrerasBTN.setBackground(new java.awt.Color(86, 86, 86));
        carrerasBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        carrerasBTN.setForeground(new java.awt.Color(255, 255, 255));
        carrerasBTN.setText("Carreras");
        carrerasBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrerasBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(carrerasBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cancelarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(carrerasBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBTNActionPerformed
        this.guardarReserva();
    }//GEN-LAST:event_confirmarBTNActionPerformed

    private void cancelarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBTNActionPerformed
        this.volver();
        
    }//GEN-LAST:event_cancelarBTNActionPerformed

    private void carrerasBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrerasBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carrerasBTNActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SoftwareLabel;
    private javax.swing.JButton cancelarBTN;
    private javax.swing.JButton carrerasBTN;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JPanel computadorasPanel;
    private javax.swing.JButton confirmarBTN;
    private javax.swing.JLabel estudianteLabel;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel numeroComputadoraLabel;
    private javax.swing.JLabel numeroEquipoLabel;
    private javax.swing.JLabel tiempoLabel;
    // End of variables declaration//GEN-END:variables
}
