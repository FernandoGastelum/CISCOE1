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
public class BloqueoDTOGuardar {
    
    private Calendar fechaBloqueo;
    private String motivo;
    private Estudiante estudiante;

    /**
     * Constructor por ausencia
     */
    public BloqueoDTOGuardar() {
    }

    public BloqueoDTOGuardar(Calendar fechaBloqueo, String motivo, Estudiante estudiante) {
        this.fechaBloqueo = fechaBloqueo;
        this.motivo = motivo;
        this.estudiante = estudiante;
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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "BloqueoDTOGuardar{" + "fechaBloqueo=" + fechaBloqueo + ", motivo=" + motivo + ", estudiante=" + estudiante + '}';
    }
    
}
