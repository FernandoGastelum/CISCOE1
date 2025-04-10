package ModuloAdministracion.Interfaz;

import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOEditar;
import DTOs.LaboratorioDTOGuardar;
import DTOs.LaboratorioTablaDTO;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface ILaboratorioNegocio {
    LaboratorioDTO guardar(LaboratorioDTOGuardar laboratorio) throws NegocioException;
    
    List<LaboratorioDTO> obtener() throws NegocioException;
    
    List<LaboratorioTablaDTO> obtenerTabla() throws NegocioException;
    
    LaboratorioDTO obtenerPorID(Long id) throws NegocioException;
    
    LaboratorioDTO editar(Long id, LaboratorioDTOEditar laboratorio) throws NegocioException;
    
    void eliminar(Long id) throws NegocioException;
}
