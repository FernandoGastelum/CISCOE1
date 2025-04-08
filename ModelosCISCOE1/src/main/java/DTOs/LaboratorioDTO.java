/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Instituto;
import java.util.Calendar;

/**
 *
 * @author Ángel Ruíz
 */
public class LaboratorioDTO {
    
    private Long idLaboratorio;
    private String nombre;
    private Calendar horaApertura;
    private Calendar horaCierre;
    private String contrasenaMaestra;
    private Instituto instituto;

    /**
     * Constructor por ausencia
     */
    public LaboratorioDTO() {
    }
    
    public LaboratorioDTO(Long idLaboratorio, String nombre, Calendar horaApertura, Calendar horaCierre, String contrasenaMaestra, Instituto instituto) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.contrasenaMaestra = contrasenaMaestra;
        this.instituto = instituto;
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

    public String getContrasenaMaestra() {
        return contrasenaMaestra;
    }

    public void setContrasenaMaestra(String contrasenaMaestra) {
        this.contrasenaMaestra = contrasenaMaestra;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}
