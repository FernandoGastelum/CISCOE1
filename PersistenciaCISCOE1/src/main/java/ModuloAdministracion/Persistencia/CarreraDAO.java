/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.CarreraDTOGuardar;
import Entidades.Carrera;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import javax.persistence.EntityManager;

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
    
}
