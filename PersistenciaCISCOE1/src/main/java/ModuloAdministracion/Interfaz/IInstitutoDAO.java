/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.InstitutoDTOGuardar;
import Entidades.Instituto;
import Excepcion.PersistenciaException;

/**
 *
 * @author gaspa
 */
public interface IInstitutoDAO {
    Instituto guardar(InstitutoDTOGuardar instituto) throws PersistenciaException;
    Instituto obtenerPorID(Long id) throws PersistenciaException;
}
