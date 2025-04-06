/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Negocio;

import DTOs.ComputadoraDTOGuardar;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.EstudianteDTOGuardar;
import DTOs.ReservaDTOGuardar;
import Entidades.Carrera;
import Entidades.Computadora;
import Entidades.Estudiante;
import Entidades.Horario;
import Entidades.Laboratorio;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IComputadoraNegocio;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IEstudianteNegocio;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Negocio.ComputadoraNegocio;
import ModuloAdministracion.Negocio.EstudianteNegocio;
import ModuloAdministracion.Persistencia.CarreraDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import ModuloReservas.Interfaz.IReservaDAO;
import ModuloReservas.Interfaz.IReservaNegocio;
import ModuloReservas.Persistencia.ReservaDAO;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaspa
 */
public class ReservaPruebas {
    private final IReservaNegocio reservaNegocio;
    private final IEstudianteNegocio estudianteNegocio;
    private final IComputadoraNegocio computadoraNegocio;
    public IEntityManager entityManager;
    public ReservaPruebas(IReservaNegocio reservaNegocio,IEstudianteNegocio estudianteNegocio, IComputadoraNegocio computadoraNegocio){
        this.estudianteNegocio = estudianteNegocio;
        this.computadoraNegocio = computadoraNegocio;
        this.reservaNegocio = reservaNegocio;
        this.entityManager =  new EntityManagerDAO();
   }
    public void registrarReserva(){
        try {
            ReservaDTOGuardar reservaDTO = new ReservaDTOGuardar(
                    Calendar.getInstance(),
                    this.horaInicioReserva(),
                    this.obtenerComputadora(1L),
                    this.obtenerEstudiante(1L),
                    this.obtenerHorario(1L));
            reservaNegocio.guardar(reservaDTO);
        } catch (NegocioException | PersistenciaException ex) {
            System.out.println("Error: "+ ex.getMessage());
        }
    }
    public EstudianteDTO registrarEstudiante() throws NegocioException{
        try {
            EstudianteDTOGuardar estudianteDTO = new EstudianteDTOGuardar("Bethlehem", "hola", "como estas", "1234566", this.obtenerCarrera(1L));
            return estudianteNegocio.guardar(estudianteDTO);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    public ComputadoraDTO registrarComputadora() throws NegocioException{
        try {
            ComputadoraDTOGuardar computadoraDTO = new ComputadoraDTOGuardar(4, "192.1.2.245", this.obtenerLab(1L), this.obtenerCarrera(1L));
            return computadoraNegocio.guardar(computadoraDTO);
        } catch (PersistenciaException | NegocioException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    public Laboratorio obtenerLab(Long id) throws PersistenciaException{
        try {
            ILaboratorioDAO laboratorioDAO = new LaboratorioDAO(entityManager);
            return laboratorioDAO.obtenerPorID(id);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("No se encontro una laboratorio con el id proporcionado");
        }
    }
    public Carrera obtenerCarrera(Long id) throws PersistenciaException{
        try {
            ICarreraDAO carreraDAO = new CarreraDAO(entityManager);
            return carreraDAO.obtenerPorID(id);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("No se encontro una carrera con el id proporcionado");
        }
    }
    public Computadora obtenerComputadora(Long id) throws PersistenciaException{
        try {
            IComputadoraDAO computadoraDAO = new ComputadoraDAO(entityManager);
            return computadoraDAO.obtenerPorID(1L);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException("No se encontro una computadora con el id proporcionado");
        }
    }
    public Estudiante obtenerEstudiante(Long id) throws PersistenciaException{
        try {
            IEstudianteDAO estudianteDAO = new EstudianteDAO(entityManager);
            return estudianteDAO.obtenerPorID(1L);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException("No se encontro una estudiante con el id proporcionado");
        }
    }
    public Horario obtenerHorario(Long id) throws PersistenciaException{
        try {
            IHorarioDAO horarioDAO = new HorarioDAO(entityManager);
            return horarioDAO.obtenerPorID(1L);
        } catch (PersistenciaException ex) {
            throw new PersistenciaException("No se encontro un horario con el id proporcionado");
        }
    }
    public Calendar horaInicioReserva(){
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 12);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }
    public static void main(String[] args) {
        IEntityManager entityManager = new EntityManagerDAO();
        IReservaDAO reservaDAO = new ReservaDAO(entityManager);
        IReservaNegocio reservaNegocio = new ReservaNegocio(reservaDAO);
        IEstudianteDAO estudianteDAO = new EstudianteDAO(entityManager);
        IEstudianteNegocio estudianteNegocio = new EstudianteNegocio(estudianteDAO);
        IComputadoraDAO computadoraDAO = new ComputadoraDAO(entityManager);
        IComputadoraNegocio computadoraNegocio = new ComputadoraNegocio(computadoraDAO);
        ReservaPruebas prueba = new ReservaPruebas(reservaNegocio,estudianteNegocio, computadoraNegocio);
        
        try {
            prueba.registrarComputadora();
        } catch (NegocioException ex) {
            System.out.println("error "+ex.getMessage());
        }
    }
}
