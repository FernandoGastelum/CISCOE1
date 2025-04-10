/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Horario;
import Entidades.Instituto;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
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
public class HorarioDAOTest {
    
    private IHorarioDAO horarioDAO;
    private IEntityManager em;
    private ILaboratorioDAO laboratorioDAO;
    private IInstitutoDAO institutoDAO;
    private LaboratorioDTO laboratorioDTO;
    private InstitutoDTO institutoDTO;
    Laboratorio laboratorioEntidad;
    Instituto institutoEntidad;
    InstitutoDTOGuardar institutoDTOGuardar;
    LaboratorioDTOGuardar laboratorioDTOGuardar;
    
    @Before
    public void setUp() {
        em = new EntityManagerDAO();
        horarioDAO = new HorarioDAO(em);
        laboratorioDAO = new LaboratorioDAO(em);
        institutoDAO = new InstitutoDAO(em);
    }
    @After
    public void tearDown() throws PersistenciaException {
        limpiarRegistros();
        limpiarLaboratorio();
        limpiarInstituto();
    }
    private void limpiarRegistros() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Horario").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarInstituto() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Instituto").executeUpdate();

        entity.getTransaction().commit();
    }
    
    private void limpiarLaboratorio() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Laboratorio").executeUpdate();

        entity.getTransaction().commit();
    }
    private InstitutoDTO guardarInstituto() throws PersistenciaException{
        institutoDTOGuardar = new InstitutoDTOGuardar("inttituto", "ins");
        institutoEntidad = institutoDAO.guardar(institutoDTOGuardar);
        return institutoDTO = institutoDAO.obtenerDTO(institutoEntidad.getIdInstituto());
    }
    private LaboratorioDTO guardarLaboratorioDTO() throws PersistenciaException{
        laboratorioDTOGuardar = new LaboratorioDTOGuardar("laboratorio", Calendar.getInstance(), Calendar.getInstance(), "Contrasena1234@", this.guardarInstituto());
        laboratorioEntidad = laboratorioDAO.guardar(laboratorioDTOGuardar);
        return laboratorioDTO = laboratorioDAO.obtenerDTO(laboratorioEntidad.getIdLaboratorio());
        
    }
    private Laboratorio guardarLaboratorio() throws PersistenciaException{
        laboratorioDTOGuardar = new LaboratorioDTOGuardar("laboratorio", Calendar.getInstance(), Calendar.getInstance(), "Contrasena1234@", this.guardarInstituto());
        return laboratorioEntidad = laboratorioDAO.guardar(laboratorioDTOGuardar);
        
    }

    /**
     * Test of guardar method, of class IHorarioDAO.
     */
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        
        HorarioDTOGuardar horario = new HorarioDTOGuardar(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), this.guardarLaboratorioDTO());
        Horario resultado = horarioDAO.guardar(horario);
        assertNotNull(resultado);
        assertEquals("laboratorio", resultado.getLaboratorio().getNombre());
    }
    @Test(expected = PersistenciaException.class)
    public void testGuardarFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO(); 
        IHorarioDAO instance = new HorarioDAO(em);

        instance.guardar(null); 
    }
    @Test
    public void testObtenerPorID() throws Exception {
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        IHorarioDAO instance = new HorarioDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Horario inst = new Horario(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), this.guardarLaboratorio());
        em.persist(inst);
        em.getTransaction().commit();
        Horario result = instance.obtenerPorID(inst.getIdHorario());
        assertNotNull(result);
    }
    @Test(expected = PersistenciaException.class)
    public void testObtenerPorIDFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO();
        IHorarioDAO instance = new HorarioDAO(em);

        instance.obtenerPorID(-1L); 
    }
    @Test
    public void testObtener() throws Exception {
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        IHorarioDAO instance = new HorarioDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Horario inst = new Horario(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), this.guardarLaboratorio());
        em.persist(inst);
        em.getTransaction().commit();
        
        List<Horario> Horarios = instance.obtener();
        assertNotNull(Horarios);
        assertTrue(Horarios.size() > 0);
    }
    @Test
    public void testObtenerDTO() throws Exception {
        EntityManager em = new EntityManagerDAO().crearEntityManager();
        IHorarioDAO instance = new HorarioDAO(new EntityManagerDAO());
        em.getTransaction().begin();
        Horario inst = new Horario(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), this.guardarLaboratorio());
        em.persist(inst);
        em.getTransaction().commit();

        HorarioDTO dto = instance.obtenerDTO(inst.getIdHorario()); 
        assertNotNull(dto);
    }
    @Test
    public void testObtenerDTOFallo() throws Exception {
        IEntityManager em = new EntityManagerDAO();
        IHorarioDAO instance = new HorarioDAO(em);

        HorarioDTO dto = instance.obtenerDTO(-1L); 
        assertNull(dto);
    }
    @Test
    public void testObtenerFallo() throws Exception {
        IHorarioDAO instance = new HorarioDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Horario").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    }
}
