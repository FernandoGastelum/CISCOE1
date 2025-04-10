/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.BloqueoDTO;
import DTOs.BloqueoDTOEditar;
import DTOs.BloqueoDTOGuardar;
import DTOs.BloqueoTablaDTO;
import Entidades.Bloqueo;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
import ModuloAdministracion.Interfaz.IBloqueoNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public class BloqueoNegocio implements IBloqueoNegocio {
    private final IBloqueoDAO bloqueoDAO;

    public BloqueoNegocio(IBloqueoDAO bloqueoDAO) {
        this.bloqueoDAO = bloqueoDAO;
    }

    @Override
    public BloqueoDTO guardar(BloqueoDTOGuardar bloqueo) throws NegocioException {
        try {
            this.reglasNegocioGuardar(bloqueo);
            Bloqueo bloqueoGuardado = this.bloqueoDAO.guardar(bloqueo);
            return this.bloqueoDAO.obtenerDTO(bloqueoGuardado.getIdBloqueo());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<BloqueoDTO> obtener() throws NegocioException {
        try {
            List<Bloqueo> listaBloqueos = this.bloqueoDAO.obtener();
            List<BloqueoDTO> dtos = new ArrayList<>();
            for (Bloqueo bloqueo : listaBloqueos) {
                dtos.add(this.bloqueoDAO.obtenerDTO(bloqueo.getIdBloqueo()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    @Override
    public List<BloqueoTablaDTO> obtenerTabla() throws NegocioException {
        try {
            List<Bloqueo> listaBloqueos = this.bloqueoDAO.obtener();
            List<BloqueoDTO> dtos = new ArrayList<>();
            for (Bloqueo bloqueo : listaBloqueos) {
                dtos.add(this.bloqueoDAO.obtenerDTO(bloqueo.getIdBloqueo()));
            }
            return this.convertirTablaDTO(dtos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private List<BloqueoTablaDTO> convertirTablaDTO(List<BloqueoDTO> bloqueos) {
        if (bloqueos == null) {
            return null;
        }
        List<BloqueoTablaDTO> bloqueosDTO = new ArrayList<>();
        for (BloqueoDTO bloqueo : bloqueos) {
            BloqueoTablaDTO dato = new BloqueoTablaDTO(bloqueo.getIdBloqueo(), 
                    bloqueo.getFechaBloqueo(), bloqueo.getMotivo(), 
                    bloqueo.getEstudiante().getIdInstitucional(), 
                    bloqueo.getFechaLiberacion());
            bloqueosDTO.add(dato);
        }
        return bloqueosDTO;
    }

    @Override
    public BloqueoDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return bloqueoDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    private boolean reglasNegocioGuardar(BloqueoDTOGuardar bloqueo) throws NegocioException {
        if (bloqueo.getFechaBloqueo() == null) {
            throw new NegocioException("La fecha no puede estar vacía");
        }
        if (bloqueo.getMotivo() == null) {
            throw new NegocioException("El motivo no puede estar vacía");
        }
        return true;
    }

    @Override
    public BloqueoDTO editar(BloqueoDTOEditar bloqueo) throws NegocioException {
        try {
            Bloqueo bloqueoEntidad = bloqueoDAO.editar(bloqueo);
            return bloqueoDAO.obtenerDTO(bloqueoEntidad.getIdBloqueo());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar el bloqueo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long idBloqueo) throws NegocioException {
        try {
            bloqueoDAO.eliminar(idBloqueo);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar el bloqueo: " + e.getMessage());
        }
    }
}
