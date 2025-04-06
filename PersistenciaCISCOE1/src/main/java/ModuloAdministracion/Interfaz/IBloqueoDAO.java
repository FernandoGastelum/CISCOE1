/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.BloqueoDTO;
import DTOs.BloqueoDTOGuardar;
import Entidades.Bloqueo;
import Excepcion.PersistenciaException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IBloqueoDAO {
    
    Bloqueo guardar(BloqueoDTOGuardar bloqueo) throws PersistenciaException;
    
    Bloqueo obtenerPorID(Long id) throws PersistenciaException;
    
    List<Bloqueo> obtener() throws PersistenciaException;
    
    BloqueoDTO obtenerDTO(Long id) throws PersistenciaException;
}
