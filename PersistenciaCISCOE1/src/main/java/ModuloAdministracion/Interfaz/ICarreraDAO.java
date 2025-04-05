/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import Entidades.Carrera;
import Excepcion.PersistenciaException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface ICarreraDAO {
    
    Carrera guardar(CarreraDTOGuardar carrera) throws PersistenciaException;
    
    Carrera obtenerPorID(Long id) throws PersistenciaException;
    
    List<Carrera> obtener() throws PersistenciaException;
    
    CarreraDTO obtenerDTO(Long id);
    
}
