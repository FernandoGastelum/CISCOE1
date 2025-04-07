package Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import DTOs.CarreraDTOGuardar;
import DTOs.ComputadoraDTO;
import DTOs.ComputadoraDTOGuardar;
import DTOs.EstudianteDTOGuardar;
import DTOs.LaboratorioDTOGuardar;
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
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Persistencia.ReservaDAO;
import java.util.Calendar;
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
    public void agregarEstudiante(){
        try {
            IEstudianteDAO IEstudianteDAO = new EstudianteDAO(entityManager);
            ICarreraDAO iCarreraDAO = new CarreraDAO(entityManager);
            CarreraDTOGuardar carreraDTO = new CarreraDTOGuardar("Software", 180, "Rojo");
            Carrera carreraEntidad = iCarreraDAO.guardar(carreraDTO);
            EstudianteDTOGuardar guardar = new EstudianteDTOGuardar("00000887621","Estudiante", "Paterno", "Materno", "12345", carreraEntidad);
            Estudiante estudianteEntidad = IEstudianteDAO.guardar(guardar);
            assertEquals("Estudiante", IEstudianteDAO.obtenerPorID(estudianteEntidad.getIdEstudiante()).getNombre());
        } catch (PersistenciaException ex) {
            Logger.getLogger(PersistenciaTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void agregarLaboratorio() {
        try {
            ILaboratorioDAO iLaboratorioDAO = new LaboratorioDAO(entityManager);
            Instituto institutoEntidad = new Instituto("Instituto Tecnologico de Sonora", "ITSON");
            LaboratorioDTOGuardar laboratorioDTO = new LaboratorioDTOGuardar("CISCO", this.horaInicio(), this.horaCierre(), "Maestra12345", institutoEntidad);
            Laboratorio laboratorioEntidad = iLaboratorioDAO.guardar(laboratorioDTO);
            assertEquals("CISCO", iLaboratorioDAO.obtenerPorID(laboratorioEntidad.getIdLaboratorio()).getNombre());
        } catch (PersistenciaException ex) {
            Logger.getLogger(PersistenciaTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarComputadora(){
        try {
            IComputadoraDAO IComputadoraDAO = new ComputadoraDAO(entityManager);
            Instituto institutoEntidad = new Instituto("Instituto Tecnologico de Sonora", "ITSON");
            Laboratorio laboratorioEntidad = new Laboratorio("CISCO", this.horaInicio(), this.horaCierre(), "Maestra12345", institutoEntidad);
            Carrera carreraEntidad = new Carrera("Ingenieria en Sistemas", 300, "Azul");
            ComputadoraDTOGuardar computadoraDTO = new ComputadoraDTOGuardar(1, "192.0.1.5", laboratorioEntidad, carreraEntidad);
            Computadora computadoraEntidad = IComputadoraDAO.guardar(computadoraDTO);
            assertEquals("192.0.1.5", IComputadoraDAO.obtenerPorID(computadoraEntidad.getIdComputadora()).getDireccionIp());
        } catch (PersistenciaException e) {
            Logger.getLogger(PersistenciaTEST.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void agregarCarrera(){
        ICarreraDAO carreraDAO = new CarreraDAO(entityManager);
        CarreraDTOGuardar carreraDTO = new CarreraDTOGuardar("ISW", 350, "ROJO");
        try {
            Carrera carreraEntidad = carreraDAO.guardar(carreraDTO);
            assertEquals("ISW", carreraEntidad.getNombreCarrera());
        } catch (PersistenciaException ex) {
            Logger.getLogger(PersistenciaTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Calendar horaInicio(){
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 10);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }
    public Calendar horaCierre(){
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 22);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }
    public Calendar horaInicioReserva(){
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 12);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }
    public Calendar horaCierreReserva(){
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 22);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }
    
    //@Test
//    public void agregarReserva(){
//        try {
//            IReservaDAO reservaDAO = new ReservaDAO(entityManager);
//            
//            Instituto institutoEntidad = new Instituto("Instituto Tecnologico de Sonora", "ITSON");
//            Laboratorio laboratorioEntidad = new Laboratorio("CISCO", this.horaInicio(), this.horaCierre(), "Maestra12345", institutoEntidad);
//            Carrera carreraEntidad = new Carrera("Ingenieria en Sistemas", 300, "Azul");
//            Estudiante estudianteEntidad = new Estudiante("00000223521","Estudiante", "Paterno", "Materno", "12345", carreraEntidad);
//            Computadora computadoraEntidad = new Computadora(1, "192.0.1.5", laboratorioEntidad, carreraEntidad);
//            Horario horarioEntidad = new Horario(this.horaInicio(), this.horaCierre(), Calendar.getInstance(), laboratorioEntidad);
//            ReservaDTOGuardar reservaDTO = new ReservaDTOGuardar(Calendar.getInstance(), this.horaInicioReserva(), computadoraEntidad, estudianteEntidad, horarioEntidad);
//            Reserva reservaEntidad = reservaDAO.guardar(reservaDTO);
//            
//            assertEquals("192.0.1.5", reservaDAO.obtenerPorID(reservaEntidad.getIdReserva()).getComputadora().getDireccionIp());
//        } catch (PersistenciaException ex) {
//            Logger.getLogger(PersistenciaTEST.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    
}
