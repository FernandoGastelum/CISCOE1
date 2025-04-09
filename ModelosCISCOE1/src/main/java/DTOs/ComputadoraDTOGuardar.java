/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Knocmare
 */
public class ComputadoraDTOGuardar {
    
    private Integer numeroMaquina;
    private String direccionIp;
    private Boolean estatus;
    private LaboratorioDTO laboratorioDTO;
    private CarreraDTO carreraDTO;
    private String tipo;

    /**
     * Constructor por ausencia
     */
    public ComputadoraDTOGuardar() {
    }

    public ComputadoraDTOGuardar(Integer numeroMaquina, String direccionIp, LaboratorioDTO laboratorioDTO, CarreraDTO carreraDTO, String tipo) {
        this.numeroMaquina = numeroMaquina;
        this.direccionIp = direccionIp;
        this.estatus = true;
        this.tipo = tipo;
        this.laboratorioDTO = laboratorioDTO;
        this.carreraDTO = carreraDTO;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public LaboratorioDTO getLaboratorioDTO() {
        return laboratorioDTO;
    }

    public void setLaboratorioDTO(LaboratorioDTO laboratorioDTO) {
        this.laboratorioDTO = laboratorioDTO;
    }

    public CarreraDTO getCarreraDTO() {
        return carreraDTO;
    }

    public void setCarreraDTO(CarreraDTO carreraDTO) {
        this.carreraDTO = carreraDTO;
    }

    @Override
    public String toString() {
        return "ComputadoraDTOGuardar{" + "numeroMaquina=" + numeroMaquina + ", direccionIp=" + direccionIp + ", estatus=" + estatus + ", laboratorioDTO=" + laboratorioDTO + ", carreraDTO=" + carreraDTO + ", tipo=" + tipo + '}';
    }
    
}
