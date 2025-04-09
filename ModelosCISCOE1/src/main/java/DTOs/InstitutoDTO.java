/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Ángel Ruíz
 */
public class InstitutoDTO {
    
    private Long idInstituto;
    private String nombreOficial;
    private String nombreAbreviado;

    /**
     * Constructor por ausencia
     */
    public InstitutoDTO() {
    }
    
    public InstitutoDTO(Long idInstituto, String nombreOficial, String nombreAbreviado) {
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

    @Override
    public String toString() {
        return nombreOficial + ", " + nombreAbreviado;
    }
    
    
    
}
