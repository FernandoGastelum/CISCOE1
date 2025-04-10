package ModuloAdministracion.Interfaz;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOEditar;
import DTOs.CarreraDTOGuardar;
import DTOs.CarreraTablaDTO;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface ICarreraNegocio {
    CarreraDTO guardar(CarreraDTOGuardar carrera) throws NegocioException;
    
    List<CarreraDTO> obtener() throws NegocioException;
    
    List<CarreraTablaDTO> obtenerTabla() throws NegocioException;
    
    CarreraDTO obtenerPorID(Long id) throws NegocioException;
    
    CarreraDTO editar(Long id, CarreraDTOEditar carrera) throws NegocioException;
    
    void eliminar(Long id) throws NegocioException;
}
