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
@Table(name = "reservas")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;
    
    @Column(name = "hora_inicio", nullable = false)
    @Temporal (TemporalType.TIME)
    private Calendar horaInicio;
    
    @Column(name = "hora_fin", nullable = true)
    @Temporal (TemporalType.TIME)
    private Calendar horaFin;
    
    @Column(name = "minutos",nullable = false)
    private int minutos;

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

    public Reserva(Calendar horaInicio,int minutos, Computadora computadora, Estudiante estudiante, Horario horario) {
        this.horaInicio = horaInicio;
        this.minutos = minutos;
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

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
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
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", minutos=" + minutos +
                ", computadora=" + computadora.getIdComputadora() +
                ", estudiante=" + estudiante.getIdEstudiante() +
                ", horario=" + horario.getIdHorario() +
                '}';
    }
    
}
