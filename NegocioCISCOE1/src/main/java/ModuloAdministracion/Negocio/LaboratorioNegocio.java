package ModuloAdministracion.Negocio;

import DTOs.LaboratorioDTO;
import DTOs.LaboratorioDTOGuardar;
import DTOs.LaboratorioTablaDTO;
import Entidades.Laboratorio;
import Excepcion.NegocioException;
import Excepcion.PersistenciaException;
import ModuloAdministracion.Interfaz.ILaboratorioDAO;
import ModuloAdministracion.Interfaz.ILaboratorioNegocio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Knocmare
 */
public class LaboratorioNegocio implements ILaboratorioNegocio {

    private final ILaboratorioDAO laboratorioDAO;

    public LaboratorioNegocio(ILaboratorioDAO laboratorioDAO) {
        this.laboratorioDAO = laboratorioDAO;
    }

    @Override
    public LaboratorioDTO guardar(LaboratorioDTOGuardar laboratorio) throws NegocioException {
        try {
            this.reglasNegocioGuardar(laboratorio);
            Laboratorio laboratorioGuardado = this.laboratorioDAO.guardar(laboratorio);
            return this.laboratorioDAO.obtenerDTO(laboratorioGuardado.getIdLaboratorio());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<LaboratorioDTO> obtener() throws NegocioException {
        try {
            List<Laboratorio> listaLaboratorios = this.laboratorioDAO.obtener();
            List<LaboratorioDTO> dtos = new ArrayList<>();
            for (Laboratorio laboratorio : listaLaboratorios) {
                dtos.add(this.laboratorioDAO.obtenerDTO(laboratorio.getIdLaboratorio()));
            }
            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    @Override
    public List<LaboratorioTablaDTO> obtenerTabla() throws NegocioException {
        try {
            List<Laboratorio> listaLaboratorios = this.laboratorioDAO.obtener();
            List<LaboratorioDTO> dtos = new ArrayList<>();
            for (Laboratorio laboratorio : listaLaboratorios) {
                dtos.add(this.laboratorioDAO.obtenerDTO(laboratorio.getIdLaboratorio()));
            }
            return this.convertirTablaDTO(dtos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private List<LaboratorioTablaDTO> convertirTablaDTO(List<LaboratorioDTO> laboratorios) {
        if (laboratorios == null) {
            return null;
        }
        List<LaboratorioTablaDTO> laboratoriosDTO = new ArrayList<>();
        for (LaboratorioDTO laboratorio : laboratorios) {
            LaboratorioTablaDTO dato = new LaboratorioTablaDTO(laboratorio.getIdLaboratorio(), laboratorio.getNombre(), laboratorio.getHoraApertura(), laboratorio.getHoraCierre());
            laboratoriosDTO.add(dato);
        }
        return laboratoriosDTO;
    }

    @Override
    public LaboratorioDTO obtenerPorID(Long id) throws NegocioException {
        try {
            return laboratorioDAO.obtenerDTO(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error " + ex.getMessage());
        }
    }

    private boolean reglasNegocioGuardar(LaboratorioDTOGuardar laboratorio) throws NegocioException {
        if (laboratorio.getNombre() == null) {
            throw new NegocioException("El nombre no puede estar vacío");
        }
        if (laboratorio.getHoraApertura() == null) {
            throw new NegocioException("La hora de apertura no puede estar vacía");
        }
        if (laboratorio.getHoraCierre() == null) {
            throw new NegocioException("La hora de cierre no puede estar vacía");
        }
        if (laboratorio.getContrasenaMaestra() == null) {
            throw new NegocioException("La contraseña maestra no puede estar vacía");
        }
        if (laboratorio.getInstitutoDTO() == null) {
            throw new NegocioException("El instituto no puede estar vacío");
        }
        return true;
    }

}
