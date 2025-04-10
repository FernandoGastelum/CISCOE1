/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOEditar;
import DTOs.EstudianteDTOGuardar;
import DTOs.EstudianteTablaDTO;
import Entidades.Estudiante;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author gaspa
 */
public class EstudianteNegocio implements IEstudianteNegocio {

    private final IEstudianteDAO estudianteDAO;

    public EstudianteNegocio(IEstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public EstudianteDTO guardar(EstudianteDTOGuardar estudiante) throws NegocioException {
        try {
            this.reglasNegocioGuardar(estudiante);
            Estudiante estudianteGuardado = this.estudianteDAO.guardar(estudiante);
            return this.estudianteDAO.obtenerDTO(estudianteGuardado.getIdEstudiante());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<EstudianteDTO> obtener() throws NegocioException {
        try {
            List<Estudiante> listaEstudiantes = this.estudianteDAO.obtener();
            List<EstudianteDTO> dtos = new ArrayList<>();
            for (Estudiante estudiante : listaEstudiantes) {
                dtos.add(this.estudianteDAO.obtenerDTO(estudiante.getIdEstudiante()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    @Override
    public List<EstudianteTablaDTO> obtenerTabla() throws NegocioException {
        try {
            List<Estudiante> listaEstudiantes = this.estudianteDAO.obtener();
            List<EstudianteDTO> dtos = new ArrayList<>();
            for (Estudiante estudiante : listaEstudiantes) {
                dtos.add(this.estudianteDAO.obtenerDTO(estudiante.getIdEstudiante()));
            }
            return this.convertirTablaDTO(dtos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private List<EstudianteTablaDTO> convertirTablaDTO(List<EstudianteDTO> estudiantes) {
        if (estudiantes == null) {
            return null;
        }
        List<EstudianteTablaDTO> estudiantesDTO = new ArrayList<>();
        for (EstudianteDTO estudiante : estudiantes) {
            String nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellidoPaterno() + " " + estudiante.getApellidoMaterno();
            String estatus = estudiante.getEstatusInscripcion() == true ? "Inscrito" : "Desinscrito";
            EstudianteTablaDTO dato = new EstudianteTablaDTO(estudiante.getIdEstudiante(), estudiante.getIdInstitucional(), nombreCompleto, estatus);
            estudiantesDTO.add(dato);
        }
        return estudiantesDTO;
    }
    
    @Override
    public EstudianteDTO obtenerPorIdInstitucional(String id) throws NegocioException{
        try {
             Estudiante estudianteEntidad = estudianteDAO.obtenerPorIdInstitucional(id);
             return estudianteDAO.obtenerDTO(estudianteEntidad.getIdEstudiante());
        }catch (NoResultException e) {
        throw new NegocioException("No se encontró el estudiante con el ID: " + id);
        }catch (PersistenciaException e) {
            throw new NegocioException("Error " + e.getMessage());
        }
    }

    @Override
    public EstudianteDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return estudianteDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    @Override
    public EstudianteDTO editar(Long id, EstudianteDTOEditar estudiante) throws NegocioException {
        try {
            this.reglasNegocioEditar(estudiante);
            Estudiante estudianteEditado = this.estudianteDAO.editar(id, estudiante);
            return this.estudianteDAO.obtenerDTO(estudianteEditado.getIdEstudiante());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    @Override
    public void eliminar(Long id) throws NegocioException {
        try {
            this.estudianteDAO.eliminar(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private boolean reglasNegocioGuardar(EstudianteDTOGuardar estudiante) throws NegocioException {
        if (estudiante.getCarreraDTO()== null) {
            throw new NegocioException("La carrera no puede estar vacia");
        }
        if (estudiante.getNombre() == null) {
            throw new NegocioException("El nombre no puede estar vacio");
        }
        if (estudiante.getApellidoMaterno() == null) {
            throw new NegocioException("El apellido materno no puede estar vacio");
        }
        if (estudiante.getApellidoPaterno() == null) {
            throw new NegocioException("El apellido paterno no puede estar vacio");
        }
        if (estudiante.getContrasena() == null) {
            throw new NegocioException("La contrasenia no puede estar vacia");
        }
        return true;
    }
    
    private boolean reglasNegocioEditar(EstudianteDTOEditar estudiante) throws NegocioException {
        if (estudiante.getCarreraDTO()== null) {
            throw new NegocioException("La carrera no puede estar vacia");
        }
        if (estudiante.getNombre() == null) {
            throw new NegocioException("El nombre no puede estar vacio");
        }
        if (estudiante.getApellidoMaterno() == null) {
            throw new NegocioException("El apellido materno no puede estar vacio");
        }
        if (estudiante.getApellidoPaterno() == null) {
            throw new NegocioException("El apellido paterno no puede estar vacio");
        }
        if (estudiante.getContrasena() == null) {
            throw new NegocioException("La contrasenia no puede estar vacia");
        }
        return true;
    }

}
