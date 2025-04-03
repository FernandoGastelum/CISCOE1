/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.LaboratorioDTOGuardar;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author gaspa
 */
public class LaboratorioDAO implements ILaboratorioDAO{
    private IEntityManager em;
    public LaboratorioDAO(IEntityManager em){
        this.em = em;
    }

    @Override
    public Laboratorio guardar(LaboratorioDTOGuardar laboratorio) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Laboratorio laboratorioEntidad = new Laboratorio(laboratorio.getNombre(), laboratorio.getHoraApertura(), laboratorio.getHoraCierre(), laboratorio.getContrasenaMaestra(), laboratorio.getInstituto());
        
        entity.persist(laboratorioEntidad);
        entity.getTransaction().commit();
        return laboratorioEntidad;
    }

    @Override
    public Laboratorio obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Laboratorio laboratorio = entity.find(Laboratorio.class, id);
        if(laboratorio!=null){
            return laboratorio;
        }else{
            throw new PersistenciaException("No se encontro un laboratorio con el id "+id);
        }
    }
}
