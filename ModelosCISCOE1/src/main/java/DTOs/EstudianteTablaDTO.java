/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Knocmare
 */
public class EstudianteTablaDTO {
    
    private String idInstitucional;
    private String nombreCompleto;
    private String estatusInscripcion;

    public EstudianteTablaDTO(String idInstitucional, String nombreCompleto, String estatusInscripcion) {
        this.idInstitucional = idInstitucional;
        this.nombreCompleto = nombreCompleto;
        this.estatusInscripcion = estatusInscripcion;
    }

    public String getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(String idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEstatusInscripcion() {
        return estatusInscripcion;
    }

    public void setEstatusInscripcion(String estatusInscripcion) {
        this.estatusInscripcion = estatusInscripcion;
    }
    
}
