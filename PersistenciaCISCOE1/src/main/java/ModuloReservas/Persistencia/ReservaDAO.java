/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Persistencia;

import DTOs.ReservaDTOGuardar;
import Entidades.Reserva;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloReservas.Interfaz.IReservaDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author gaspa
 */
public class ReservaDAO implements IReservaDAO{
    private IEntityManager em;
    public ReservaDAO(IEntityManager em){
        this.em = em;
    }
    @Override
    public Reserva guardar(ReservaDTOGuardar reserva) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Reserva reservaEntidad = new Reserva(reserva.getFechaReserva(), reserva.getHoraInicio(), reserva.getHoraFin(), reserva.getComputadora(), reserva.getEstudiante(), reserva.getHorario());
        
        entity.persist(reservaEntidad);
        entity.getTransaction().commit();
        return reservaEntidad;
    }
    
}
