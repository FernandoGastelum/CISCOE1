/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Ángel Ruíz
 */
public class InstitutoDTOEditar {
    
    private String nombreOficial;
    private String nombreAbreviado;

    /**
     * Constructor por ausencia
     */
    public InstitutoDTOEditar() {
    }

    public InstitutoDTOEditar(String nombreOficial, String nombreAbreviado) {
        this.nombreOficial = nombreOficial;
        this.nombreAbreviado = nombreAbreviado;
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
        return "EditarInstitutoDTO{" + "nombreOficial=" + nombreOficial + ", nombreAbreviado=" + nombreAbreviado + '}';
    }

    
}
