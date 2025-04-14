package ModuloAdministracion.Interfaz;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import DTOs.ReporteTablaDTO;
import Excepcion.NegocioException;
import java.util.Calendar;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author Knocmare
 */
public interface IHorarioNegocio {
    HorarioDTO guardar(HorarioDTOGuardar horario) throws NegocioException;
    
    List<HorarioDTO> obtener() throws NegocioException;
    
    HorarioDTO obtenerPorID(Long id) throws NegocioException;
    
    HorarioDTO obtenerHorarioDelDia(Long idLaboratorio) throws NegocioException;
    
    List<ReporteTablaDTO> obtenerReporte(Calendar fechaInicio, Calendar fechaFin)throws NegocioException;
}
