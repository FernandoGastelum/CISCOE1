package ModuloAdministracion.Interfaz;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface ICarreraNegocio {
    CarreraDTO guardar(CarreraDTOGuardar carrera) throws NegocioException;
    
    List<CarreraDTO> obtener() throws NegocioException;
    
    CarreraDTO obtenerPorID(Long id) throws NegocioException;
}
