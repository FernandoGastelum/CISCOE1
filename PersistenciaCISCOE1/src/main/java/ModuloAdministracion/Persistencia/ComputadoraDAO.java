/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import Entidades.Computadora;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
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
public class ComputadoraDAO implements IComputadoraDAO{
    private IEntityManager em;
    public ComputadoraDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Computadora guardar(ComputadoraDTOGuardar computadora) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Computadora computadoraEntidad = new Computadora(computadora.getNumeroMaquina(), computadora.getDireccionIp(), computadora.getLaboratorio(), computadora.getCarrera());
        
        entity.persist(computadoraEntidad);
        entity.getTransaction().commit();
        return computadoraEntidad;
    }

    @Override
    public Computadora obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Computadora computadora = entity.find(Computadora.class, id);
        if(computadora!=null){
            return computadora;
        }else{
            throw new PersistenciaException("No se encontro un computadora con el id "+id);
        }
    }

    @Override
    public List<Computadora> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Computadora> query = entity.createQuery("""
                                                         SELECT c
                                                         FROM Computadora c
                                                         """, Computadora.class);
        if(query.getResultList()==null){
            throw new PersistenciaException("No se encontraron resultados");
        }
        return query.getResultList();
    }

    @Override
    public ComputadoraDTO obtenerDTO(Long id)throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<ComputadoraDTO> cq = cb.createQuery(ComputadoraDTO.class);
        Root<Computadora> computadora = cq.from(Computadora.class);
        cq.select(cb.construct(ComputadoraDTO.class,
                computadora.get("idComputadora"),
                computadora.get("numeroMaquina"),
                computadora.get("direccionIp"),
                computadora.get("estatus"),
                computadora.get("laboratorio"),
                computadora.get("carrera")))
          .where(cb.equal(computadora.get("idComputadora"), id));

        TypedQuery<ComputadoraDTO> query = entity.createQuery(cq);
        if(query.getSingleResult()==null){
            throw new PersistenciaException("No se encontraron resultados");
        }
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
