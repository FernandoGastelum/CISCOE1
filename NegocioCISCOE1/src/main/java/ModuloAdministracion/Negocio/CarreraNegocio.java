/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAdministracion.Negocio;

import DTOs.CarreraDTO;
import DTOs.CarreraDTOGuardar;
import Entidades.Carrera;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ICarreraDAO;
import ModuloAdministracion.Interfaz.ICarreraNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public class CarreraNegocio implements ICarreraNegocio {
    private final ICarreraDAO carreraDAO;

    public CarreraNegocio(ICarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    @Override
    public CarreraDTO guardar(CarreraDTOGuardar carrera) throws NegocioException {
        try {
            this.reglasNegocioGuardar(carrera);
            Carrera carreraGuardado = this.carreraDAO.guardar(carrera);
            return this.carreraDAO.obtenerDTO(carreraGuardado.getIdCarrera());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<CarreraDTO> obtener() throws NegocioException {
        try {
            List<Carrera> listaCarreras = this.carreraDAO.obtener();
            List<CarreraDTO> dtos = new ArrayList<>();
            for (Carrera carrera : listaCarreras) {
                dtos.add(this.carreraDAO.obtenerDTO(carrera.getIdCarrera()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public CarreraDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return carreraDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }
    
    private boolean reglasNegocioGuardar(CarreraDTOGuardar carrera) throws NegocioException {
        if (carrera.getNombreCarrera() == null) {
            throw new NegocioException("El nombre no puede estar vacío");
        }
        if (carrera.getTiempoMaximoDiario() == null) {
            throw new NegocioException("El tiempo máximo diario no puede estar vacía");
        }
        if (carrera.getColor() == null) {
            throw new NegocioException("El color no puede estar vacía");
        }
        return true;
    }
}
