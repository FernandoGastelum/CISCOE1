package ModuloAdministracion.Interfaz;

import DTOs.BloqueoDTO;
import DTOs.BloqueoDTOGuardar;
import DTOs.BloqueoTablaDTO;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface IBloqueoNegocio {
    BloqueoDTO guardar(BloqueoDTOGuardar bloqueo) throws NegocioException;
    
    List<BloqueoDTO> obtener() throws NegocioException;
    
    List<BloqueoTablaDTO> obtenerTabla() throws NegocioException;
    
    BloqueoDTO obtenerPorID(Long id) throws NegocioException;
}
