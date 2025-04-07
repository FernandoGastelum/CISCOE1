/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Estudiante;
import java.util.Calendar;

/**
 *
 * @author Knocmare
 */
public class BloqueoDTOEditar {
    
    private Calendar fechaBloqueo;
    private Calendar fechaLiberacion;
    private String motivo;
    private Estudiante estudiante;

    /**
     * Constructor por ausencia
     */
    public BloqueoDTOEditar() {
    }

    public BloqueoDTOEditar(Calendar fechaBloqueo, Calendar fechaLiberacion, String motivo, Estudiante estudiante) {
        this.fechaBloqueo = fechaBloqueo;
        this.fechaLiberacion = fechaLiberacion;
        this.motivo = motivo;
        this.estudiante = estudiante;
    }

    public Calendar getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Calendar fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public Calendar getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(Calendar fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "BloqueoDTOEditar{" + "fechaBloqueo=" + fechaBloqueo + ", fechaLiberacion=" + fechaLiberacion + ", motivo=" + motivo + ", estudiante=" + estudiante + '}';
    }
    
}
