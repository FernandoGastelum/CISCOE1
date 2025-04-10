/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import Entidades.Horario;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gaspa
 */
public class HorarioDAO implements IHorarioDAO {

    private IEntityManager em;

    public HorarioDAO(IEntityManager em) {
        this.em = em;
    }

    @Override
    public Horario guardar(HorarioDTOGuardar horario) throws PersistenciaException {
        if(horario == null){
            throw new PersistenciaException("El horario esta vacio");
        }
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        Horario horarioEntidad = this.convertirEntidad(horario);

        entity.persist(horarioEntidad);
        entity.getTransaction().commit();
        return horarioEntidad;
    }

    private Horario convertirEntidad(HorarioDTOGuardar horario) throws PersistenciaException {
        ILaboratorioDAO laboratorioDAO = new LaboratorioDAO(em);

        Laboratorio laboratorioEntidad = laboratorioDAO.obtenerPorID(horario.getLaboratorioDTO().getIdLaboratorio());
        Horario horarioEntidad = new Horario(horario.getHoraApertura(), horario.getHoraCierre(), horario.getFecha(), laboratorioEntidad);
        return horarioEntidad;
    }

    @Override
    public Horario obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Horario horario = entity.find(Horario.class, id);
        if (horario != null) {
            return horario;
        } else {
            throw new PersistenciaException("No se encontro un horario con el id " + id);
        }
    }

    @Override
    public List<Horario> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Horario> query = entity.createQuery("""
                                                         SELECT h
                                                         FROM Horario h
                                                         """, Horario.class);
        List<Horario> resultado = query.getResultList();
        if (resultado.isEmpty()) {
            throw new PersistenciaException("No se encontraron resultados");
        }
        return query.getResultList();
    }

    @Override
    public HorarioDTO obtenerDTO(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<HorarioDTO> cq = cb.createQuery(HorarioDTO.class);
        Root<Horario> horario = cq.from(Horario.class);
        cq.select(cb.construct(HorarioDTO.class,
                horario.get("idHorario"),
                horario.get("horaApertura"),
                horario.get("horaCierre"),
                horario.get("fecha"),
                horario.get("laboratorio")))
                .where(cb.equal(horario.get("idHorario"), id));

        TypedQuery<HorarioDTO> query = entity.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Horario obtenerHorarioDelDia(Long idLaboratorio) throws PersistenciaException {
        
            EntityManager entity = em.crearEntityManager();

            TypedQuery<Horario> query = entity.createQuery(
                "SELECT h FROM Horario h " +
                "WHERE h.laboratorio.idLaboratorio = :idLab " +
                "AND h.fecha = :fechaHoy",
                Horario.class
            );
            query.setParameter("idLab", idLaboratorio);
            query.setParameter("fechaHoy", Calendar.getInstance());

            List<Horario> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                return resultados.get(0); 
            } else {
                return null;
            }
    }

    public Calendar getFecha() {
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.MILLISECOND, 0);
        return hoy;
    }

}
