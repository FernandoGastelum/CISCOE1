/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.BloqueoDTOGuardar;
import Entidades.Bloqueo;
import Entidades.Carrera;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import javax.persistence.EntityManager;

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
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Bloqueo bloqueoEntidad = new Bloqueo(bloqueo.getFechaBloqueo(), bloqueo.getMotivo());
        
        entity.persist(bloqueoEntidad);
        entity.getTransaction().commit();
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
}
