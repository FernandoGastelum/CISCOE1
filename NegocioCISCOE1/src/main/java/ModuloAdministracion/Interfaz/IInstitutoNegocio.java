package ModuloAdministracion.Interfaz;

import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import DTOs.InstitutoTablaDTO;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface IInstitutoNegocio {
    InstitutoDTO guardar(InstitutoDTOGuardar instituto) throws NegocioException;
    
    List<InstitutoDTO> obtener() throws NegocioException;
    
    List<InstitutoTablaDTO> obtenerTabla() throws NegocioException;
    
    InstitutoDTO obtenerPorID(Long id) throws NegocioException;
}
