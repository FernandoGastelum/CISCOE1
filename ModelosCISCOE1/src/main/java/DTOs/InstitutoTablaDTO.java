package DTOs;

/**
 *
 * @author Knocmare
 */
public class InstitutoTablaDTO {

    private Long idInstituto;
    private String nombreOficial;
    private String nombreAbreviado;

    public InstitutoTablaDTO(Long idInstituto, String nombreOficial, String nombreAbreviado) {
        this.idInstituto = idInstituto;
        this.nombreOficial = nombreOficial;
        this.nombreAbreviado = nombreAbreviado;
    }

    public Long getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(Long idInstituto) {
        this.idInstituto = idInstituto;
    }

    public String getNombreOficial() {
        return nombreOficial;
    }

    public void setNombreOficial(String nombreOficial) {
        this.nombreOficial = nombreOficial;
    }

    public String getNombreAbreviado() {
        return nombreAbreviado;
    }

    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }
    
    
}
