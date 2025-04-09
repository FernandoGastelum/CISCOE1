/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
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
public class ComputadoraDAO implements IComputadoraDAO{
    private IEntityManager em;
    public ComputadoraDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Computadora guardar(ComputadoraDTOGuardar computadora) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Computadora computadoraEntidad = this.convertirEntidad(computadora);
        
        entity.persist(computadoraEntidad);
        entity.getTransaction().commit();
        return computadoraEntidad;
    }
    
    private Computadora convertirEntidad(ComputadoraDTOGuardar computadora) throws PersistenciaException{
        ILaboratorioDAO laboratorioDAO = new LaboratorioDAO(em);
        ICarreraDAO carreraDAO = new CarreraDAO(em);
        
        Laboratorio laboratorioEntidad = laboratorioDAO.obtenerPorID(computadora.getLaboratorioDTO().getIdLaboratorio());
        Carrera carreraEntidad = carreraDAO.obtenerPorID(computadora.getCarreraDTO().getIdCarrera());
        Computadora computadoraEntidad = new Computadora(computadora.getNumeroMaquina(), computadora.getDireccionIp(), laboratorioEntidad, carreraEntidad,computadora.getTipo());
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
    public boolean existeComputadoraRepetida(Integer numero, String tipo, Long idLaboratorio) throws PersistenciaException {
        try {
            EntityManager entity = em.crearEntityManager();
            TypedQuery<Computadora> query = entity.createQuery("""
                                                               SELECT c 
                                                               FROM Computadora c 
                                                               WHERE c.numeroMaquina = :numero 
                                                               AND c.tipo = :tipo 
                                                               AND c.laboratorio.idLaboratorio = :idLab
                                                               """,Computadora.class
            );
            query.setParameter("numero", numero);
            query.setParameter("tipo", tipo);
            query.setParameter("idLab", idLaboratorio);

            List<Computadora> resultado = query.getResultList();
            return !resultado.isEmpty();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al verificar la unicidad de la computadora"+e.getMessage());
        }
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
                computadora.get("carrera"),
                computadora.get("tipo")))
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
