/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gaspa
 */
@Entity
@Table(name = "horarios")
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long idHorario;
    
    @Temporal (TemporalType.TIME)
    @Column(name = "hora_apertura", nullable = false)
    private Calendar horaApertura;
    
    @Temporal (TemporalType.TIME)
    @Column(name = "hora_cierre", nullable = false)
    private Calendar horaCierre;

    @Temporal (TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Calendar fecha;

    @ManyToOne
    @JoinColumn(name = "id_laboratorio", nullable = false)
    private Laboratorio laboratorio;
    
    @OneToMany(mappedBy = "horario")
    private List<Reserva> reservas;

    // Constructores
    public Horario() {
    }

    public Horario(Calendar horaApertura, Calendar horaCierre, Calendar fecha, Laboratorio laboratorio) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.fecha = fecha;
        this.laboratorio = laboratorio;
    }

    // Getters y Setters
    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Calendar getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Calendar horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Calendar getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Calendar horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "idHorario=" + idHorario +
                ", horaApertura=" + horaApertura +
                ", horaCierre=" + horaCierre +
                ", fecha=" + fecha +
                ", laboratorio=" + laboratorio.getIdLaboratorio() +
                '}';
    }
    
}
