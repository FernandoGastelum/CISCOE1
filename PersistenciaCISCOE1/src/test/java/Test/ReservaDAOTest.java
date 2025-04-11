/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import DTOs.HorarioDTO;
import DTOs.HorarioDTOGuardar;
import DTOs.InstitutoDTO;
import DTOs.InstitutoDTOGuardar;
import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import DTOs.ReservaDTO;
import DTOs.ReservaDTOEditar;
import DTOs.ReservaDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Estudiante;
import Entidades.Horario;
import Entidades.Instituto;
import Entidades.Laboratorio;
import Entidades.Reserva;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Persistencia.BloqueoDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Persistencia.ReservaDAO;
import java.util.Calendar;
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
public class ReservaDAOTest {
    private IEntityManager em;
    private IReservaDAO reservaDAO;
    private EstudianteDTOGuardar estudianteDTOGuardar;
    private IEstudianteDAO estudianteDAO;
    private Estudiante estudianteEtidad;
    private EstudianteDTO estudianteDTO;
    private Carrera carreraEntidad;
    private CarreraDTOGuardar carreraDTOGuardar;
    private ICarreraDAO carreraDAO;
    private ILaboratorioDAO laboratorioDAO;
    private IInstitutoDAO institutoDAO;
    private LaboratorioDTO laboratorioDTO;
    private InstitutoDTO institutoDTO;
    private Laboratorio laboratorioEntidad;
    private Instituto institutoEntidad;
    private InstitutoDTOGuardar institutoDTOGuardar;
    private LaboratorioDTOGuardar laboratorioDTOGuardar;
    private IComputadoraDAO computadoraDAO;
    private Computadora computadoraEntidad;
    private ComputadoraDTO computadoraDTO;
    private ComputadoraDTOGuardar computadoraDTOGuardar;
    private IHorarioDAO horarioDAO;
    private Horario horarioEntidad;
    private HorarioDTO horarioDTO;
    private HorarioDTOGuardar horarioDTOGuardar;
    
    @Before
    public void setUp() {
        em = new EntityManagerDAO();
        reservaDAO = new ReservaDAO(em);
        estudianteDAO = new EstudianteDAO(em);
        carreraDAO = new CarreraDAO(em);
        computadoraDAO = new ComputadoraDAO(em);
        laboratorioDAO = new LaboratorioDAO(em);
        institutoDAO = new InstitutoDAO(em);
        horarioDAO = new HorarioDAO(em);
    }
    @After
    public void tearDown() throws PersistenciaException {
        limpiarRegistros();
        limpiarHorario();
        limpiarComputadora();
        limpiarLaboratorio();
        limpiarInstituto();
        limpiarEstudiantes();
        limpiarCarreras();
    }
    private void limpiarEstudiantes()throws PersistenciaException{
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Estudiante").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarCarreras()throws PersistenciaException{
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Carrera").executeUpdate();

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
    private void limpiarComputadora() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Computadora").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarHorario() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Horario").executeUpdate();

        entity.getTransaction().commit();
    }
    private void limpiarRegistros() throws PersistenciaException {
        EntityManager entity = em.crearEntityManager();
        entity.getTransaction().begin();

        entity.createQuery("DELETE FROM Reserva").executeUpdate();

        entity.getTransaction().commit();
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
    private EstudianteDTO guardarEstudiante()throws PersistenciaException{
        estudianteDTOGuardar = new EstudianteDTOGuardar("00000191748", "EstudianteTest", "ApellidoPTest", "ApellidoMTest", "Alumno12345@", this.guardarCarrera());
        estudianteEtidad = estudianteDAO.guardar(estudianteDTOGuardar);
        return estudianteDTO = estudianteDAO.obtenerDTO(estudianteEtidad.getIdEstudiante());
    }
    private ComputadoraDTO guardarComputadora()throws PersistenciaException{
        computadoraDTOGuardar = new ComputadoraDTOGuardar(1, "102.124.12", this.guardarLaboratorio(), this.guardarCarrera(), "Comun");
        computadoraEntidad = computadoraDAO.guardar(computadoraDTOGuardar);
        return computadoraDTO = computadoraDAO.obtenerDTO(computadoraEntidad.getIdComputadora());
    }
    private HorarioDTO guardarHorario() throws PersistenciaException{
        horarioDTOGuardar = new HorarioDTOGuardar(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), this.guardarLaboratorio());
        horarioEntidad = horarioDAO.guardar(horarioDTOGuardar);
        return horarioDTO = horarioDAO.obtenerDTO(horarioEntidad.getIdHorario());
    }
    @Test
    public void testGuardar() throws Exception {
        System.out.println("guardar");
        ReservaDTOGuardar reserva = new ReservaDTOGuardar(Calendar.getInstance(), 100, this.guardarComputadora(), this.guardarEstudiante(), this.guardarHorario());
        Reserva resultado = reservaDAO.guardar(reserva);
        assertNotNull(resultado);
        assertEquals(100, resultado.getMinutos());
    }
    @Test(expected = PersistenciaException.class)
    public void testGuardarFallo() throws Exception {
        reservaDAO.guardar(null); 
    }
    @Test
    public void testObtenerPorID() throws Exception {
        
        ReservaDTOGuardar reserva = new ReservaDTOGuardar(Calendar.getInstance(), 100, this.guardarComputadora(), this.guardarEstudiante(), this.guardarHorario());
        Reserva reservaGuardada = reservaDAO.guardar(reserva);
        Reserva resultado = reservaDAO.obtenerPorID(reservaGuardada.getIdReserva());
        assertNotNull(resultado);
    }
    @Test(expected = PersistenciaException.class)
    public void testObtenerPorIDFallo() throws Exception {
        
        reservaDAO.obtenerPorID(-1L); 
    }
    @Test
    public void testObtener() throws Exception {
        
        ReservaDTOGuardar reserva = new ReservaDTOGuardar(Calendar.getInstance(), 100, this.guardarComputadora(), this.guardarEstudiante(), this.guardarHorario());
        Reserva reservaGuardada = reservaDAO.guardar(reserva);
        
        List<Reserva> reservas = reservaDAO.obtener();
        assertNotNull(reservas);
        assertTrue(!reservas.isEmpty());
    }
    @Test
    public void testObtenerFallo() throws Exception {
        ReservaDAO instance = new ReservaDAO(new EntityManagerDAO());

        EntityManager em = new EntityManagerDAO().crearEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Reserva").executeUpdate();
        em.getTransaction().commit();

        assertThrows(PersistenciaException.class, () -> {
            instance.obtener();
        });
    } 
}
