/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOEditar;
import DTOs.InstitutoDTOGuardar;
import Entidades.Instituto;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
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
public class InstitutoDAO implements IInstitutoDAO{
    private IEntityManager em;
    public InstitutoDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Instituto guardar(InstitutoDTOGuardar instituto) throws PersistenciaException {
        if (instituto == null) {
            throw new PersistenciaException("El instituto no puede ser null");
        }

        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        Instituto institutoEntidad = new Instituto(
            instituto.getNombreOficial(),
            instituto.getNombreAbreviado()
        );

        entity.persist(institutoEntidad);
        entity.getTransaction().commit();
        return institutoEntidad;
    }

    @Override
    public Instituto obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Instituto instituto = entity.find(Instituto.class, id);
        if(instituto!=null){
            return instituto;
        }else{
            throw new PersistenciaException("No se encontro un instituto con el id "+id);
        }
    }

    @Override
    public List<Instituto> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Instituto> query = entity.createQuery("""
                                                         SELECT i
                                                         FROM Instituto i
                                                         """, Instituto.class);
        List<Instituto> resultados = query.getResultList();
        if(resultados.isEmpty()){
            throw new PersistenciaException("No se encontraron resultados");
        }
        return resultados;
    }

    @Override
    public InstitutoDTO obtenerDTO(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<InstitutoDTO> cq = cb.createQuery(InstitutoDTO.class);
        Root<Instituto> instituto = cq.from(Instituto.class);
        cq.select(cb.construct(InstitutoDTO.class,
                instituto.get("idInstituto"),
                instituto.get("nombreOficial"),
                instituto.get("nombreAbreviado")))
          .where(cb.equal(instituto.get("idInstituto"), id));

        TypedQuery<InstitutoDTO> query = entity.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Instituto editar(InstitutoDTOEditar instituto) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        try {
            entity.getTransaction().begin();

            Instituto institutoExistente = this.obtenerPorID(instituto.getId());

            if (institutoExistente == null) {
                throw new PersistenciaException("El instituto con ID " + instituto.getId() + " no existe.");
            }
            institutoExistente.setNombreAbreviado(instituto.getNombreAbreviado());
            institutoExistente.setNombreOficial(instituto.getNombreOficial());

            entity.merge(institutoExistente);

            entity.getTransaction().commit();

            return institutoExistente;
        } catch (PersistenciaException e) {
            entity.getTransaction().rollback();
            throw new PersistenciaException("Error al editar el instituto: " + e.getMessage());
        } finally {
            entity.close();
        }
    }

    @Override
    public void eliminar(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        try {
            entity.getTransaction().begin();
            Instituto instituto = entity.find(Instituto.class, id);
            if (instituto != null) {
                entity.remove(instituto);
            } else {
                throw new PersistenciaException("instituto no encontrado con ID: " + id);
            }
            entity.getTransaction().commit();
        } catch (PersistenciaException e) {
            entity.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar el instituto: " + e.getMessage());
        } finally {
            entity.close();
        }
    }
}
