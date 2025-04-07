/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Computadora;
import Entidades.Estudiante;
import Entidades.Horario;
import java.util.Calendar;

/**
 *
 * @author Knocmare
 */
public class ReservaDTOGuardar {
    
    private Calendar fechaReserva;
    private Calendar horaInicio;
    private Calendar horaFin;
    private Long idComputadora;
    private String idEstudiante;
    private Long idHorario;

    /**
     * Constructor por ausencia
     */
    public ReservaDTOGuardar() {
    }

    public ReservaDTOGuardar(Calendar fechaReserva, Calendar horaInicio, Long idComputadora, String idEstudiante, Long idHorario) {
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.idComputadora = idComputadora;
        this.idEstudiante = idEstudiante;
        this.idHorario = idHorario;
    }

    public Calendar getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Calendar fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Calendar getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Calendar horaFin) {
        this.horaFin = horaFin;
    }

    public Long getComputadora() {
        return idComputadora;
    }

    public void setComputadora(Long idComputadora) {
        this.idComputadora = idComputadora;
    }

    public String getEstudiante() {
        return idEstudiante;
    }

    public void setEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getHorario() {
        return idHorario;
    }

    public void setHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    @Override
    public String toString() {
        return "ReservaDTOGuardar{" + "fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", computadora=" + idComputadora + ", estudiante=" + idEstudiante + ", horario=" + idHorario + '}';
    }
    
    
    
    
}
