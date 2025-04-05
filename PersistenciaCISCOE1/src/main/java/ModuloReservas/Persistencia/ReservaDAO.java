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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
        
        Reserva reservaEntidad = new Reserva(reserva.getFechaReserva(), reserva.getHoraInicio(), reserva.getComputadora(), reserva.getEstudiante(), reserva.getHorario());
        
        entity.persist(reservaEntidad);
        entity.getTransaction().commit();
        return reservaEntidad;
    }

    @Override
    public List<Reserva> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Reserva> query = entity.createQuery("""
                                                         SELECT r
                                                         FROM Reserva r
                                                         """, Reserva.class);
        if(query.getResultList()==null){
            throw new PersistenciaException("No se encontraron resultados");
        }
        return query.getResultList();
    }

    @Override
    public Reserva obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Reserva reserva = entity.find(Reserva.class, id);
        if(reserva!=null){
            return reserva;
        }else{
            throw new PersistenciaException("No se encontro una reserva con el id "+id);
        }
    }
    
}
