/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.BloqueoDTO;
import DTOs.BloqueoDTOGuardar;
import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import Entidades.Bloqueo;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IBloqueoDAO;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Persistencia.BloqueoDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gaspa
 */
public class BloqueoDAOTest {
    private IBloqueoDAO bloqueoDAO;
    private IEntityManager em;
    private EstudianteDTOGuardar estudianteDTOGuardar;
    private IEstudianteDAO estudianteDAO;
    private Estudiante estudianteEtidad;
    private EstudianteDTO estudianteDTO;
    private Carrera carreraEntidad;
    private CarreraDTOGuardar carreraDTOGuardar;
    private ICarreraDAO carreraDAO;
    @Before
    public void setUp() {
        // Inicializas tu EntityManager y DAO antes de cada test
        em = new EntityManagerDAO();
        bloqueoDAO = new BloqueoDAO(em);
        estudianteDAO = new EstudianteDAO(em);
        carreraDAO = new CarreraDAO(em);
    }
    @After
    public void tearDown() throws PersistenciaException {
        limpiarRegistros();
        limpiarEstudiantes();
    }
    private void limpiarEstudiantes()throws PersistenciaException{
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Estudiante").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarRegistros() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Bloqueo").executeUpdate();

        entity.getTransaction().commit();
    }
    private CarreraDTO guardarCarrera()throws PersistenciaException{
        carreraDTOGuardar = new CarreraDTOGuardar("Carrera", 300, "Red");
        carreraEntidad = carreraDAO.guardar(carreraDTOGuardar);
        return carreraDAO.obtenerDTO(carreraEntidad.getIdCarrera());
    }
    private EstudianteDTO guardarEstudiante()throws PersistenciaException{
        estudianteDTOGuardar = new EstudianteDTOGuardar("00000191748", "EstudianteTest", "ApellidoPTest", "ApellidoMTest", "Alumno12345@", this.guardarCarrera());
        estudianteEtidad = estudianteDAO.guardar(estudianteDTOGuardar);
        return estudianteDTO = estudianteDAO.obtenerDTO(estudianteEtidad.getIdEstudiante());
    }
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        BloqueoDTOGuardar bloqueo = new BloqueoDTOGuardar(Calendar.getInstance(), "TestBloqueo", this.guardarEstudiante());
        Bloqueo resultado = bloqueoDAO.guardar(bloqueo);
        assertNotNull(resultado);
        assertEquals("TestBloqueo", resultado.getMotivo());
    }
    @Test(expected = PersistenciaException.class)
    public void testGuardarFallo() throws Exception {
        bloqueoDAO.guardar(null); 
    }
    @Test
    public void testObtenerPorID() throws Exception {
        
        BloqueoDTOGuardar bloqueo = new BloqueoDTOGuardar(Calendar.getInstance(), "TestBloqueo", this.guardarEstudiante());
        Bloqueo bloqueoGuardado = bloqueoDAO.guardar(bloqueo);
        Bloqueo resultado = bloqueoDAO.obtenerPorID(bloqueoGuardado.getIdBloqueo());
        assertNotNull(resultado);
    }
    @Test(expected = PersistenciaException.class)
    public void testObtenerPorIDFallo() throws Exception {
        
        bloqueoDAO.obtenerPorID(-1L); 
    }
    @Test
    public void testObtener() throws Exception {
        
        BloqueoDTOGuardar bloqueo = new BloqueoDTOGuardar(Calendar.getInstance(), "TestBloqueo", this.guardarEstudiante());
        Bloqueo bloqueoGuardado = bloqueoDAO.guardar(bloqueo);
        
        List<Bloqueo> bloqueos = bloqueoDAO.obtener();
        assertNotNull(bloqueos);
        assertTrue(!bloqueos.isEmpty());
    }
    @Test
    public void testObtenerDTO() throws Exception {
        BloqueoDTOGuardar bloqueo = new BloqueoDTOGuardar(Calendar.getInstance(), "TestBloqueo", this.guardarEstudiante());
        Bloqueo bloqueoGuardado = bloqueoDAO.guardar(bloqueo);
        
        BloqueoDTO dto = bloqueoDAO.obtenerDTO(bloqueoGuardado.getIdBloqueo()); 
        assertNotNull(dto);
    }
    @Test
    public void testObtenerDTOFallo() throws Exception {
        
        BloqueoDTO dto = bloqueoDAO.obtenerDTO(-1L); 
        assertNull(dto);
    }
    @Test
    public void testEliminarExito() throws Exception {
        
        BloqueoDTOGuardar bloqueo = new BloqueoDTOGuardar(Calendar.getInstance(), "TestBloqueo", this.guardarEstudiante());
        Bloqueo bloqueoGuardado = bloqueoDAO.guardar(bloqueo);
        bloqueoDAO.eliminar(bloqueoGuardado.getIdBloqueo());
        
        assertThrows(PersistenciaException.class, () -> {
            bloqueoDAO.obtenerPorID(bloqueoGuardado.getIdBloqueo());
        });
        
    }
    @Test(expected = PersistenciaException.class)
    public void testEliminarFallo() throws Exception {
        bloqueoDAO.eliminar(-1L); 
    }
    @Test
    public void testObtenerFallo() throws Exception {
        BloqueoDAO instance = new BloqueoDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Bloqueo").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    }
    
}
