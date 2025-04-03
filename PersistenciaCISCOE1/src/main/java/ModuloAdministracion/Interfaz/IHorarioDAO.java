/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.ComputadoraDTOGuardar;
import DTOs.HorarioDTOGuardar;
import Entidades.Computadora;
import Entidades.Horario;
import Excepcion.PersistenciaException;

/**
 *
 * @author gaspa
 */
public interface IHorarioDAO {
    Horario guardar(HorarioDTOGuardar horario) throws PersistenciaException;
    Horario obtenerPorID(Long id) throws PersistenciaException;
}
