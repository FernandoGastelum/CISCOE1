package DTOs;

import java.util.Calendar;

/**
 *
 * @author Knocmare
 */
public class BloqueoTablaDTO {
    
    private Long idBloqueo;
    private Calendar fechaBloqueo;
    private String motivo;
    private String idInstitucional;
    private Calendar fechaLiberacion;

    public BloqueoTablaDTO(Long idBloqueo, Calendar fechaBloqueo, String motivo, String idInstitucional, Calendar fechaLiberacion) {
        this.idBloqueo = idBloqueo;
        this.fechaBloqueo = fechaBloqueo;
        this.motivo = motivo;
        this.idInstitucional = idInstitucional;
        this.fechaLiberacion = fechaLiberacion;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(String idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public Calendar getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(Calendar fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }
    
    
    
}
