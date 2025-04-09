/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloReservas;

import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.HorarioDTO;
import DTOs.LaboratorioDTO;
import Excepcion.NegocioException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloAdministracion.Interfaz.IHorarioNegocio;
import ModuloAdministracion.Negocio.ComputadoraNegocio;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloReservas.Interfaz.IReservaNegocio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ilian Gastelum,
 */
public class FrmReservas extends javax.swing.JFrame {
    private IComputadoraNegocio computadoraNegocio;
    private IEstudianteNegocio estudianteNegocio;
    private IHorarioNegocio horarioNegocio;
    private IReservaNegocio reservaNegocio;
    private LaboratorioDTO laboratorioDTO;
    private String idUsuario;
    private boolean ventanaReservaAbierta = false;
    /**
     * 
     * @param computadoraNegocio
     * @param estudianteNegocio
     * @param horarioNegocio
     * @param idUsuario
     * @param reservaNegocio
     * @param laboratorioDTO 
     */
    public FrmReservas(IComputadoraNegocio computadoraNegocio,IEstudianteNegocio estudianteNegocio, IHorarioNegocio horarioNegocio,String idUsuario,IReservaNegocio reservaNegocio,LaboratorioDTO laboratorioDTO) {
        this.computadoraNegocio = computadoraNegocio;
        this.estudianteNegocio = estudianteNegocio;
        this.reservaNegocio = reservaNegocio;
        this.horarioNegocio = horarioNegocio;
        this.laboratorioDTO = laboratorioDTO;
        this.idUsuario = idUsuario;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.computadorasPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.computadorasPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.computadorasPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.computadorasScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.computadorasScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.computadorasScrollPanel.setPreferredSize(new Dimension(1800, 600));
        
        this.cargarComputadoras();
    }
    public void cargarComputadoras(){
        try {
            this.computadorasPanel.removeAll();
            this.computadorasPanel.repaint();
            List<ComputadoraDTO> listaComputadoras = computadoraNegocio.obtener();
            if(listaComputadoras != null){
                for (ComputadoraDTO listaComputadora : listaComputadoras) {
                    if(listaComputadora.getLaboratorio().getIdLaboratorio()
                            .equals(laboratorioDTO.getIdLaboratorio())
                            &&listaComputadora.getTipo().equals("Comun")){
                        
                        ComputadoraPanel panel = new ComputadoraPanel(
                            listaComputadora,
                            this.cargarEstudianteLogeado(),
                            this.cargarHorario(listaComputadora.getLaboratorio().getIdLaboratorio()),
                            idUsuario,
                            true,
                            this,
                            reservaNegocio);
                        this.computadorasPanel.add(panel);
                    }
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.revalidate();
        this.repaint();
    }
    public String getMinutos(){
        return this.minutosTextField.getText();
    }
    public EstudianteDTO cargarEstudianteLogeado() throws NegocioException{
        EstudianteDTO estudianteDTO = estudianteNegocio.obtenerPorIdInstitucional(idUsuario);
        return estudianteDTO;
    }
    public HorarioDTO cargarHorario(Long idLaboratorio) throws NegocioException{
        HorarioDTO horarioDTO = horarioNegocio.obtenerHorarioActivoPorLaboratorio(idLaboratorio);
        return horarioDTO;
    }
    public void deshabilitarVentana() {
        this.setEnabled(false);

        // Crear un glass pane translúcido
        JPanel glass = new JPanel();
        glass.setOpaque(true);
        glass.setBackground(new Color(0, 0, 0, 80)); // Color negro translúcido
        this.setGlassPane(glass);
        glass.setVisible(true);
    }
    public void habilitarVentana() {
        this.setEnabled(true);
        this.getGlassPane().setVisible(false);
    }
    public boolean isVentanaReservaAbierta() {
        return ventanaReservaAbierta;
    }

    public void setVentanaReservaAbierta(boolean abierta) {
        this.ventanaReservaAbierta = abierta;
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
        minutosDisponiblesLabel = new javax.swing.JLabel();
        minutosTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        computadorasScrollPanel = new javax.swing.JScrollPane();
        computadorasPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        carrerasBTN = new javax.swing.JButton();
        salirBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1980, 1080));
        setMinimumSize(new java.awt.Dimension(1980, 1080));
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(47, 47, 47));
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setName(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(86, 86, 86));
        jPanel2.setMaximumSize(new java.awt.Dimension(1756, 734));
        jPanel2.setMinimumSize(new java.awt.Dimension(1756, 734));

        minutosDisponiblesLabel.setBackground(new java.awt.Color(255, 255, 255));
        minutosDisponiblesLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        minutosDisponiblesLabel.setForeground(new java.awt.Color(255, 255, 255));
        minutosDisponiblesLabel.setText("---");

        minutosTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        minutosTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutosTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Minutos:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tiempo disponible:");

        computadorasScrollPanel.setMaximumSize(new java.awt.Dimension(1852, 611));
        computadorasScrollPanel.setMinimumSize(new java.awt.Dimension(1852, 611));
        computadorasScrollPanel.setPreferredSize(new java.awt.Dimension(1852, 611));
        computadorasScrollPanel.setRequestFocusEnabled(false);

        computadorasPanel.setBackground(new java.awt.Color(86, 86, 86));

        javax.swing.GroupLayout computadorasPanelLayout = new javax.swing.GroupLayout(computadorasPanel);
        computadorasPanel.setLayout(computadorasPanelLayout);
        computadorasPanelLayout.setHorizontalGroup(
            computadorasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1852, Short.MAX_VALUE)
        );
        computadorasPanelLayout.setVerticalGroup(
            computadorasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );

        computadorasScrollPanel.setViewportView(computadorasPanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(minutosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minutosDisponiblesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(computadorasScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jLabel3)
                    .addContainerGap(1736, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(1475, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(218, 218, 218)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(minutosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutosDisponiblesLabel))
                .addGap(50, 50, 50)
                .addComponent(computadorasScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jLabel3)
                    .addContainerGap(671, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jLabel4)
                    .addContainerGap(665, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccione el equipo");

        carrerasBTN.setBackground(new java.awt.Color(86, 86, 86));
        carrerasBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        carrerasBTN.setForeground(new java.awt.Color(255, 255, 255));
        carrerasBTN.setText("Carreras");
        carrerasBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrerasBTNActionPerformed(evt);
            }
        });

        salirBTN.setBackground(new java.awt.Color(86, 86, 86));
        salirBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        salirBTN.setForeground(new java.awt.Color(255, 255, 255));
        salirBTN.setText("Salir");
        salirBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(847, 847, 847)
                        .addComponent(carrerasBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(salirBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(carrerasBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salirBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void carrerasBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrerasBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carrerasBTNActionPerformed

    private void salirBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salirBTNActionPerformed

    private void minutosTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutosTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutosTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton carrerasBTN;
    private javax.swing.JPanel computadorasPanel;
    private javax.swing.JScrollPane computadorasScrollPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel minutosDisponiblesLabel;
    public javax.swing.JTextField minutosTextField;
    private javax.swing.JButton salirBTN;
    // End of variables declaration//GEN-END:variables
}
