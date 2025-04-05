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
    private Computadora computadora;
    private Estudiante estudiante;
    private Horario horario;

    /**
     * Constructor por ausencia
     */
    public ReservaDTOGuardar() {
    }

    public ReservaDTOGuardar(Calendar fechaReserva, Calendar horaInicio, Computadora computadora, Estudiante estudiante, Horario horario) {
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.computadora = computadora;
        this.estudiante = estudiante;
        this.horario = horario;
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

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "ReservaDTOGuardar{" + "fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", computadora=" + computadora + ", estudiante=" + estudiante + ", horario=" + horario + '}';
    }
    
    
    
    
}
