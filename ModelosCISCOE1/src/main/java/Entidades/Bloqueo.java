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
@Table(name = "bloqueo")
public class Bloqueo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloqueo")
    private Long idBloqueo;

    @Column(name = "fecha_bloqueo", nullable = false)
    @Temporal (TemporalType.DATE)
    private Calendar fechaBloqueo;

    @Column(name = "fecha_liberacion", nullable = true)
    @Temporal (TemporalType.DATE)
    private Calendar fechaLiberacion;

    @Column(name = "motivo", nullable = false, length = 250)
    private String motivo;

    // Constructores
    public Bloqueo() {
    }

    public Bloqueo(Calendar fechaBloqueo, String motivo) {
        this.fechaBloqueo = fechaBloqueo;
        this.motivo = motivo;
    }

    // Getters y Setters
    public Long getIdBloqueo() {
        return idBloqueo;
    }

    public void setIdBloqueo(Long idBloqueo) {
        this.idBloqueo = idBloqueo;
    }

    public Calendar getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Calendar fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Calendar getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(Calendar fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    @Override
    public String toString() {
        return "Bloqueo{" +
                "idBloqueo=" + idBloqueo +
                ", fechaBloqueo=" + fechaBloqueo +
                ", motivo='" + motivo + '\'' +
                '}';
    }
    
}
