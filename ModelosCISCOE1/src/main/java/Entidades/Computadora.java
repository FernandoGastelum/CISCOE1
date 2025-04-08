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
@Table(name = "computadora")
public class Computadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computadora")
    private Long idComputadora;

    @Column(name = "numero_maquina", nullable = false, unique = true)
    private Integer numeroMaquina;

    @Column(name = "direccion_ip", nullable = false, unique = true, length = 15)
    private String direccionIp;

    @Column(name = "estatus", nullable = false)
    private Boolean estatus;

    @ManyToOne
    @JoinColumn(name = "id_laboratorio", nullable = false)
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    // Constructores
    public Computadora() {
    }

    public Computadora(Integer numeroMaquina, String direccionIp, Laboratorio laboratorio, Carrera carrera) {
        this.numeroMaquina = numeroMaquina;
        this.direccionIp = direccionIp;
        this.estatus = true;
        this.laboratorio = laboratorio;
        this.carrera = carrera;
    }

    // Getters y Setters
    public Long getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(Long idComputadora) {
        this.idComputadora = idComputadora;
    }

    public Integer getNumeroMaquina() {
        return numeroMaquina;
    }

    public void setNumeroMaquina(Integer numeroMaquina) {
        this.numeroMaquina = numeroMaquina;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Computadora{" +
                "idComputadora=" + idComputadora +
                ", numeroMaquina=" + numeroMaquina +
                ", direccionIp='" + direccionIp + '\'' +
                ", estatus=" + estatus +
                ", laboratorio=" + laboratorio.getIdLaboratorio() +
                ", carrera=" + carrera.getIdCarrera() +
                '}';
    }
    
}
