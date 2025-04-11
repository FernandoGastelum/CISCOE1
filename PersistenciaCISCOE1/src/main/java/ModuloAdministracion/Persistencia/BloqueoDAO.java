/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.BloqueoDTO;
import DTOs.BloqueoDTOEditar;
import DTOs.BloqueoDTOGuardar;
import Entidades.Bloqueo;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
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
public class BloqueoDAO implements IBloqueoDAO{
    private IEntityManager em;
    public BloqueoDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Bloqueo guardar(BloqueoDTOGuardar bloqueo) throws PersistenciaException {
        if (bloqueo == null) {
            throw new PersistenciaException("El bloqueo no puede ser null");
        }
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Bloqueo bloqueoEntidad = this.convertirEntidad(bloqueo);
        
        entity.persist(bloqueoEntidad);
        entity.getTransaction().commit();
        return bloqueoEntidad;
    }
    
    private Bloqueo convertirEntidad(BloqueoDTOGuardar bloqueo) throws PersistenciaException {
        IEstudianteDAO estudianteDAO = new EstudianteDAO(em);

        Estudiante estudianteEntidad = estudianteDAO.obtenerPorID(bloqueo.getEstudianteDTO().getIdEstudiante());
        Bloqueo bloqueoEntidad = new Bloqueo(bloqueo.getFechaBloqueo(), bloqueo.getMotivo(), estudianteEntidad);
        return bloqueoEntidad;
    }

    @Override
    public Bloqueo obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Bloqueo bloqueo = entity.find(Bloqueo.class, id);
        if(bloqueo!=null){
            return bloqueo;
        }else{
            throw new PersistenciaException("No se encontro un bloqueo con el id "+id);
        }
    }

    @Override
    public List<Bloqueo> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Bloqueo> query = entity.createQuery("""
                                                         SELECT b
                                                         FROM Bloqueo b
                                                         """, Bloqueo.class);
        List<Bloqueo> resultado = query.getResultList();
        if(resultado.isEmpty()){
            return null;
        }
        return query.getResultList();
    }

    @Override
    public BloqueoDTO obtenerDTO(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<BloqueoDTO> cq = cb.createQuery(BloqueoDTO.class);
        Root<Bloqueo> bloqueo = cq.from(Bloqueo.class);
        
        cq.select(cb.construct(BloqueoDTO.class,
                bloqueo.get("idBloqueo"),
                bloqueo.get("fechaBloqueo"),
                bloqueo.get("fechaLiberacion"),
                bloqueo.get("motivo"),
                bloqueo.get("estudiante")))
          .where(cb.equal(bloqueo.get("idBloqueo"), id));

        TypedQuery<BloqueoDTO> query = entity.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Bloqueo editar(BloqueoDTOEditar bloqueoDTO) throws PersistenciaException {
        EntityManager entity = this.em.crearEntityManager();
        try {
            entity.getTransaction().begin();

            Bloqueo bloqueo = entity.find(Bloqueo.class, bloqueoDTO.getIdBloqueo());
            if (bloqueo == null) {
                throw new PersistenciaException("No se encontró el bloqueo con id: " + bloqueoDTO.getIdBloqueo());
            }

                             
            bloqueo.setFechaBloqueo(bloqueoDTO.getFechaBloqueo());
            bloqueo.setFechaLiberacion(bloqueoDTO.getFechaLiberacion());
            bloqueo.setMotivo(bloqueoDTO.getMotivo());

            entity.merge(bloqueo);
            entity.getTransaction().commit();

            return bloqueo;
        } catch (PersistenciaException e) {
            entity.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar el bloqueo: " + e.getMessage());
        } finally {
            entity.close();
        }
    }

    @Override
    public void eliminar(Long idBloqueo) throws PersistenciaException {
        EntityManager em = this.em.crearEntityManager();
            try {
                em.getTransaction().begin();
                Bloqueo bloqueo = em.find(Bloqueo.class, idBloqueo);
                if (bloqueo != null) {
                    em.remove(bloqueo);
                } else {
                    throw new PersistenciaException("No se encontró el bloqueo con id: " + idBloqueo);
                }
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw new PersistenciaException("Error al eliminar el bloqueo: " + e.getMessage());
            } finally {
                em.close();
            }
    }
}
