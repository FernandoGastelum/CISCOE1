/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOEditar;
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

    private Estudiante convertirEntidad(EstudianteDTOGuardar estudiante) throws PersistenciaException {
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
    public Estudiante editar(Long id, EstudianteDTOEditar estudianteDTO) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Estudiante estudianteEntidad = entity.find(Estudiante.class, id);
        if (estudianteEntidad == null) {
            throw new PersistenciaException("No se encontró un estudiante con el id " + id);
        }
        
        ICarreraDAO carreraDAO = new CarreraDAO(em);
        Carrera carreraEntidad = carreraDAO.obtenerPorID(estudianteDTO.getCarreraDTO().getIdCarrera());
        
        estudianteEntidad.setNombre(estudianteDTO.getNombre());
        estudianteEntidad.setApellidoPaterno(estudianteDTO.getApellidoPaterno());
        estudianteEntidad.setApellidoMaterno(estudianteDTO.getApellidoMaterno());
        estudianteEntidad.setContrasena(estudianteDTO.getContrasena());
        estudianteEntidad.setEstatusInscripcion(estudianteDTO.getEstatusInscripcion());
        estudianteEntidad.setCarrera(carreraEntidad);
        
        entity.merge(estudianteEntidad);
        entity.getTransaction().commit();
        return estudianteEntidad;
    }
    
    @Override
    public void eliminar(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Estudiante estudianteEntidad = entity.find(Estudiante.class, id);
        if (estudianteEntidad == null) {
            throw new PersistenciaException("No se encontró un estudiante con el id " + id);
        }
        
        entity.remove(estudianteEntidad);
        entity.getTransaction().commit();
    }

    @Override
    public List<Estudiante> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Estudiante> query = entity.createQuery("""
                                                         SELECT e
                                                         FROM Estudiante e
                                                         """, Estudiante.class);
        List<Estudiante> resultado = query.getResultList();
        if (resultado.isEmpty()) {
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
        if (query.getSingleResult() == null) {
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
                                                             """, Estudiante.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
