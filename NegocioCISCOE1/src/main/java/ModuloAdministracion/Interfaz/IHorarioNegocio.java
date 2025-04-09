package ModuloAdministracion.Interfaz;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public interface IHorarioNegocio {
    HorarioDTO guardar(HorarioDTOGuardar horario) throws NegocioException;
    
    List<HorarioDTO> obtener() throws NegocioException;
    
    HorarioDTO obtenerPorID(Long id) throws NegocioException;
    
    HorarioDTO obtenerHorarioDelDia(Long idLaboratorio) throws NegocioException;
}
