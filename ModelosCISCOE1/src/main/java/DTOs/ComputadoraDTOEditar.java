/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Knocmare
 */
public class ComputadoraDTOEditar {
    
    private Long id;
    private Integer numeroMaquina;
    private String direccionIp;
    private Boolean estatus;
    private LaboratorioDTO laboratorioDTO;
    private CarreraDTO carreraDTO;
    private String tipo;

    /**
     * Constructor por ausencia
     */
    public ComputadoraDTOEditar() {
    }

    public ComputadoraDTOEditar(Long id, Integer numeroMaquina, String direccionIp, Boolean estatus, LaboratorioDTO laboratorioDTO, CarreraDTO carreraDTO, String tipo) {
        this.id = id;
        this.numeroMaquina = numeroMaquina;
        this.direccionIp = direccionIp;
        this.estatus = estatus;
        this.laboratorioDTO = laboratorioDTO;
        this.carreraDTO = carreraDTO;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "ComputadoraDTOEditar{" + "numeroMaquina=" + numeroMaquina + ", direccionIp=" + direccionIp + ", estatus=" + estatus + ", laboratorioDTO=" + laboratorioDTO + ", carreraDTO=" + carreraDTO + '}';
    }
    
    
}
