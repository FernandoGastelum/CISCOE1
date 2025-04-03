/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.Calendar;

/**
 *
 * @author Knocmare
 */
public class BloqueoDTOGuardar {
    
    private Calendar fechaBloqueo;
    private String motivo;

    /**
     * Constructor por ausencia
     */
    public BloqueoDTOGuardar() {
    }

    public BloqueoDTOGuardar(Calendar fechaBloqueo, String motivo) {
        this.fechaBloqueo = fechaBloqueo;
        this.motivo = motivo;
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

    @Override
    public String toString() {
        return "BloqueoDTOGuardar{" + "fechaBloqueo=" + fechaBloqueo + ", motivo=" + motivo + '}';
    }
    
    
}
