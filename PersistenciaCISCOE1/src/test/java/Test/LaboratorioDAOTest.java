/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.LaboratorioDTOEditar;
import DTOs.LaboratorioDTOGuardar;
import Entidades.Instituto;
import Entidades.Laboratorio;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Persistencia.LaboratorioDAO;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.gen5.api.AfterEach;
import org.junit.gen5.api.BeforeEach;
/**
 *
 * @author gaspa
 */
public class LaboratorioDAOTest {
    
    
}
