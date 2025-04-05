/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModuloAdministracion.Interfaz;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
/**
 *
 * @author Laboratorios
 */
public interface IEntityManager {
    EntityManager crearEntityManager() throws PersistenceException;
}
