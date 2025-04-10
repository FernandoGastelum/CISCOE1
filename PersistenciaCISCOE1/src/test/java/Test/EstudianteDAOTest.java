/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.CarreraDTO;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOEditar;
import DTOs.EstudianteDTOGuardar;
import Entidades.Carrera;
import Entidades.Estudiante;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Knocmare
 */
public class EstudianteDAOTest {
    
    public EstudianteDAOTest() {
    }

    /**
     * Prueba del metodo guardar(), de la clase de EstudianteDAO.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("Prueba del metodo guardar(), de la clase de EstudianteDAO");
        Estudiante result = null;
        
        IEntityManager em = new EntityManagerDAO();
        EstudianteDAO instance = new EstudianteDAO(em);
        
        EstudianteDTOGuardar estudiante = new EstudianteDTOGuardar("55555", "Angel", "Ruiz", "Garcia", "1234567@", null);
        
        ////////////
        EntityManager entityManager = em.crearEntityManager();
        entityManager.getTransaction().begin();
        
        Carrera carrera = new Carrera("Ingeneria en Software", 360, "#0560f2");
        
        entityManager.persist(carrera);
        entityManager.getTransaction().commit();
        entityManager.close();
        ////////////
        
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(carrera.getIdCarrera());
        estudiante.setCarreraDTO(carreraDTO);
        
        
        result = instance.guardar(estudiante);
        
        // ID Institucional
        assertEquals("55555", result.getIdInstitucional());
        // Nombre completo
        assertEquals("Angel", result.getNombre());
        assertEquals("Ruiz", result.getApellidoPaterno());
        assertEquals("Garcia", result.getApellidoMaterno());
        // Contraseña
        assertEquals("1234567@", result.getContrasena());
        // Carrera
        assertEquals(carrera.getIdCarrera(), result.getCarrera().getIdCarrera());
    }
    
    /**
     * Prueba del metodo guardar(), de la clase de EstudianteDAO.
     */
    @Test(expected = PersistenciaException.class)
    public void testGuardarError() throws Exception {
        System.out.println("Prueba del metodo guardar(), de la clase de EstudianteDAO");
        
        IEntityManager em = new EntityManagerDAO();
        EstudianteDAO instance = new EstudianteDAO(em);
        
        EstudianteDTOGuardar estudiante = new EstudianteDTOGuardar("15555", "Angel", "Ruiz", "Garcia", "1234567@", null);
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(9999L); // ID inválido
        estudiante.setCarreraDTO(carreraDTO);
        
        instance.guardar(estudiante);
        
        fail("Se esperaba una PersistenciaException debido a una carrera no existente.");
    }

    /**
     * Prueba del metodo obtenerPorID(), de la clase de EstudianteDAO.
     */
    @Test
    public void testObtenerPorID() throws Exception {
        System.out.println("Prueba del metodo obtenerPorID(), de la clase de EstudianteDAO.");
        
        EstudianteDAO instance = new EstudianteDAO(new EntityManagerDAO());
        
        // Prueva que lanza una exception cuando no lo encuentra
        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(-1L); 
        });
        
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1L);
        Estudiante estudiante = new Estudiante("55545", "Angel", "Ruiz", "Garcia", "1234567@", carrera);

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();

        Estudiante result = instance.obtenerPorID(estudiante.getIdEstudiante());
        assertNotNull(result);
    }

    /**
     * Prueba del metodo editar(), de la clase de EstudianteDAO.
     */
    @Test
    public void testEditar() throws Exception {
        EstudianteDAO instance = new EstudianteDAO(new EntityManagerDAO());

        Carrera carrera = new Carrera("Nombre", 000, "Color");
        
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(carrera);
        
        Estudiante estudiante = new Estudiante("56545", "Angel", "Ruiz", "Garcia", "1234567@", carrera);
        em.persist(estudiante);
        em.getTransaction().commit();

        EstudianteDTOEditar dto = new EstudianteDTOEditar("NuevoNombre", "NuevoApellidoPaterno", "NuevoApellidoMaterno", 
                "1234567@", false, new CarreraDTO(carrera.getIdCarrera(), "NuevoNombre", 100, "NuevoColor"));
        
        // Prueva que lanza una exception cuando no lo edita
        assertThrows(PersistenciaException.class, () -> {
            instance.editar(999L, dto);
        });
        
        Estudiante actualizado = instance.editar(estudiante.getIdEstudiante(), dto);

        assertEquals("NuevoNombre", actualizado.getNombre());
    }

    /**
     * Prueba del metodo eliminar(), de la clase de EstudianteDAO.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("Prueba del metodo eliminar(), de la clase de EstudianteDAO.");
        
        EstudianteDAO instance = new EstudianteDAO(new EntityManagerDAO());
        
        // Prueba que lanza una excepcion si se intenta eliminar algo que no existe
        assertThrows(PersistenciaException.class, () -> {
            instance.eliminar(-999L);
        });
        
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1L);
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        Estudiante estudiante = new Estudiante("23845", "Angel", "Ruiz", "Garcia", "1234567@", carrera);
        em.persist(estudiante);
        em.getTransaction().commit();

        instance.eliminar(estudiante.getIdEstudiante());

        assertThrows(PersistenciaException.class, () -> {
            instance.obtenerPorID(estudiante.getIdEstudiante());
        });
    }

    /**
     * Prueba del metodo obtenerPorIdInstitucional(), de la clase de EstudianteDAO.
     */
    @Test
    public void testObtenerPorIdInstitucional() throws Exception {
        System.out.println("Prueba del metodo obtenerPorIdInstitucional()), de la clase de EstudianteDAO.");
        
        EstudianteDAO instance = new EstudianteDAO(new EntityManagerDAO());
        
        // Prueva que lanza una exception cuando no lo encuentra
        assertThrows(NoResultException.class, () -> {
            instance.obtenerPorIdInstitucional("99999"); 
        });
        
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1L);
        Estudiante estudiante = new Estudiante("77745", "Angel", "Ruiz", "Garcia", "1234567@", carrera);

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();

        Estudiante result = instance.obtenerPorIdInstitucional(estudiante.getIdInstitucional());
        assertNotNull(result);
    }
    
    /**
     * Prueba del metodo obtener(), de la clase de EstudianteDAO.
     */
    @Test
    public void testObtener() throws Exception {
        System.out.println("Prueba del metodo obtener(), de la clase de EstudianteDAO.");
        
        EstudianteDAO instance = new EstudianteDAO(new EntityManagerDAO());
        
        Carrera carrera = new Carrera("Test", 0,"Test");
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        
        em.getTransaction().begin();
        em.persist(carrera);
        Estudiante estudiante = new Estudiante("76821", "Test", "Test", "Test", "1234141@432", carrera);
        em.persist(estudiante);
        em.getTransaction().commit();
        
        List<Estudiante> resultado = instance.obtener();
        assertTrue(resultado.size() > 0);
    }
    
    /**
     * Prueba de excepcion del metodo obtener(), de la clase de EstudianteDAO.
     */
//    @Test
//    public void testObtenerError() throws Exception {
//        System.out.println("Prueba de excepcion del metodo obtener(), de la clase de EstudianteDAO.");
//        
//        EstudianteDAO instance = new EstudianteDAO(new EntityManagerDAO());
//
//        EntityManager em = new EntityManagerDAO().crearEntityManager();
//        em.getTransaction().begin();
//        em.createQuery("DELETE FROM Estudiante").executeUpdate();
//        em.getTransaction().commit();
//
//        assertThrows(PersistenciaException.class, () -> {
//            instance.obtener();
//        });
//    }
    
}
