/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.Calendar;

/**
 *
 * @author gaspa
 */
public class ReporteTablaDTO {
    private String nombreLaboratorio;
    private Calendar fecha;
    private int tiempoServicio;
    private int tiempoUso;
    private int tiempoSinUso;

    public ReporteTablaDTO(String nombreLaboratorio, Calendar fecha, int tiempoServicio, int tiempoUso, int tiempoSinUso) {
        this.nombreLaboratorio = nombreLaboratorio;
        this.fecha = fecha;
        this.tiempoServicio = tiempoServicio;
        this.tiempoUso = tiempoUso;
        this.tiempoSinUso = tiempoSinUso;
    }

    public ReporteTablaDTO() {
    }

    public String getNombreLaboratorio() {
        return nombreLaboratorio;
    }

    public void setNombreLaboratorio(String nombreLaboratorio) {
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public int getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(int tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public int getTiempoSinUso() {
        return tiempoSinUso;
    }

    public void setTiempoSinUso(int tiempoSinUso) {
        this.tiempoSinUso = tiempoSinUso;
    }

    @Override
    public String toString() {
        return "ReporteTablaDTO{" + "nombreLaboratorio=" + nombreLaboratorio + ", fecha=" + fecha + ", tiempoServicio=" + tiempoServicio + ", tiempoUso=" + tiempoUso + ", tiempoSinUso=" + tiempoSinUso + '}';
    }
    
    
}
