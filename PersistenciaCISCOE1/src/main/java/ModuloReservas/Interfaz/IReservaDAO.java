/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloReservas.Interfaz;

import DTOs.BloqueoDTOGuardar;
import DTOs.ReservaDTOGuardar;
import Entidades.Bloqueo;
import Entidades.Reserva;
import Excepcion.PersistenciaException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IReservaDAO {
    Reserva guardar(ReservaDTOGuardar reserva) throws PersistenciaException;
    List<Reserva> obtener() throws PersistenciaException;
    Reserva obtenerPorID(Long id) throws PersistenciaException;
}
