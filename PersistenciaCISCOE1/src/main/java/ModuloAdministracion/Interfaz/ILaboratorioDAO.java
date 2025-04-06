/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface ILaboratorioDAO {
    
    Laboratorio guardar(LaboratorioDTOGuardar laboratorio) throws PersistenciaException;
    
    Laboratorio obtenerPorID(Long id) throws PersistenciaException;
    
    List<Laboratorio> obtener() throws PersistenciaException;
    
    LaboratorioDTO obtenerDTO(Long id) throws PersistenciaException;
}
