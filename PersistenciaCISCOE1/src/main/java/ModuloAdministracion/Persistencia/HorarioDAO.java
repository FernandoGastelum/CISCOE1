/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import DTOs.ReporteTablaDTO;
import Entidades.Horario;
import Entidades.Laboratorio;
import Entidades.Reserva;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
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
    public List<ReporteTablaDTO> obtenerReporte(Calendar fechaInicio, Calendar fechaFin)throws PersistenceException{
        EntityManager entity = em.crearEntityManager();
        List<ReporteTablaDTO> resultados = new ArrayList<>();

        try {
            String sql = "SELECT l.nombre AS laboratorio, h.fecha, " +
            "TIMESTAMPDIFF(MINUTE, h.hora_apertura, h.hora_cierre) AS tiempo_servicio, " +
            "IFNULL(SUM(r.minutos), 0) AS tiempo_uso, " +
            "TIMESTAMPDIFF(MINUTE, h.hora_apertura, h.hora_cierre) - IFNULL(SUM(r.minutos), 0) AS tiempo_sin_uso " +
            "FROM horarios h " +
            "JOIN laboratorios l ON h.id_laboratorio = l.id_laboratorio " +
            "LEFT JOIN reservas r ON h.id_horario = r.id_horario " +
            "WHERE h.fecha BETWEEN ? AND ? " +
            "GROUP BY l.nombre, h.fecha, h.hora_apertura, h.hora_cierre " +
            "ORDER BY l.nombre, h.fecha";

        Query query = entity.createNativeQuery(sql);
        query.setParameter(1, fechaInicio.getTime());  
        query.setParameter(2, fechaFin.getTime());

            List<Object[]> rows = query.getResultList();

            for (Object[] row : rows) {
                String nombreLaboratorio = (String) row[0];
                Date fechaSQL = (Date)row[1];
                Calendar fecha = Calendar.getInstance();
                fecha.setTime(fechaSQL);
                int tiempoServicio = ((Number) row[2]).intValue();
                int tiempoUso = ((Number) row[3]).intValue();
                int tiempoSinUso = ((Number) row[4]).intValue();

                ReporteTablaDTO dto = new ReporteTablaDTO(
                    nombreLaboratorio, fecha, tiempoServicio, tiempoUso, tiempoSinUso
                );

                resultados.add(dto);
            }

        } finally {
            entity.close();
        }

        return resultados;
    
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
