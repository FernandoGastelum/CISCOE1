/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Persistencia;

import DTOs.ComputadoraDTO;
import DTOs.ReservaDTO;
import DTOs.ReservaDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Reserva;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloReservas.Interfaz.IReservaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    public Reserva guardar(Reserva reserva) throws PersistenciaException {
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

    @Override
    public ReservaDTO obtenerReservaDTO(Long id) {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<ReservaDTO> cq = cb.createQuery(ReservaDTO.class);
        Root<Reserva> reserva = cq.from(Reserva.class);
        cq.select(cb.construct(ReservaDTO.class,
                reserva.get("idReserva"),
                reserva.get("fechaReserva"),
                reserva.get("horaInicio"),
                reserva.get("horaFin"),
                reserva.get("computadora"),
                reserva.get("estudiante"),
                reserva.get("horario")))
          .where(cb.equal(reserva.get("idReserva"), id));

        TypedQuery<ReservaDTO> query = entity.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    
    }
    
}
