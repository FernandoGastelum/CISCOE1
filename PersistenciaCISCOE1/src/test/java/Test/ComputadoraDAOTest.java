/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Instituto;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author gaspa
 */
public class ComputadoraDAOTest {
    private IComputadoraDAO computadoraDAO;
    private ICarreraDAO carreraDAO;
    private IEntityManager em;
    
    private ILaboratorioDAO laboratorioDAO;
    private IInstitutoDAO institutoDAO;
    private LaboratorioDTO laboratorioDTO;
    private InstitutoDTO institutoDTO;
    Laboratorio laboratorioEntidad;
    Instituto institutoEntidad;
    InstitutoDTOGuardar institutoDTOGuardar;
    LaboratorioDTOGuardar laboratorioDTOGuardar;
    
    Carrera carreraEntidad;
    CarreraDTOGuardar carreraDTOGuardar;
    @Before
    public void setUp() {
        em = new EntityManagerDAO();
        computadoraDAO = new ComputadoraDAO(em);
        laboratorioDAO = new LaboratorioDAO(em);
        carreraDAO = new CarreraDAO(em);
        institutoDAO = new InstitutoDAO(em);
    }
    private InstitutoDTO guardarInstituto() throws PersistenciaException{
        institutoDTOGuardar = new InstitutoDTOGuardar("inttituto", "ins");
        institutoEntidad = institutoDAO.guardar(institutoDTOGuardar);
        return institutoDTO = institutoDAO.obtenerDTO(institutoEntidad.getIdInstituto());
    }
    private LaboratorioDTO guardarLaboratorio() throws PersistenciaException{
        laboratorioDTOGuardar = new LaboratorioDTOGuardar("laboratorio", Calendar.getInstance(), Calendar.getInstance(), "Contrasena1234@", this.guardarInstituto());
        laboratorioEntidad = laboratorioDAO.guardar(laboratorioDTOGuardar);
        return laboratorioDTO = laboratorioDAO.obtenerDTO(laboratorioEntidad.getIdLaboratorio());
    }
    private CarreraDTO guardarCarrera()throws PersistenciaException{
        carreraDTOGuardar = new CarreraDTOGuardar("Carrera", 300, "Red");
        carreraEntidad = carreraDAO.guardar(carreraDTOGuardar);
        return carreraDAO.obtenerDTO(carreraEntidad.getIdCarrera());
    }
    @After
    public void tearDown() throws PersistenciaException {
        limpiarRegistros();
        limpiarLaboratorio();
        limpiarInstituto();
        
        limpiarCarrera();
    }
    private void limpiarRegistros() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Computadora").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarInstituto() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Instituto").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarCarrera() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Carrera").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarLaboratorio() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Laboratorio").executeUpdate();

        entity.getTransaction().commit();
    }
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        
        ComputadoraDTOGuardar computadora = new ComputadoraDTOGuardar(1, "192.1.24", this.guardarLaboratorio(), this.guardarCarrera(), "Comun");
        Computadora resultado = computadoraDAO.guardar(computadora);
        assertNotNull(resultado);
        assertEquals("192.1.24", resultado.getDireccionIp());
    }
    @Test(expected = PersistenciaException.class)
    public void testGuardarFallo() throws Exception {
        computadoraDAO.guardar(null); 
    }
    @Test
    public void testObtenerPorID() throws Exception {
        
        ComputadoraDTOGuardar computadora = new ComputadoraDTOGuardar(1, "192.1.24", this.guardarLaboratorio(), this.guardarCarrera(), "Comun");
        Computadora computadoraGuardada = computadoraDAO.guardar(computadora);
        Computadora resultado = computadoraDAO.obtenerPorID(computadoraGuardada.getIdComputadora());
        assertNotNull(resultado);
    }
    @Test(expected = PersistenciaException.class)
    public void testObtenerPorIDFallo() throws Exception {
        
        computadoraDAO.obtenerPorID(-1L); 
    }
    @Test
    public void testObtener() throws Exception {
        
        ComputadoraDTOGuardar computadora = new ComputadoraDTOGuardar(1, "192.1.24", this.guardarLaboratorio(), this.guardarCarrera(), "Comun");
        Computadora computadoraGuardada = computadoraDAO.guardar(computadora);
        
        List<Computadora> computadoras = computadoraDAO.obtener();
        assertNotNull(computadoras);
        assertTrue(!computadoras.isEmpty());
    }
    @Test
    public void testObtenerDTO() throws Exception {
        ComputadoraDTOGuardar computadora = new ComputadoraDTOGuardar(1, "192.1.24", this.guardarLaboratorio(), this.guardarCarrera(), "Comun");
        Computadora computadoraGuardada = computadoraDAO.guardar(computadora);

        ComputadoraDTO dto = computadoraDAO.obtenerDTO(computadoraGuardada.getIdComputadora()); 
        assertNotNull(dto);
    }
    @Test
    public void testObtenerDTOFallo() throws Exception {
        
        ComputadoraDTO dto = computadoraDAO.obtenerDTO(-1L); 
        assertNull(dto);
    }
    @Test
    public void testEliminarExito() throws Exception {
        
        ComputadoraDTOGuardar computadora = new ComputadoraDTOGuardar(1, "192.1.24", this.guardarLaboratorio(), this.guardarCarrera(), "Comun");
        Computadora computadoraGuardada = computadoraDAO.guardar(computadora);
        computadoraDAO.eliminar(computadoraGuardada.getIdComputadora());
        
        assertThrows(PersistenciaException.class, () -> {
            computadoraDAO.obtenerPorID(computadoraGuardada.getIdComputadora());
        });
        
    }
    @Test(expected = PersistenciaException.class)
    public void testEliminarFallo() throws Exception {
        computadoraDAO.eliminar(-1L); 
    }
    @Test
    public void testObtenerFallo() throws Exception {
        ComputadoraDAO instance = new ComputadoraDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Computadora").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    }    
}
