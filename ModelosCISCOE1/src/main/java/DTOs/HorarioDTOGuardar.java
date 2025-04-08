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
public class HorarioDTOGuardar {
    
    private Calendar horaApertura;
    private Calendar horaCierre;
    private Calendar fecha;
    private LaboratorioDTO laboratorioDTO;

    /**
     * Constructor por ausencia
     */
    public HorarioDTOGuardar() {
    }

    public HorarioDTOGuardar(Calendar horaApertura, Calendar horaCierre, Calendar fecha, LaboratorioDTO laboratorioDTO) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.fecha = fecha;
        this.laboratorioDTO = laboratorioDTO;
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

    public LaboratorioDTO getLaboratorioDTO() {
        return laboratorioDTO;
    }

    public void setLaboratorioDTO(LaboratorioDTO laboratorioDTO) {
        this.laboratorioDTO = laboratorioDTO;
    }

    @Override
    public String toString() {
        return "HorarioDTOGuardar{" + "horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + ", fecha=" + fecha + ", laboratorioDTO=" + laboratorioDTO + '}';
    }
    
    
}
