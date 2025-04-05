/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloReservas.Interfaz;

import DTOs.ReservaDTO;
import DTOs.ReservaDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IReservaNegocio {
    ReservaDTO guardar(ReservaDTOGuardar reserva) throws NegocioException;
    
    List<ReservaDTO> obtener() throws NegocioException;
    
    ReservaDTO obtenerPorID() throws NegocioException;
}
