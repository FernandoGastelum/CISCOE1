/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.ComputadoraDTOGuardar;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Computadora;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;

/**
 *
 * @author gaspa
 */
public interface ILaboratorioDAO {
    Laboratorio guardar(LaboratorioDTOGuardar laboratorio) throws PersistenciaException;
    Laboratorio obtenerPorID(Long id) throws PersistenciaException;
}
