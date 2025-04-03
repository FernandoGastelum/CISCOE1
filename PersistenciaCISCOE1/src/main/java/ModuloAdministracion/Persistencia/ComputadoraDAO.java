/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.ComputadoraDTOGuardar;
import Entidades.Computadora;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import javax.persistence.EntityManager;

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
        
        Computadora computadoraEntidad = new Computadora(computadora.getNumeroMaquina(), computadora.getDireccionIp(), computadora.getEstatus(), computadora.getLaboratorio(), computadora.getCarrera());
        
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
}
