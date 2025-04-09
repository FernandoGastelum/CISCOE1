/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOEditar;
import DTOs.EstudianteDTOGuardar;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IEstudianteDAO {
    
    Estudiante guardar(EstudianteDTOGuardar alumno) throws PersistenciaException;
    
    Estudiante obtenerPorID(Long id) throws PersistenciaException;
    
    List<Estudiante> obtener() throws PersistenciaException;
    
    EstudianteDTO obtenerDTO(Long id)throws PersistenciaException;
    
    Estudiante obtenerPorIdInstitucional(String id)throws PersistenciaException;
    
    Estudiante editar(Long id, EstudianteDTOEditar estudianteDTO) throws PersistenciaException;
    
    void eliminar(Long id) throws PersistenciaException;
}
