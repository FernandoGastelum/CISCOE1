/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.BloqueoDTOGuardar;
import Entidades.Bloqueo;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;

/**
 *
 * @author gaspa
 */
public interface IBloqueoDAO {
    Bloqueo guardar(BloqueoDTOGuardar bloqueo) throws PersistenciaException;
    Bloqueo obtenerPorID(Long id) throws PersistenciaException;
}
