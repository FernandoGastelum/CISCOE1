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
public class LaboratorioDTOEditar {
    
    private String nombre;
    private Calendar horaApertura;
    private Calendar horaCierre;
    private String contrasenaMaestra;

    /**
     * Constructor por ausencia
     */
    public LaboratorioDTOEditar() {
    }

    public LaboratorioDTOEditar(String nombre, Calendar horaApertura, Calendar horaCierre, String contrasenaMaestra) {
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.contrasenaMaestra = contrasenaMaestra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getContrasenaMaestra() {
        return contrasenaMaestra;
    }

    public void setContrasenaMaestra(String contrasenaMaestra) {
        this.contrasenaMaestra = contrasenaMaestra;
    }

    @Override
    public String toString() {
        return "EditarLaboratorioDTO{" + "nombre=" + nombre + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + ", contrasenaMaestra=" + contrasenaMaestra + '}';
    }
    
    
    
}
