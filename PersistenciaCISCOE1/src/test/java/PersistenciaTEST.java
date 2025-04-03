/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import DTOs.CarreraDTOGuardar;
import DTOs.EstudianteDTOGuardar;
import Entidades.Carrera;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author gaspa
 */
public class PersistenciaTEST {
    public IEntityManager entityManager;
    @Before
    public void setUp() {
        entityManager = new EntityManagerDAO();
    }
    @Test
    public void agregarEstudiante(){
        try {
            IEstudianteDAO IEstudianteDAO = new EstudianteDAO(entityManager);
            ICarreraDAO iCarreraDAO = new CarreraDAO(entityManager);
            CarreraDTOGuardar carreraDTO = new CarreraDTOGuardar("Software", 180, "Rojo");
            Carrera carreraEntidad = iCarreraDAO.guardar(carreraDTO);
            EstudianteDTOGuardar guardar = new EstudianteDTOGuardar("Estudiante", "Paterno", "Materno", "12345", carreraEntidad);
            Estudiante estudianteEntidad = IEstudianteDAO.guardar(guardar);
            assertEquals("Estudiante", IEstudianteDAO.obtenerPorID(estudianteEntidad.getIdEstudiante()).getNombre());
        } catch (PersistenciaException ex) {
            Logger.getLogger(PersistenciaTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
