/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Knocmare
 */
public class ComputadoraTablaDTO {

    private Long idComputadora;
    private Integer numeroMaquina;
    private String direccionIp;
    private String estatus;
    private String tipo;

    public ComputadoraTablaDTO(Long idComputadora, Integer numeroMaquina, String direccionIp, String estatus,String tipo) {
        this.idComputadora = idComputadora;
        this.numeroMaquina = numeroMaquina;
        this.direccionIp = direccionIp;
        this.estatus = estatus;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(Long idComputadora) {
        this.idComputadora = idComputadora;
    }

    public Integer getNumeroMaquina() {
        return numeroMaquina;
    }

    public void setNumeroMaquina(Integer numeroMaquina) {
        this.numeroMaquina = numeroMaquina;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
