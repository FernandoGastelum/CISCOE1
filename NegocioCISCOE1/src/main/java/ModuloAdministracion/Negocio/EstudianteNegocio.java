/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import Entidades.Computadora;
import Entidades.Estudiante;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaspa
 */
public class EstudianteNegocio implements IEstudianteNegocio{
    private final IEstudianteDAO estudianteDAO;
    public EstudianteNegocio(IEstudianteDAO estudianteDAO){
        this.estudianteDAO = estudianteDAO;
    }
    @Override
    public EstudianteDTO guardar(EstudianteDTOGuardar estudiante) throws NegocioException {
        try {this.reglasNegocioGuardar(estudiante);
                Estudiante estudianteGuardado = this.estudianteDAO.guardar(estudiante);
                return this.estudianteDAO.obtenerDTO(estudianteGuardado.getIdEstudiante());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error "+ex.getMessage());
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
    public EstudianteDTO obtenerPorID(Long id) throws NegocioException {
        try {
                return estudianteDAO.obtenerDTO(id);
            } catch (PersistenciaException ex) {
                throw new NegocioException("Error "+ex.getMessage());
            }
    }

    private boolean reglasNegocioGuardar(EstudianteDTOGuardar estudiante) throws NegocioException {
        if(estudiante.getCarrera()==null){
            throw new NegocioException("La carrera no puede estar vacia");
        }
        if(estudiante.getNombre()==null){
            throw new NegocioException("El nombre no puede estar vacio");
        }
        if(estudiante.getApellidoMaterno()==null){
            throw new NegocioException("El apellido materno no puede estar vacio");
        }
        if(estudiante.getApellidoPaterno()==null){
            throw new NegocioException("El apellido paterno no puede estar vacio");
        }
        if(estudiante.getContrasena()==null){
            throw new NegocioException("La contrasenia no puede estar vacia");
        }
        return true;
    }
    
}
