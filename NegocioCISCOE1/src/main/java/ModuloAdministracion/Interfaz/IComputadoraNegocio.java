/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOEditar;
import DTOs.ComputadoraDTOGuardar;
import DTOs.ComputadoraTablaDTO;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IComputadoraNegocio {
    ComputadoraDTO guardar(ComputadoraDTOGuardar computadora) throws NegocioException;
    
    List<ComputadoraDTO> obtener() throws NegocioException;
    
    List<ComputadoraTablaDTO> obtenerTabla() throws NegocioException;
    
    ComputadoraDTO obtenerPorID(Long id) throws NegocioException;
    
    ComputadoraDTO actualizar(ComputadoraDTOEditar computadora) throws NegocioException;
    
    void eliminar(Long id) throws NegocioException;
}
