/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOEditar;
import DTOs.InstitutoDTOGuardar;
import Entidades.Instituto;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author gaspa
 */
public class InstitutoDAOTest {
    private InstitutoDAO institutoDAO;
    private IEntityManager em;
    
    @Before
    public void setUp() {
        // Inicializas tu EntityManager y DAO antes de cada test
        em = new EntityManagerDAO();
        institutoDAO = new InstitutoDAO(em);
    }
    @After
    public void tearDown() throws PersistenciaException {
        // Llamamos a limpiar los registros
        limpiarRegistros();
    }
    private void limpiarRegistros() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Instituto").executeUpdate();

        entity.getTransaction().commit();
    }

    /**
     * Test of guardar method, of class InstitutoDAO.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        IEntityManager em = new EntityManagerDAO();
        InstitutoDAO dao = new InstitutoDAO(em);
        InstitutoDTOGuardar instituto = new InstitutoDTOGuardar("Insntituto Prueba", "Prueab");
        Instituto resultado = dao.guardar(instituto);
        assertNotNull(resultado);
        assertEquals("Insntituto Prueba", resultado.getNombreOficial());
        assertEquals("Prueab", resultado.getNombreAbreviado());
    }
    @Test(expected = PersistenciaException.class)
    public void testGuardarFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO(); 
        InstitutoDAO instance = new InstitutoDAO(em);

        instance.guardar(null); 
    }
    @Test
    public void testObtenerPorID() throws Exception {
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        InstitutoDAO instance = new InstitutoDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Instituto inst = new Instituto("Prueba obtener", "id");
        em.persist(inst);
        em.getTransaction().commit();
        Instituto result = instance.obtenerPorID(inst.getIdInstituto());
        assertNotNull(result);
    }
    @Test(expected = PersistenciaException.class)
    public void testObtenerPorIDFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO();
        InstitutoDAO instance = new InstitutoDAO(em);

        instance.obtenerPorID(-1L); 
    }
    @Test
    public void testObtener() throws Exception {
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        InstitutoDAO instance = new InstitutoDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Instituto inst = new Instituto("Prueba obtenner", "obtener");
        em.persist(inst);
        em.getTransaction().commit();
        
        List<Instituto> institutos = instance.obtener();
        assertNotNull(institutos);
        assertTrue(institutos.size() > 0);
    }
    
    
    @Test
    public void testObtenerDTO() throws Exception {
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        InstitutoDAO instance = new InstitutoDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Instituto inst = new Instituto("Prueba dto", "dto");
        em.persist(inst);
        em.getTransaction().commit();

        InstitutoDTO dto = instance.obtenerDTO(inst.getIdInstituto()); 
        assertNotNull(dto);
    }
    @Test
    public void testObtenerDTOFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO();
        InstitutoDAO instance = new InstitutoDAO(em);

        InstitutoDTO dto = instance.obtenerDTO(-1L); 
        assertNull(dto);
    }
    @Test
    public void testEliminarExito() throws Exception {
        
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        InstitutoDAO instance = new InstitutoDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Instituto inst = new Instituto("Prueba eliminar", "elim");
        em.persist(inst);
        em.getTransaction().commit();
        instance.eliminar(inst.getIdInstituto());
        
        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(inst.getIdInstituto());
        });
        
    }
    @Test(expected = PersistenciaException.class)
    public void testEliminarFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO();
        InstitutoDAO instance = new InstitutoDAO(em);

        instance.eliminar(-1L); 
    }
    @Test
    public void testObtenerFallo() throws Exception {
        InstitutoDAO instance = new InstitutoDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Instituto").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    }    
    
}
