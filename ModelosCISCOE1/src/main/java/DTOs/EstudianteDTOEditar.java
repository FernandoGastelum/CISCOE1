/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Ángel Ruíz
 */
public class EstudianteDTOEditar {
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String contrasena;
    private Boolean estatusInscripcion;
    private CarreraDTO carreraDTO;

    /**
     * Constructor por ausencia
     */
    public EstudianteDTOEditar() {
    }

    public EstudianteDTOEditar(String nombre, String apellidoPaterno, String apellidoMaterno, String contrasena, Boolean estatusInscripcion, CarreraDTO carreraDTO) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.contrasena = contrasena;
        this.estatusInscripcion = estatusInscripcion;
        this.carreraDTO = carreraDTO;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public CarreraDTO getCarreraDTO() {
        return carreraDTO;
    }

    public void setCarreraDTO(CarreraDTO carreraDTO) {
        this.carreraDTO = carreraDTO;
    }

    public Boolean getEstatusInscripcion() {
        return estatusInscripcion;
    }

    public void setEstatusInscripcion(Boolean estatusInscripcion) {
        this.estatusInscripcion = estatusInscripcion;
    }

    @Override
    public String toString() {
        return "EstudianteDTOEditar{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", contrasena=" + contrasena + ", estatusInscripcion=" + estatusInscripcion + ", carreraDTO=" + carreraDTO + '}';
    }
    
    
}
