/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import Entidades.Instituto;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Interfaz.IInstitutoNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public class InstitutoNegocio implements IInstitutoNegocio {
    
    private final IInstitutoDAO institutoDAO;

    public InstitutoNegocio(IInstitutoDAO institutoDAO) {
        this.institutoDAO = institutoDAO;
    }
    
    @Override
    public InstitutoDTO guardar(InstitutoDTOGuardar instituto) throws NegocioException {
        try {
            this.reglasNegocioGuardar(instituto);
            Instituto institutoGuardado = this.institutoDAO.guardar(instituto);
            return this.institutoDAO.obtenerDTO(institutoGuardado.getIdInstituto());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<InstitutoDTO> obtener() throws NegocioException {
        try {
            List<Instituto> listaInstitutos = this.institutoDAO.obtener();
            List<InstitutoDTO> dtos = new ArrayList<>();
            for (Instituto instituto : listaInstitutos) {
                dtos.add(this.institutoDAO.obtenerDTO(instituto.getIdInstituto()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public InstitutoDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return institutoDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    private boolean reglasNegocioGuardar(InstitutoDTOGuardar instituto) throws NegocioException {
        if (instituto.getNombreOficial() == null) {
            throw new NegocioException("El nombre oficial no puede estar vacío");
        }
        if (instituto.getNombreAbreviado() == null) {
            throw new NegocioException("El nombre abreviado no puede estar vacío");
        }
        return true;
    }
    
}
