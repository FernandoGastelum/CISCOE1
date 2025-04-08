/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import Entidades.Horario;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import java.util.Calendar;
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
public class HorarioDAO implements IHorarioDAO{
    private IEntityManager em;
    public HorarioDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Horario guardar(HorarioDTOGuardar horario) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Horario horarioEntidad = new Horario(horario.getHoraApertura(), horario.getHoraCierre(), horario.getFecha(), horario.getLaboratorio());
        
        entity.persist(horarioEntidad);
        entity.getTransaction().commit();
        return horarioEntidad;
    }

    @Override
    public Horario obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Horario horario = entity.find(Horario.class, id);
        if(horario!=null){
            return horario;
        }else{
            throw new PersistenciaException("No se encontro un horario con el id "+id);
        }
    }

    @Override
    public List<Horario> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Horario> query = entity.createQuery("""
                                                         SELECT h
                                                         FROM Horario h
                                                         """, Horario.class);
        if(query.getResultList()==null){
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
    public Horario obtenerUltimoHorarioActivoPorLaboratorio(Long idLaboratorio) throws PersistenciaException{
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Horario> query = entity.createQuery("""
                                                       SELECT h 
                                                       FROM Horario h 
                                                       WHERE h.laboratorio.idLaboratorio = :id 
                                                       AND h.fecha <= :hoy 
                                                       ORDER BY h.fecha DESC
                                                             """,Horario.class);
        query.setParameter("id", idLaboratorio);
        query.setParameter("hoy", this.getFecha());
        List<Horario> resultados = query.getResultList();
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        }
        else{
            throw new PersistenciaException("No se encontraron horarios con el id de laboratorio: "+idLaboratorio);
        }
    }
    public Calendar getFecha(){
        Calendar hoy = Calendar.getInstance();
        // Limpiar hora, minutos, segundos para comparar solo fechas
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.MILLISECOND, 0);
        return hoy;
    }
    
}
