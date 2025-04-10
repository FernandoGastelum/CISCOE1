package Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import DTOs.InstitutoDTO;
import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOEditar;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Instituto;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class LaboratorioDAOTest {
    
    private InstitutoDAO institutoDAO;
    private IEntityManager em;
    
    @Before
    public void setUp() {
        em = new EntityManagerDAO();
        institutoDAO = new InstitutoDAO(em);
    }
    @After
    public void tearDown() throws PersistenciaException {
        limpiarRegistros();
        limpiarInstitutos();
    }
    private void limpiarRegistros() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Laboratorio").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarInstitutos() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Instituto").executeUpdate();

        entity.getTransaction().commit();
    }
    
    /**
     * Test of guardar method, of class LaboratorioDAO.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");

        IEntityManager em = new EntityManagerDAO();
        LaboratorioDAO dao = new LaboratorioDAO(em);

        EntityManager entityManager = em.crearEntityManager();
        entityManager.getTransaction().begin();
        Instituto instituto = new Instituto();
        instituto.setNombreOficial("Instituto Prueba");
        instituto.setNombreAbreviado("TEST");
        entityManager.persist(instituto);
        entityManager.getTransaction().commit();
        entityManager.close();

        InstitutoDTO institutoDTO = new InstitutoDTO();
        institutoDTO.setIdInstituto(instituto.getIdInstituto());

        Calendar apertura = Calendar.getInstance();
        apertura.set(Calendar.HOUR_OF_DAY, 8);
        apertura.set(Calendar.MINUTE, 0);

        Calendar cierre = Calendar.getInstance();
        cierre.set(Calendar.HOUR_OF_DAY, 18);
        cierre.set(Calendar.MINUTE, 0);

        LaboratorioDTOGuardar dto = new LaboratorioDTOGuardar("Laboratorio de Prueba", apertura, cierre, "Maestra12345@", institutoDTO);

        Laboratorio resultado = dao.guardar(dto);

        assertNotNull(resultado);
        assertEquals("Laboratorio de Prueba", resultado.getNombre());
        assertEquals("Maestra12345@", resultado.getContrasenaMaestra());
        assertEquals(instituto.getIdInstituto(), resultado.getInstituto().getIdInstituto());
    }
    @Test(expected = PersistenciaException.class)
    public void testGuardarInstitutoInexistente() throws Exception {
        IEntityManager em = new EntityManagerDAO();
        LaboratorioDAO dao = new LaboratorioDAO(em);

        InstitutoDTO institutoDTO = new InstitutoDTO();
        institutoDTO.setIdInstituto(9999L); 

        Calendar apertura = Calendar.getInstance();
        apertura.set(Calendar.HOUR_OF_DAY, 8);
        apertura.set(Calendar.MINUTE, 0);

        Calendar cierre = Calendar.getInstance();
        cierre.set(Calendar.HOUR_OF_DAY, 18);
        cierre.set(Calendar.MINUTE, 0);

        LaboratorioDTOGuardar laboratorioDTO = new LaboratorioDTOGuardar(
                "Laboratorio Test Error",
                apertura,
                cierre,
                "ClaveTest123",
                institutoDTO
        );

        dao.guardar(laboratorioDTO);

        fail("Se esperaba una PersistenciaException debido a un instituto no existente.");
    }

    /**
     * Test of obtenerPorID method, of class LaboratorioDAO.
     */
    @Test
    public void testObtenerPorID() throws Exception {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());
        Instituto instituto = new Instituto("Test", "Test");
        instituto.setIdInstituto(1L);
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(instituto);
        Laboratorio laboratorio = new Laboratorio("Test", Calendar.getInstance(), Calendar.getInstance(), "12345", instituto);
        em.persist(laboratorio);
        em.getTransaction().commit();

        Laboratorio result = instance.obtenerPorID(laboratorio.getIdLaboratorio());
        assertNotNull(result);
    }
    @Test
    public void testObtenerPorIDFallo() {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());
        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(-1L); 
        });
    }

    /**
     * Test of editar method, of class LaboratorioDAO.
     */
    @Test
    public void testEditar() throws Exception {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());

        Instituto instituto = new Instituto("Nombre", "Descripcion");
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(instituto);
        Laboratorio lab = new Laboratorio("Viejo", Calendar.getInstance(), Calendar.getInstance(), "1234", instituto);
        em.persist(lab);
        em.getTransaction().commit();

        LaboratorioDTOEditar dto = new LaboratorioDTOEditar("NuevoNombre", Calendar.getInstance(), Calendar.getInstance(), "NuevaPass", new InstitutoDTO(instituto.getIdInstituto(), "Nombre", "Desc"));
        Laboratorio actualizado = instance.editar(lab.getIdLaboratorio(), dto);

        assertEquals("NuevoNombre", actualizado.getNombre());
    }

    @Test
    public void testEditarFallo() {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());
        LaboratorioDTOEditar dto = new LaboratorioDTOEditar("Nuevo", Calendar.getInstance(), Calendar.getInstance(), "123", new InstitutoDTO(999L, "", ""));

        assertThrows(PersistenciaException.class, () -> {
            instance.editar(999L, dto);
        });
    }

    /**
     * Test of eliminar method, of class LaboratorioDAO.
     */
    @Test
    public void testEliminarExito() throws Exception {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());
        Instituto instituto = new Instituto("Test", "Test");
        instituto.setIdInstituto(1L);
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(instituto);
        Laboratorio lab = new Laboratorio("Test", Calendar.getInstance(), Calendar.getInstance(), "123", instituto);
        em.persist(lab);
        em.getTransaction().commit();

        instance.eliminar(lab.getIdLaboratorio());

        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(lab.getIdLaboratorio());
        });
    }

    @Test
    public void testEliminarFallo() {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());
        assertThrows(PersistenciaException.class, () -> {
            instance.eliminar(-999L);
        });
    }

    /**
     * Test of obtener method, of class LaboratorioDAO.
     */
    @Test
    public void testObtenerExito() throws Exception {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());
        Instituto instituto = new Instituto("Test", "Test");
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(instituto);
        Laboratorio lab = new Laboratorio("Test", Calendar.getInstance(), Calendar.getInstance(), "123", instituto);
        em.persist(lab);
        em.getTransaction().commit();
        List<Laboratorio> resultado = instance.obtener();
        assertTrue(resultado.size() > 0);
    }

    @Test
    public void testObtenerFallo() throws Exception {
        LaboratorioDAO instance = new LaboratorioDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Laboratorio").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    }
    
}
