package ModuloAdministracion.Interfaz;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOEditar;
import DTOs.EstudianteDTOGuardar;
import DTOs.EstudianteTablaDTO;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IEstudianteNegocio {
    EstudianteDTO guardar(EstudianteDTOGuardar estudiante) throws NegocioException;
    
    List<EstudianteDTO> obtener() throws NegocioException;
    
    List<EstudianteTablaDTO> obtenerTabla() throws NegocioException;
    
    EstudianteDTO obtenerPorID(Long id) throws NegocioException;
    
    EstudianteDTO obtenerPorIdInstitucional(String id) throws NegocioException;
    
    EstudianteDTO editar(Long id, EstudianteDTOEditar estudiante) throws NegocioException;
    
    void eliminar(Long id) throws NegocioException;
}
