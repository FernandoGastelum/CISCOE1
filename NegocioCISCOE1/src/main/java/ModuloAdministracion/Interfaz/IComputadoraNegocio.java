/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IComputadoraNegocio {
    ComputadoraDTO guardar(ComputadoraDTOGuardar computadora) throws NegocioException;
    
    List<ComputadoraDTO> obtener() throws NegocioException;
    
    ComputadoraDTO obtenerPorID(Long id) throws NegocioException;
}
