/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Knocmare
 */
public class CarreraTablaDTO {

    private Long idCarrera;
    private String nombreCarrera;
    private Integer tiempoMaximoDiario;

    public CarreraTablaDTO(Long idCarrera, String nombreCarrera, Integer tiempoMaximoDiario) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.tiempoMaximoDiario = tiempoMaximoDiario;
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

}
