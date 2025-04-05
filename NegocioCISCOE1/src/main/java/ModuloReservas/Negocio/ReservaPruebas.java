/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloReservas.Negocio;

import DTOs.ReservaDTOGuardar;
import Entidades.Computadora;
import Entidades.Estudiante;
import Entidades.Horario;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IComputadoraDAO;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IEstudianteDAO;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Persistencia.ComputadoraDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.EstudianteDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
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
    public IEntityManager entityManager;
    public ReservaPruebas(IReservaNegocio reservaNegocio){
        this.reservaNegocio = reservaNegocio;
        this.entityManager =  new EntityManagerDAO();
   }
    public void registrarPrueba(){
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
        ReservaPruebas prueba = new ReservaPruebas(reservaNegocio);
        prueba.registrarPrueba();
    }
}
