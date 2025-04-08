/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.HorarioDTOGuardar;
import DTOs.InstitutoDTOGuardar;
import DTOs.LaboratorioDTO;
import Entidades.Horario;
import Entidades.Instituto;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IHorarioDAO;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.HorarioDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author gaspa
 */
public class RegistrarEntidadesTest {

    public IEntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = new EntityManagerDAO();
    }

    public void registrarInstituto() {
        IInstitutoDAO institutoDAO = new InstitutoDAO(entityManager);
        InstitutoDTOGuardar institutoDTO = new InstitutoDTOGuardar("Instituto Tecnologico de Sonora", "ITSON");
        try {
            Instituto institutoEntiad = institutoDAO.guardar(institutoDTO);
            assertEquals("ITSON", institutoDAO.obtenerPorID(institutoEntiad.getIdInstituto()).getNombreAbreviado());
        } catch (PersistenciaException ex) {
            Logger.getLogger(RegistrarEntidadesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarHorario() {
        IHorarioDAO horarioDAO = new HorarioDAO(entityManager);
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
        laboratorioDTO.setIdLaboratorio(1L);
        HorarioDTOGuardar horarioDTO = new HorarioDTOGuardar(this.gethoraInicio(), this.gethoraCierre(), Calendar.getInstance(), laboratorioDTO);
        try {
            Horario horarioEntidad = horarioDAO.guardar(horarioDTO);
            assertEquals(this.gethoraInicio(), horarioDAO.obtenerPorID(horarioEntidad.getIdHorario()).getHoraApertura());
        } catch (PersistenciaException ex) {
            Logger.getLogger(RegistrarEntidadesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Calendar gethoraInicio() {
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 10);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }

    public Calendar gethoraCierre() {
        Calendar hora = Calendar.getInstance();
        hora.set(Calendar.HOUR_OF_DAY, 22);
        hora.set(Calendar.MINUTE, 0);
        hora.set(Calendar.SECOND, 0);
        hora.set(Calendar.MILLISECOND, 0);
        return hora;
    }

    public void registrarComputadora() {

    }

}
