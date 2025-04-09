/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import Entidades.Computadora;
import Excepcion.PersistenciaException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IComputadoraDAO {
    
    Computadora guardar(ComputadoraDTOGuardar computadora) throws PersistenciaException;
    
    Computadora obtenerPorID(Long id) throws PersistenciaException;
    
    List<Computadora> obtener() throws PersistenciaException;
    
    ComputadoraDTO obtenerDTO(Long id)throws PersistenciaException;
    
    boolean existeComputadoraRepetida(Integer numero, String tipo, Long idLaboratorio) throws PersistenciaException;
}
