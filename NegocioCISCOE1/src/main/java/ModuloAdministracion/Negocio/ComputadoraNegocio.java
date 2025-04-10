/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOEditar;
import DTOs.ComputadoraDTOGuardar;
import DTOs.ComputadoraTablaDTO;
import Entidades.Computadora;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaspa
 */
public class ComputadoraNegocio implements IComputadoraNegocio {

    private final IComputadoraDAO computadoraDAO;

    public ComputadoraNegocio(IComputadoraDAO computadoraDAO) {
        this.computadoraDAO = computadoraDAO;
    }

    @Override
    public ComputadoraDTO guardar(ComputadoraDTOGuardar computadora) throws NegocioException {
        try {
            this.reglasNegocioGuardar(computadora);
            Computadora computadoraGuardado = this.computadoraDAO.guardar(computadora);
            return this.computadoraDAO.obtenerDTO(computadoraGuardado.getIdComputadora());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<ComputadoraDTO> obtener() throws NegocioException {
        try {
            List<Computadora> listaComputadoras = this.computadoraDAO.obtener();
            List<ComputadoraDTO> dtos = new ArrayList<>();
            for (Computadora computadora : listaComputadoras) {
                dtos.add(this.computadoraDAO.obtenerDTO(computadora.getIdComputadora()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    @Override
    public List<ComputadoraTablaDTO> obtenerTabla() throws NegocioException {
        try {
            List<Computadora> listaComputadoras = this.computadoraDAO.obtener();
            List<ComputadoraDTO> dtos = new ArrayList<>();
            for (Computadora computadora : listaComputadoras) {
                dtos.add(this.computadoraDAO.obtenerDTO(computadora.getIdComputadora()));
            }
            return this.convertirTablaDTO(dtos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private List<ComputadoraTablaDTO> convertirTablaDTO(List<ComputadoraDTO> computadoras) {
        if (computadoras == null) {
            return null;
        }
        List<ComputadoraTablaDTO> computadorasDTO = new ArrayList<>();
        for (ComputadoraDTO computadora : computadoras) {
            String estatus = computadora.getEstatus() == true ? "Disponible" : "Mantenimiento";
            ComputadoraTablaDTO dato = new ComputadoraTablaDTO(computadora.getIdComputadora(), computadora.getNumeroMaquina(), computadora.getDireccionIp(), estatus,computadora.getTipo());
            computadorasDTO.add(dato);
        }
        return computadorasDTO;
    }

    @Override
    public ComputadoraDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return computadoraDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private boolean reglasNegocioGuardar(ComputadoraDTOGuardar computadora) throws NegocioException {
        try {
            if(computadoraDAO.existeComputadoraRepetida(computadora.getNumeroMaquina(), computadora.getTipo(), computadora.getLaboratorioDTO().getIdLaboratorio())){
                throw new NegocioException("Ya existe una computadora con ese número, tipo y laboratorio.");
            }
        } catch (PersistenciaException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        if (computadora.getCarreraDTO()== null) {
            throw new NegocioException("La carrera no puede estar vacia");
        }
        if (computadora.getDireccionIp() == null) {
            throw new NegocioException("La direccion ip no puede estar vacia");
        }
        if (computadora.getLaboratorioDTO()== null) {
            throw new NegocioException("El laboratorio no puede estar vacio");
        }
        if (computadora.getNumeroMaquina() == null) {
            throw new NegocioException("El numero de maquina no puede estar vacio");
        }
        return true;
    }

    @Override
    public ComputadoraDTO actualizar(ComputadoraDTOEditar computadora) throws NegocioException {
        try {
            Computadora computadoraEntidad = computadoraDAO.actualizar(computadora);
            return computadoraDAO.obtenerDTO(computadoraEntidad.getIdComputadora());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error "+e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) throws NegocioException {
        try {
            computadoraDAO.eliminar(id);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error "+e.getMessage());
        }
    }

}
