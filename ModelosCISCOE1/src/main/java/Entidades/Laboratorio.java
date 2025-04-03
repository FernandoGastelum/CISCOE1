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
@Table(name = "laboratorio")
public class Laboratorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio")
    private Long idLaboratorio;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "hora_apertura", nullable = false)
    @Temporal (TemporalType.TIME)
    private Calendar horaApertura;

    @Column(name = "hora_cierre", nullable = false)
    @Temporal (TemporalType.TIME)
    private Calendar horaCierre;

    @Column(name = "contrasena_maestra", nullable = false, length = 255)
    private String contrasenaMaestra;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_instituto",nullable = false)
    private Instituto instituto;
    
    @OneToMany(mappedBy = "laboratorio")
    private List<Computadora> computadoras;

    // Constructores
    public Laboratorio() {
    }

    public Laboratorio(String nombre, Calendar horaApertura, Calendar horaCierre, String contrasenaMaestra, Instituto instituto) {
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.contrasenaMaestra = contrasenaMaestra;
        this.instituto = instituto;
    }

    // Getters y Setters
    public Long getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Long idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public List<Computadora> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(List<Computadora> computadoras) {
        this.computadoras = computadoras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getContrasenaMaestra() {
        return contrasenaMaestra;
    }

    public void setContrasenaMaestra(String contrasenaMaestra) {
        this.contrasenaMaestra = contrasenaMaestra;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
                "idLaboratorio=" + idLaboratorio +
                ", nombre='" + nombre + '\'' +
                ", horaApertura=" + horaApertura +
                ", horaCierre=" + horaCierre +
                ", contrasenaMaestra='" + contrasenaMaestra + '\'' +
                ", instituto=" + instituto.getIdInstituto() +
                '}';
    }
}
