/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Ilian Gastelum
 */
@Entity
@Table(name = "institutos")
public class Instituto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instituto")
    private Long idInstituto;

    @Column(name = "nombre_oficial", nullable = false, length = 100)
    private String nombreOficial;

    @Column(name = "nombre_abreviado", nullable = false, length = 30)
    private String nombreAbreviado;
    
    @OneToMany(mappedBy = "instituto")
    private List<Laboratorio> laboratorios;

    public Instituto() {
    }

    public Instituto(String nombreOficial, String nombreAbreviado) {
        this.nombreOficial = nombreOficial;
        this.nombreAbreviado = nombreAbreviado;
    }

    public Long getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(Long idInstituto) {
        this.idInstituto = idInstituto;
    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(List<Laboratorio> laboratorios) {
        this.laboratorios = laboratorios;
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
        return "Instituto{" +
                "idInstituto=" + idInstituto +
                ", nombreOficial='" + nombreOficial + '\'' +
                ", nombreAbreviado='" + nombreAbreviado + '\'' +
                '}';
    }
}