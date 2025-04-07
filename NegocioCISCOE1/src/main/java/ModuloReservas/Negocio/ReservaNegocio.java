/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Negocio;

import DTOs.ReservaDTO;
import DTOs.ReservaDTOGuardar;
import Entidades.Computadora;
import Entidades.Estudiante;
import Entidades.Horario;
import Entidades.Reserva;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Interfaz.IReservaNegocio;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gaspa
 */
public class ReservaNegocio implements IReservaNegocio{
    private final IReservaDAO reservaDAO;
    private IEstudianteNegocio estudianteNegocio;
    public ReservaNegocio(IReservaDAO reservaDAO, IEstudianteNegocio estudianteNegocio){
        this.reservaDAO = reservaDAO;
        this.estudianteNegocio = estudianteNegocio;
    }
    

    @Override
    public List<ReservaDTO> obtener() throws NegocioException {
        try {
                List<Reserva> listaReserva = this.reservaDAO.obtener();
                return listaReserva.stream().map(reserva -> this.reservaDAO.obtenerReservaDTO(reserva.getIdReserva())).collect(Collectors.toList());
            } catch (PersistenciaException ex) {
                throw new NegocioException("Error "+ex.getMessage());
            }
    }

    @Override
    public ReservaDTO obtenerPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean reglasNegocioGuardar(ReservaDTOGuardar reserva) throws NegocioException {
        if(reserva.getComputadora()==null){
            throw new NegocioException("Error, es obligatorio proporcionar una computadora");
        }
        if(reserva.getEstudiante()==null){
            throw new NegocioException("Error, es obligatorio proporcionar un estudiante");
        }
        if(reserva.getHorario()==null){
            throw new NegocioException("Error, es obligatorio proporcionar un horario");
        }
        return true;
    }

    @Override
    public ReservaDTO guardar(ReservaDTOGuardar dto) throws NegocioException {
        try {reglasNegocioGuardar(dto);

            Computadora computadora = new Computadora();
            computadora.setIdComputadora(dto.getComputadora());

            Estudiante estudiante = new Estudiante();
            Long idEstudiante = estudianteNegocio.obtenerPorIdInstitucional(dto.getEstudiante()).getIdEstudiante();
            estudiante.setIdEstudiante(idEstudiante);

            Horario horario = new Horario();
            horario.setIdHorario(dto.getHorario());

            Reserva reserva = new Reserva();
            reserva.setFechaReserva(dto.getFechaReserva());
            reserva.setHoraInicio(dto.getHoraInicio());
            reserva.setComputadora(computadora);
            reserva.setEstudiante(estudiante);
            reserva.setHorario(horario);

            Reserva reservaGuardada = reservaDAO.guardar(reserva);
            return reservaDAO.obtenerReservaDTO(reservaGuardada.getIdReserva());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar reserva: " + ex.getMessage());
        }
    }

    
    
}
