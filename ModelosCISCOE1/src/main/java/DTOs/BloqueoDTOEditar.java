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
public class BloqueoDTOEditar {
    private Long idBloqueo;
    private Calendar fechaBloqueo;
    private Calendar fechaLiberacion;
    private String motivo;
    private EstudianteDTO estudianteDTO;

    /**
     * Constructor por ausencia
     */
    public BloqueoDTOEditar() {
    }

    public BloqueoDTOEditar(Long idBloqueo, Calendar fechaBloqueo, Calendar fechaLiberacion, String motivo, EstudianteDTO estudianteDTO) {
        this.fechaBloqueo = fechaBloqueo;
        this.fechaLiberacion = fechaLiberacion;
        this.motivo = motivo;
        this.idBloqueo = idBloqueo;
        this.estudianteDTO = estudianteDTO;
    }

    public Long getIdBloqueo() {
        return idBloqueo;
    }

    public void setIdBloqueo(Long idBloqueo) {
        this.idBloqueo = idBloqueo;
    }

    public Calendar getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Calendar fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public Calendar getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(Calendar fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstudianteDTO getEstudianteDTO() {
        return estudianteDTO;
    }

    public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
        this.estudianteDTO = estudianteDTO;
    }

    @Override
    public String toString() {
        return "BloqueoDTOEditar{" + "fechaBloqueo=" + fechaBloqueo + ", fechaLiberacion=" + fechaLiberacion + ", motivo=" + motivo + ", estudianteDTO=" + estudianteDTO + '}';
    }
    
}
