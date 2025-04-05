/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import Entidades.Carrera;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
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
public class CarreraDAO implements ICarreraDAO{
    private IEntityManager em;
    
    public CarreraDAO(IEntityManager em){
        this.em = em;
    }
    
    @Override
    public Carrera guardar(CarreraDTOGuardar carrera) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Carrera carreraEntidad = new Carrera(carrera.getNombreCarrera(), carrera.getTiempoMaximoDiario(), carrera.getColor());
        
        entity.persist(carreraEntidad);
        entity.getTransaction().commit();
        return carreraEntidad;
    }

    @Override
    public Carrera obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Carrera carrera = entity.find(Carrera.class, id);
        if(carrera!=null){
            return carrera;
        }else{
            throw new PersistenciaException("No se encontro una carrera con el id "+id);
        }
    }

    @Override
    public List<Carrera> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Carrera> query = entity.createQuery("""
                                                         SELECT c
                                                         FROM Carrera c
                                                         """, Carrera.class);
        if(query.getResultList()==null){
            throw new PersistenciaException("No se encontraron resultados");
        }
        return query.getResultList();
    }

    @Override
    public CarreraDTO obtenerDTO(Long id) {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<CarreraDTO> cq = cb.createQuery(CarreraDTO.class);
        Root<Carrera> carrera = cq.from(Carrera.class);
        cq.select(cb.construct(CarreraDTO.class,
                carrera.get("idCarrera"),
                carrera.get("nombreCarrera"),
                carrera.get("tiempoMaximoDiario"),
                carrera.get("color")))
          .where(cb.equal(carrera.get("idCarrera"), id));

        TypedQuery<CarreraDTO> query = entity.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    
}
