/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Carrera;

/**
 *
 * @author Ángel Ruíz
 */
public class EstudianteDTO {
    
    private Long idEstudiante;
    private String idInstitucional;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Boolean estatusInscripcion;
    private String contrasena;
    private Carrera carrera;

    /**
     * Constructor por ausencia
     */
    public EstudianteDTO() {
    }

    public EstudianteDTO(Long idEstudiante, String idInstitucional, String nombre, String apellidoPaterno, String apellidoMaterno, Boolean estatusInscripcion, String contrasena, Carrera carrera) {
        this.idEstudiante = idEstudiante;
        this.idInstitucional = idInstitucional;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estatusInscripcion = estatusInscripcion;
        this.contrasena = contrasena;
        this.carrera = carrera;
    }

    public String getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(String idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Boolean getEstatusInscripcion() {
        return estatusInscripcion;
    }

    public void setEstatusInscripcion(Boolean estatusInscripcion) {
        this.estatusInscripcion = estatusInscripcion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return idInstitucional + " " + nombre + " " + apellidoPaterno + "" + estatusInscripcion + " " + carrera.toString();
    }

    
    
}
