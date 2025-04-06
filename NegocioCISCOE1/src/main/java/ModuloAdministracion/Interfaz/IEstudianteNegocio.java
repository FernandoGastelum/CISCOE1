/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import Excepcion.NegocioException;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IEstudianteNegocio {
    EstudianteDTO guardar(EstudianteDTOGuardar estudiante) throws NegocioException;
    
    List<EstudianteDTO> obtener() throws NegocioException;
    
    EstudianteDTO obtenerPorID(Long id) throws NegocioException;
}
