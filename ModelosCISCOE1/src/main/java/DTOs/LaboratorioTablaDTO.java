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
public class LaboratorioTablaDTO {

    private Long idLaboratorio;
    private String nombre;
    private Calendar horaApertura;
    private Calendar horaCierre;

    public LaboratorioTablaDTO(Long idLaboratorio, String nombre, Calendar horaApertura, Calendar horaCierre) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Long getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Long idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
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
    
}
