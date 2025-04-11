/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Negocio;

import DTOs.ReservaDTO;
import DTOs.ReservaDTOEditar;
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
    public List<ReservaDTO> obtener() throws NegocioException {
        try {
            
                List<Reserva> listaReserva = this.reservaDAO.obtener();
                if(listaReserva==null){
                    return null;
                }
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
        if(reserva.getComputadoraDTO()==null){
            throw new NegocioException("Error, es obligatorio proporcionar una computadora");
        }
        if(reserva.getEstudianteDTO()==null){
            throw new NegocioException("Error, es obligatorio proporcionar un estudiante");
        }
        
        return true;
    }

    @Override
    public ReservaDTO guardar(ReservaDTOGuardar dto) throws NegocioException {
        try {reglasNegocioGuardar(dto);

            Reserva reservaGuardada = reservaDAO.guardar(dto);
            return reservaDAO.obtenerReservaDTO(reservaGuardada.getIdReserva());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar reserva: " + ex.getMessage());
        }
    }
    @Override
    public ReservaDTO actualizar(ReservaDTOEditar reserva) throws NegocioException{
        try {
            Reserva reservaActualizada = reservaDAO.actualizar(reserva);
            return reservaDAO.obtenerReservaDTO(reservaActualizada.getIdReserva());
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar reserva: " + ex.getMessage());
        }
    }

    
    
}
