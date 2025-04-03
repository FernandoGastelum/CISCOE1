/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Knocmare
 */
public class CarreraDTOGuardar {
    
    private String nombreCarrera;
    private Integer tiempoMaximoDiario;
    private String color;

    /**
     * Constructor por ausencia
     */
    public CarreraDTOGuardar() {
    }

    public CarreraDTOGuardar(String nombreCarrera, Integer tiempoMaximoDiario, String color) {
        this.nombreCarrera = nombreCarrera;
        this.tiempoMaximoDiario = tiempoMaximoDiario;
        this.color = color;
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
        return "CarreraDTOGuardar{" + "nombreCarrera=" + nombreCarrera + ", tiempoMaximoDiario=" + tiempoMaximoDiario + ", color=" + color + '}';
    }
    
    
    
}
