/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import DTOs.InstitutoDTOGuardar;
import Entidades.Instituto;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.IEntityManager;
import ModuloAdministracion.Interfaz.IInstitutoDAO;
import ModuloAdministracion.Persistencia.EntityManagerDAO;
import ModuloAdministracion.Persistencia.InstitutoDAO;
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
public class InstitutoDAOTest {
    
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
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
