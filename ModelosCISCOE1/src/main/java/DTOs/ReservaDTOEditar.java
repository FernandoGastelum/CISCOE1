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
 * @author gaspa
 */
public class ReservaDTOEditar {
    private Long Id;
    private Calendar horaInicio;
    private Calendar horaFin;
    private int minutos;
    private Computadora computadora;
    private Estudiante estudiante;
    private Horario horario;

    public ReservaDTOEditar() {
    }

    public ReservaDTOEditar(Calendar horaInicio, Calendar horaFin, int minutos, Computadora computadora, Estudiante estudiante, Horario horario) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.minutos = minutos;
        this.computadora = computadora;
        this.estudiante = estudiante;
        this.horario = horario;
    }
    public ReservaDTOEditar(ReservaDTO reservaDTO){
        this.Id = reservaDTO.getIdReserva();
        this.horaInicio = reservaDTO.getHoraInicio();
        this.horaFin = reservaDTO.getHoraFin();
        this.minutos = reservaDTO.getMinutos();
        this.computadora = reservaDTO.getComputadora();
        this.estudiante = reservaDTO.getEstudiante();
        this.horario = reservaDTO.getHorario();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
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

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
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
        return "ReservaDTOEditar{" + "horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", minutos=" + minutos + ", computadora=" + computadora + ", estudiante=" + estudiante + ", horario=" + horario + '}';
    }
    
    
}
