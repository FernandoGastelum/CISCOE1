package ModuloAdministracion.Interfaz;

import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface IInstitutoNegocio {
    InstitutoDTO guardar(InstitutoDTOGuardar instituto) throws NegocioException;
    
    List<InstitutoDTO> obtener() throws NegocioException;
    
    InstitutoDTO obtenerPorID(Long id) throws NegocioException;
}
