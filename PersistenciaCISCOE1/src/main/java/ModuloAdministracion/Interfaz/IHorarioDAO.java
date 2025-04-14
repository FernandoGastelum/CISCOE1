/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import DTOs.ReporteTablaDTO;
import Entidades.Horario;
import Excepcion.PersistenciaException;
import java.util.Calendar;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author gaspa
 */
public interface IHorarioDAO {
    
    Horario guardar(HorarioDTOGuardar horario) throws PersistenciaException;
    
    Horario obtenerPorID(Long id) throws PersistenciaException;
    
    List<Horario> obtener() throws PersistenciaException;
    
    HorarioDTO obtenerDTO(Long id) throws PersistenciaException;
    
    Horario obtenerHorarioDelDia(Long idLaboratorio) throws PersistenciaException;
    
    List<ReporteTablaDTO> obtenerReporte(Calendar fechaInicio, Calendar fechaFin)throws PersistenceException;
}
