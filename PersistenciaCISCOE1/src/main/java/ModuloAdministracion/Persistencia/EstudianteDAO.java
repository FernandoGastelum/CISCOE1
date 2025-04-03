/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Persistencia;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author gaspa
 */
public class EstudianteDAO implements IEstudianteDAO{
    private IEntityManager em;
    public EstudianteDAO(IEntityManager em){
        this.em = em;
    }
    @Override
    public Estudiante guardar(EstudianteDTOGuardar estudiante) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();
        
        Estudiante estudianteEntidad = new Estudiante(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoPaterno(), estudiante.getContrasena(), estudiante.getCarrera());
        
        entity.persist(estudianteEntidad);
        entity.getTransaction().commit();
        return estudianteEntidad;
    }

    @Override
    public Estudiante obtenerPorID(Long id) throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        Estudiante alumno = entity.find(Estudiante.class, id);
        if(alumno!=null){
            return alumno;
        }else{
            throw new PersistenciaException("No se encontro un estudiante con el id "+id);
        }
    }

    @Override
    public List<Estudiante> obtener() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EstudianteDTO obtenerEstudianteDTO(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
