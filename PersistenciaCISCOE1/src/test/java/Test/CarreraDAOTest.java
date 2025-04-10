package Test;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOEditar;
import DTOs.CarreraDTOGuardar;
import Entidades.Carrera;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Knocmare
 */
public class CarreraDAOTest {
    
    public CarreraDAOTest() {
    }
    @AfterClass
    public static void tearDown() throws PersistenciaException {
        IEntityManager em = new EntityManagerDAO();
        ICarreraDAO carreraDAO = new CarreraDAO(em);
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Carrera").executeUpdate();

        entity.getTransaction().commit();
    }

    /**
     * Prueba del metodo guardar, de la clase de CarreraDAO.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        Carrera result;
        
        IEntityManager em = new EntityManagerDAO();
        CarreraDAO instance = new CarreraDAO(em);
        
        CarreraDTOGuardar carrera = new CarreraDTOGuardar("Ingeneria en Mecatronica", 360, "#0562f2");
        
        result = instance.guardar(carrera);
        
        // Nombre
        assertEquals("Ingeneria en Mecatronica", result.getNombreCarrera());
        // Tiempo Maximo Diario
        assertEquals((Integer) 360, result.getTiempoMaximoDiario());
        // Color
        assertEquals("#0562f2", result.getColor());
    }

    /**
     * Prueba erronea del metodo guardar(), de la clase de CarreraDAO.
     */
    @Test(expected = RollbackException.class)
    public void testGuardarError() throws Exception {
        System.out.println("Prueba erronea del metodo guardar(), de la clase de CarreraDAO");
        
        IEntityManager em = new EntityManagerDAO();
        CarreraDAO instance = new CarreraDAO(em);
        
        CarreraDTOGuardar carrera = new CarreraDTOGuardar("Ingeneria en Mecatronica", 360, null);
        
        instance.guardar(carrera);
        
        fail("Se esperaba una PersistenciaException debido al color no existente.");
    }
    
    /**
     * Prueba del metodo obtenerPorID(), de la clase de CarreraDAO.
     */
    @Test
    public void testObtenerPorID() throws Exception {
        System.out.println("Prueba del metodo obtenerPorID(), de la clase de CarreraDAO.");
        
        CarreraDAO instance = new CarreraDAO(new EntityManagerDAO());
        
        // Prueva que lanza una exception cuando no lo encuentra
        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(-1L); 
        });
        
        Carrera carrera = new Carrera("NombreCarrera", 110, "Coloroso");

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();

        Carrera result = instance.obtenerPorID(carrera.getIdCarrera());
        assertNotNull(result);
    }

    /**
     * Prueba del metodo editar(), de la clase de CarreraDAO.
     */
    @Test
    public void testEditar() throws Exception {
        System.out.println("Prueba del metodo editar(), de la clase de CarreraDAO.");
        
        CarreraDAO instance = new CarreraDAO(new EntityManagerDAO());

        Carrera carrera = new Carrera("Nombre", 000, "Color");
        
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();

        CarreraDTOEditar dto = new CarreraDTOEditar("NuevoNombre", 100, "NuevoColor");
        
        // Prueva que lanza una exception cuando no lo edita
        assertThrows(PersistenciaException.class, () -> {
            instance.editar(999L, dto);
        });
        
        Carrera actualizado = instance.editar(carrera.getIdCarrera(), dto);

        assertEquals("NuevoNombre", actualizado.getNombreCarrera());
    }

    /**
     * Prueba del metodo eliminar(), de la clase de CarreraDAO.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("Prueba del metodo eliminar(), de la clase de CarreraDAO.");
        
        CarreraDAO instance = new CarreraDAO(new EntityManagerDAO());
        
        // Prueba que lanza una excepcion si se intenta eliminar algo que no existe
        assertThrows(PersistenciaException.class, () -> {
            instance.eliminar(-999L);
        });
        
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        Carrera carrera = new Carrera("Test", 0,"Test");
        em.persist(carrera);
        em.getTransaction().commit();

        instance.eliminar(carrera.getIdCarrera());

        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(carrera.getIdCarrera());
        });
    }

    /**
     * Prueba del metodo obtener(), de la clase de CarreraDAO.
     */
    @Test
    public void testObtener() throws Exception {
        System.out.println("Prueba del metodo obtener(), de la clase de CarreraDAO.");
        
        CarreraDAO instance = new CarreraDAO(new EntityManagerDAO());
        
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        
        em.getTransaction().begin();
        Carrera carrera = new Carrera("Test", 0,"Test");
        em.persist(carrera);
        em.getTransaction().commit();
        
        List<Carrera> resultado = instance.obtener();
        assertTrue(resultado.size() > 0);
    }
    
    /**
     * Prueba de excepcion del metodo obtener(), de la clase de CarreraDAO.
     */
    @Test
    public void testObtenerError() throws Exception {
        System.out.println("Prueba de excepcion del metodo obtener(), de la clase de CarreraDAO.");
        
        CarreraDAO instance = new CarreraDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Carrera").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    }
}
