package ModuloAdministracion.Interfaz;

import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface ILaboratorioNegocio {
    LaboratorioDTO guardar(LaboratorioDTOGuardar laboratorio) throws NegocioException;
    
    List<LaboratorioDTO> obtener() throws NegocioException;
    
    LaboratorioDTO obtenerPorID(Long id) throws NegocioException;
}
