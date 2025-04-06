/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import Entidades.Computadora;
import Entidades.Reserva;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gaspa
 */
public class ComputadoraNegocio implements IComputadoraNegocio{
    private final IComputadoraDAO computadoraDAO;
    public ComputadoraNegocio(IComputadoraDAO computadoraDAO){
        this.computadoraDAO = computadoraDAO;
    }
    @Override
    public ComputadoraDTO guardar(ComputadoraDTOGuardar computadora) throws NegocioException {
        try {this.reglasNegocioGuardar(computadora);
                Computadora computadoraGuardado = this.computadoraDAO.guardar(computadora);
                return this.computadoraDAO.obtenerDTO(computadoraGuardado.getIdComputadora());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error "+ex.getMessage());
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
    public ComputadoraDTO obtenerPorID(Long id) throws NegocioException {
        try {
                return computadoraDAO.obtenerDTO(id);
            } catch (PersistenciaException ex) {
                throw new NegocioException("Error "+ex.getMessage());
            }
    }

    private boolean reglasNegocioGuardar(ComputadoraDTOGuardar computadora) throws NegocioException {
        if(computadora.getCarrera()==null){
            throw new NegocioException("La carrera no puede estar vacia");
        }
        if(computadora.getDireccionIp()==null){
            throw new NegocioException("La direccion ip no puede estar vacia");
        }
        if(computadora.getLaboratorio()==null){
            throw new NegocioException("El laboratorio no puede estar vacio");
        }
        if(computadora.getNumeroMaquina()==null){
            throw new NegocioException("El numero de maquina no puede estar vacio");
        }
        return true;
    }
    
}
