/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * @author gaspa
 */
@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;
    
    @Column(name = "fecha_reserva", nullable = false)
    @Temporal (TemporalType.DATE)
    private Calendar fechaReserva;
    
    @Column(name = "hora_inicio", nullable = false)
    @Temporal (TemporalType.TIME)
    private Calendar horaInicio;
    
    @Column(name = "hora_fin", nullable = false)
    @Temporal (TemporalType.TIME)
    private Calendar horaFin;

    @ManyToOne
    @JoinColumn(name = "id_computadora", nullable = false)
    private Computadora computadora;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_horario", nullable = false)
    private Horario horario;

    // Constructores
    public Reserva() {
    }

    public Reserva(Calendar fechaReserva, Calendar horaInicio, Calendar horaFin, Computadora computadora, Estudiante estudiante, Horario horario) {
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.computadora = computadora;
        this.estudiante = estudiante;
        this.horario = horario;
    }

    // Getters y Setters
    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
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
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", fechaReserva=" + fechaReserva +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", computadora=" + computadora.getIdComputadora() +
                ", estudiante=" + estudiante.getIdEstudiante() +
                ", horario=" + horario.getIdHorario() +
                '}';
    }
    
}
