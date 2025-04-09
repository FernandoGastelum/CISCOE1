/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;
import java.util.Calendar;

/**
 *
 * @author Knocmare
 */
public class ReservaDTOGuardar {
    
    private Calendar horaInicio;
    private int minutos;
    private ComputadoraDTO computadoraDTO;
    private EstudianteDTO estudianteDTO;
    private HorarioDTO horario;

    /**
     * Constructor por ausencia
     */
    public ReservaDTOGuardar() {
    }

    public ReservaDTOGuardar(Calendar horaInicio,int minutos, ComputadoraDTO computadoraDTO, EstudianteDTO estudianteDTO, HorarioDTO horario) {
        this.horaInicio = horaInicio;
        this.minutos = minutos;
        this.computadoraDTO = computadoraDTO;
        this.estudianteDTO = estudianteDTO;
        this.horario = horario;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ComputadoraDTO getComputadoraDTO() {
        return computadoraDTO;
    }

    public void setComputadoraDTO(ComputadoraDTO computadoraDTO) {
        this.computadoraDTO = computadoraDTO;
    }

    public EstudianteDTO getEstudianteDTO() {
        return estudianteDTO;
    }

    public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
        this.estudianteDTO = estudianteDTO;
    }

    public HorarioDTO getHorario() {
        return horario;
    }

    public void setHorario(HorarioDTO horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "ReservaDTOGuardar{" + "horaInicio=" + horaInicio + ", computadoraDTO=" + computadoraDTO + ", estudianteDTO=" + estudianteDTO + ", horario=" + horario + '}';
    }
    
    
    
    
}
