/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Negocio;

import DTOs.ReservaDTO;
import DTOs.ReservaDTOGuardar;
import Entidades.Reserva;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
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
    public ReservaNegocio(IReservaDAO reservaDAO){
        this.reservaDAO = reservaDAO;
    }
    @Override
    public ReservaDTO guardar(ReservaDTOGuardar reserva) throws NegocioException {
        try {this.reglasNegocioGuardar(reserva);
                Reserva reservaGuardado = this.reservaDAO.guardar(reserva);
                return this.reservaDAO.obtenerReservaDTO(reservaGuardado.getIdReserva());
            } catch (PersistenciaException ex) {
                throw new NegocioException("Error "+ex.getMessage());
            }
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
    public ReservaDTO obtenerPorID() throws NegocioException {
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
    
}
