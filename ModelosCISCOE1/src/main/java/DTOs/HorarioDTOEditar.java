/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Laboratorio;
import java.util.Calendar;

/**
 *
 * @author Knocmare
 */
public class HorarioDTOEditar {
    
    private Calendar horaApertura;
    private Calendar horaCierre;
    private Calendar fecha;
    private Laboratorio laboratorio;

    /**
     * Constructor por ausencia
     */
    public HorarioDTOEditar() {
    }

    public HorarioDTOEditar(Calendar horaApertura, Calendar horaCierre, Calendar fecha, Laboratorio laboratorio) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.fecha = fecha;
        this.laboratorio = laboratorio;
    }

    public Calendar getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Calendar horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Calendar getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Calendar horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return "HorarioDTOEditar{" + "horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + ", fecha=" + fecha + ", laboratorio=" + laboratorio + '}';
    }
    
}
