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
public class ComputadoraDTO {
    
    private Long idComputadora;
    private Integer numeroMaquina;
    private String direccionIp;
    private Boolean estatus;
    private String tipo;
    private Laboratorio laboratorio;
    private Carrera carrera;

    /**
     * Constructor por ausencia
     */
    public ComputadoraDTO() {
    }

    public ComputadoraDTO(Long idComputadora, Integer numeroMaquina, String direccionIp, Boolean estatus, Laboratorio laboratorio, Carrera carrera,String tipo) {
        this.idComputadora = idComputadora;
        this.numeroMaquina = numeroMaquina;
        this.direccionIp = direccionIp;
        this.tipo = tipo;
        this.estatus = estatus;
        this.laboratorio = laboratorio;
        this.carrera = carrera;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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
        return "ComputadoraDTO{" + "idComputadora=" + idComputadora + ", numeroMaquina=" + numeroMaquina + ", direccionIp=" + direccionIp + ", estatus=" + estatus + ", tipo=" + tipo + ", laboratorio=" + laboratorio + ", carrera=" + carrera + '}';
    }
    
}
