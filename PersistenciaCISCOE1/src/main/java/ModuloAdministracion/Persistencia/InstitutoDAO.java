/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.InstitutoDTOGuardar;
import Entidades.Instituto;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import javax.persistence.EntityManager;

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
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Instituto institutoEntidad = new Instituto(instituto.getNombreOficial(), instituto.getNombreAbreviado());
        
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
}
