/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author gaspa
 */

@Entity
@Table(name = "carrera")
public class Carrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long idCarrera;

    @Column(name = "nombre_carrera", nullable = false, length = 100)
    private String nombreCarrera;

    @Column(name = "tiempo_maximo_diario", nullable = false)
    private Integer tiempoMaximoDiario;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    public Carrera() {
    }

    public Carrera(String nombreCarrera, Integer tiempoMaximoDiario, String color) {
        this.nombreCarrera = nombreCarrera;
        this.tiempoMaximoDiario = tiempoMaximoDiario;
        this.color = color;
    }

    public Long getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Integer getTiempoMaximoDiario() {
        return tiempoMaximoDiario;
    }

    public void setTiempoMaximoDiario(Integer tiempoMaximoDiario) {
        this.tiempoMaximoDiario = tiempoMaximoDiario;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                ", tiempoMaximoDiario=" + tiempoMaximoDiario +
                ", color='" + color + '\'' +
                '}';
    }
    
}
