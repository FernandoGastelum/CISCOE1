/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.HorarioDTOGuardar;
import Entidades.Horario;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author gaspa
 */
public class HorarioDAO implements IHorarioDAO{
    private IEntityManager em;
    public HorarioDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Horario guardar(HorarioDTOGuardar horario) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Horario horarioEntidad = new Horario(horario.getHoraApertura(), horario.getHoraCierre(), horario.getFecha(), horario.getLaboratorio());
        
        entity.persist(horarioEntidad);
        entity.getTransaction().commit();
        return horarioEntidad;
    }

    @Override
    public Horario obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Horario horario = entity.find(Horario.class, id);
        if(horario!=null){
            return horario;
        }else{
            throw new PersistenciaException("No se encontro un horario con el id "+id);
        }
    }
    
}
