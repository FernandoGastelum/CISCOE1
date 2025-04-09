/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import Entidades.Horario;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.IHorarioNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public class HorarioNegocio implements IHorarioNegocio {

    private final IHorarioDAO horarioDAO;

    public HorarioNegocio(IHorarioDAO horarioDAO) {
        this.horarioDAO = horarioDAO;
    }

    @Override
    public HorarioDTO guardar(HorarioDTOGuardar horario) throws NegocioException {
        try {
            this.reglasNegocioGuardar(horario);
            Horario horarioGuardado = this.horarioDAO.guardar(horario);
            return this.horarioDAO.obtenerDTO(horarioGuardado.getIdHorario());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<HorarioDTO> obtener() throws NegocioException {
        try {
            List<Horario> listaHorarios = this.horarioDAO.obtener();
            List<HorarioDTO> dtos = new ArrayList<>();
            for (Horario horario : listaHorarios) {
                dtos.add(this.horarioDAO.obtenerDTO(horario.getIdHorario()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public HorarioDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return horarioDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private boolean reglasNegocioGuardar(HorarioDTOGuardar horario) throws NegocioException {
        if (horario.getFecha() == null) {
            throw new NegocioException("La fecha no puede estar vacía");
        }
        if (horario.getHoraApertura() == null) {
            throw new NegocioException("La hora de apertura no puede estar vacía");
        }
        if (horario.getHoraCierre() == null) {
            throw new NegocioException("La hora de cierre no puede estar vacía");
        }
        if (horario.getLaboratorioDTO() == null) {
            throw new NegocioException("El laboratorio no puede estar vacío");
        }
        return true;
    }

    @Override
    public HorarioDTO obtenerHorarioDelDia(Long idLaboratorio) throws NegocioException {
        try {
            Horario horarioEntidad = horarioDAO.obtenerHorarioDelDia(idLaboratorio);
            if(horarioEntidad!=null){
                return horarioDAO.obtenerDTO(horarioEntidad.getIdHorario());
            }
            //Se devuelve un null para poder proceder a crear un horario nuevo
            return null;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error " + e.getMessage());
        }
    }
}
