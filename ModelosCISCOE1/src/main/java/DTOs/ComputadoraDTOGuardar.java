/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Carrera;
import Entidades.Laboratorio;

/**
 *
 * @author Knocmare
 */
public class ComputadoraDTOGuardar {
    
    private Integer numeroMaquina;
    private String direccionIp;
    private Boolean estatus;
    private Laboratorio laboratorio;
    private Carrera carrera;

    /**
     * Constructor por ausencia
     */
    public ComputadoraDTOGuardar() {
    }

    public ComputadoraDTOGuardar(Integer numeroMaquina, String direccionIp, Laboratorio laboratorio, Carrera carrera) {
        this.numeroMaquina = numeroMaquina;
        this.direccionIp = direccionIp;
        this.estatus = true;
        this.laboratorio = laboratorio;
        this.carrera = carrera;
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
        return "ComputadoraDTOGuardar{" + "numeroMaquina=" + numeroMaquina + ", direccionIp=" + direccionIp + ", estatus=" + estatus + ", laboratorio=" + laboratorio + ", carrera=" + carrera + '}';
    }
    
    
}
