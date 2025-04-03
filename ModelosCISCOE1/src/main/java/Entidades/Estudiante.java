/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author gaspa
 */
@Entity
@Table(name = "estudiante")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 50)
    private String apellidoMaterno;

    @Column(name = "estatus_inscripcion", nullable = false)
    private Boolean estatusInscripcion;

    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "id_bloqueo")
    private Bloqueo bloqueo;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String contrasena, Carrera carrera, Bloqueo bloqueo) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estatusInscripcion = true;
        this.contrasena = contrasena;
        this.carrera = carrera;
        this.bloqueo = bloqueo;
    }

    // Getters y Setters
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

    public Bloqueo getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Bloqueo bloqueo) {
        this.bloqueo = bloqueo;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", estatusInscripcion=" + estatusInscripcion +
                ", carrera=" + carrera.getIdCarrera() +
                ", bloqueo=" + (bloqueo != null ? bloqueo.getIdBloqueo() : "Sin bloqueo") +
                '}';
    }
    
}
