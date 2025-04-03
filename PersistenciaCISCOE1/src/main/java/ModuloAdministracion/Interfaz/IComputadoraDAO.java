/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.CarreraDTOGuardar;
import DTOs.ComputadoraDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Excepcion.PersistenciaException;

/**
 *
 * @author gaspa
 */
public interface IComputadoraDAO {
    Computadora guardar(ComputadoraDTOGuardar computadora) throws PersistenciaException;
    Computadora obtenerPorID(Long id) throws PersistenciaException;
}
