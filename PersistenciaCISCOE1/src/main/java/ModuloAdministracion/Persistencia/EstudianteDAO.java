/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import Entidades.Carrera;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
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
public class EstudianteDAO implements IEstudianteDAO {

    private IEntityManager em;

    public EstudianteDAO(IEntityManager em) {
        this.em = em;
    }

    @Override
    public Estudiante guardar(EstudianteDTOGuardar estudiante) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        Estudiante estudianteEntidad = this.convertirEntidad(estudiante);

        entity.persist(estudianteEntidad);
        entity.getTransaction().commit();
        return estudianteEntidad;
    }
    public Estudiante convertirEntidad(EstudianteDTOGuardar estudiante) throws PersistenciaException{
        ICarreraDAO carreraDAO = new CarreraDAO(em);
        
        Carrera carreraEntidad = carreraDAO.obtenerPorID(estudiante.getCarreraDTO().getIdCarrera());
        Estudiante estudianteEntidad = new Estudiante(estudiante.getIdInstitucional(), estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno(), estudiante.getContrasena(), carreraEntidad);
        return estudianteEntidad;
    }

    @Override
    public Estudiante obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Estudiante alumno = entity.find(Estudiante.class, id);
        if (alumno != null) {
            return alumno;
        } else {
            throw new PersistenciaException("No se encontro un estudiante con el id " + id);
        }
    }

    @Override
    public List<Estudiante> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Estudiante> query = entity.createQuery("""
                                                         SELECT e
                                                         FROM Estudiante e
                                                         """, Estudiante.class);
        if(query.getResultList()==null){
            throw new PersistenciaException("No se encontraron resultados");
        }
        return query.getResultList();
    }

    @Override
    public EstudianteDTO obtenerDTO(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<EstudianteDTO> cq = cb.createQuery(EstudianteDTO.class);
        Root<Estudiante> estudiante = cq.from(Estudiante.class);
        cq.select(cb.construct(EstudianteDTO.class,
                estudiante.get("idEstudiante"),
                estudiante.get("idInstitucional"),
                estudiante.get("nombre"),
                estudiante.get("apellidoPaterno"),
                estudiante.get("apellidoMaterno"),
                estudiante.get("estatusInscripcion"),
                estudiante.get("contrasena"),
                estudiante.get("carrera")))
          .where(cb.equal(estudiante.get("idEstudiante"), id));

        TypedQuery<EstudianteDTO> query = entity.createQuery(cq);
        if(query.getSingleResult()==null){
            throw new PersistenciaException("No se encontraron resultados");
        }
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Estudiante obtenerPorIdInstitucional(String id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Estudiante> query = entity.createQuery("""
                                                             SELECT e 
                                                             FROM Estudiante e 
                                                             WHERE e.idInstitucional = :id
                                                             """,Estudiante.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
