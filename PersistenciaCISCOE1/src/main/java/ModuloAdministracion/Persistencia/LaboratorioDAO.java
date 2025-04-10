/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOEditar;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Instituto;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
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
public class LaboratorioDAO implements ILaboratorioDAO {

    private IEntityManager em;

    public LaboratorioDAO(IEntityManager em) {
        this.em = em;
    }

    @Override
    public Laboratorio guardar(LaboratorioDTOGuardar laboratorio) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        Laboratorio laboratorioEntidad = this.convertirEntidad(laboratorio);

        entity.persist(laboratorioEntidad);
        entity.getTransaction().commit();
        return laboratorioEntidad;
    }

    public Laboratorio convertirEntidad(LaboratorioDTOGuardar laboratorio) throws PersistenciaException {
        IInstitutoDAO institutoDAO = new InstitutoDAO(em);

        Instituto institutoEntidad = institutoDAO.obtenerPorID(laboratorio.getInstitutoDTO().getIdInstituto());
        Laboratorio laboratorioEntidad = new Laboratorio(laboratorio.getNombre(),
                laboratorio.getHoraApertura(), laboratorio.getHoraCierre(),
                laboratorio.getContrasenaMaestra(), institutoEntidad);
        return laboratorioEntidad;
    }

    @Override
    public Laboratorio obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Laboratorio laboratorio = entity.find(Laboratorio.class, id);
        if (laboratorio != null) {
            return laboratorio;
        } else {
            throw new PersistenciaException("No se encontro un laboratorio con el id " + id);
        }
    }
    
    @Override
    public Laboratorio editar(Long id, LaboratorioDTOEditar laboratorioDTO) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Laboratorio laboratorioEntidad = entity.find(Laboratorio.class, id);
        if (laboratorioEntidad == null) {
            throw new PersistenciaException("No se encontró el laboratorio con el id " + id);
        }
        
        IInstitutoDAO institutoDAO = new InstitutoDAO(em);
        Instituto institutoEntidad = institutoDAO.obtenerPorID(laboratorioDTO.getInstitutoDTO().getIdInstituto());
        
        laboratorioEntidad.setNombre(laboratorioDTO.getNombre());
        laboratorioEntidad.setHoraApertura(laboratorioDTO.getHoraApertura());
        laboratorioEntidad.setHoraCierre(laboratorioDTO.getHoraCierre());
        laboratorioEntidad.setContrasenaMaestra(laboratorioDTO.getContrasenaMaestra());
        laboratorioEntidad.setInstituto(institutoEntidad);
        
        entity.merge(laboratorioEntidad);
        entity.getTransaction().commit();
        return laboratorioEntidad;
    }
    
    @Override
    public void eliminar(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Laboratorio laboratorioEntidad = entity.find(Laboratorio.class, id);
        if (laboratorioEntidad == null) {
            throw new PersistenciaException("No se encontró el laboratorio con el id " + id);
        }
        
        entity.remove(laboratorioEntidad);
        entity.getTransaction().commit();
    }

    @Override
    public List<Laboratorio> obtener() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        TypedQuery<Laboratorio> query = entity.createQuery("""
                                                           SELECT l
                                                           FROM Laboratorio l
                                                           """, Laboratorio.class);
        List<Laboratorio> resultados = query.getResultList();
        if (resultados.isEmpty()) {
            
            return null;
        }
        return resultados;
    }

    @Override
    public LaboratorioDTO obtenerDTO(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<LaboratorioDTO> cq = cb.createQuery(LaboratorioDTO.class);
        Root<Laboratorio> laboratorio = cq.from(Laboratorio.class);
        cq.select(cb.construct(LaboratorioDTO.class,
                laboratorio.get("idLaboratorio"),
                laboratorio.get("nombre"),
                laboratorio.get("horaApertura"),
                laboratorio.get("horaCierre"),
                laboratorio.get("contrasenaMaestra"),
                laboratorio.get("instituto")))
                .where(cb.equal(laboratorio.get("idLaboratorio"), id));

        TypedQuery<LaboratorioDTO> query = entity.createQuery(cq);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
