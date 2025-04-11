/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Persistencia;

import DTOs.ComputadoraDTO;
import DTOs.ReservaDTO;
import DTOs.ReservaDTOEditar;
import DTOs.ReservaDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Estudiante;
import Entidades.Horario;
import Entidades.Reserva;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public Reserva guardar(ReservaDTOGuardar reserva) throws PersistenciaException {
        if(reserva == null){
            throw new PersistenciaException("La reserva esta vacia");
        }
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Reserva reservaEntidad = this.convertirEntidad(reserva);
        
        entity.persist(reservaEntidad);
        entity.getTransaction().commit();
        return reservaEntidad;
    }
    public Reserva convertirEntidad(ReservaDTOGuardar reservaDTO) throws PersistenciaException{
        try {
            IComputadoraDAO computadoraDAO = new ComputadoraDAO(em);
            Computadora computadora = computadoraDAO.obtenerPorID(reservaDTO.getComputadoraDTO().getIdComputadora());
            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);
            Estudiante estudiante = estudianteDAO.obtenerPorID(reservaDTO.getEstudianteDTO().getIdEstudiante());
            IHorarioDAO horarioDAO = new HorarioDAO(em);
            Horario horario = horarioDAO.obtenerPorID(reservaDTO.getHorario().getIdHorario());
            Reserva reserva = new Reserva(reservaDTO.getHoraInicio(), reservaDTO.getMinutos(),computadora, estudiante, horario);
            return reserva;
        } catch (PersistenciaException ex) {
            throw new PersistenciaException("Error "+ex.getMessage());
        }
    }

    @Override
    public List<Reserva> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Reserva> query = entity.createQuery("""
                                                         SELECT r
                                                         FROM Reserva r
                                                         """, Reserva.class);
        List<Reserva> resultado = query.getResultList();
        if(resultado.isEmpty()){
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
    public Reserva actualizar(ReservaDTOEditar reserva) throws PersistenciaException{
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Reserva reservaActual = this.obtenerPorID(reserva.getId());
        
        reservaActual.setHoraFin(reserva.getHoraFin());
        reservaActual.setComputadora(reserva.getComputadora());
        reservaActual.setEstudiante(reserva.getEstudiante());
        reservaActual.setHoraInicio(reserva.getHoraInicio());
        reservaActual.setHorario(reserva.getHorario());
        reservaActual.setMinutos(reserva.getMinutos());
        
        entity.merge(reservaActual);
        entity.getTransaction().commit();
        return reservaActual;
    }

    @Override
    public ReservaDTO obtenerReservaDTO(Long id) {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<ReservaDTO> cq = cb.createQuery(ReservaDTO.class);
        Root<Reserva> reserva = cq.from(Reserva.class);
        cq.select(cb.construct(ReservaDTO.class,
                reserva.get("idReserva"),
                reserva.get("horaInicio"),
                reserva.get("horaFin"),
                reserva.get("minutos"),
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
