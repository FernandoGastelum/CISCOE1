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
    
    private Calendar fechaReserva;
    private Calendar horaInicio;
    private Calendar horaFin;
    private ComputadoraDTO computadoraDTO;
    private EstudianteDTO estudianteDTO;
    private HorarioDTO horario;

    /**
     * Constructor por ausencia
     */
    public ReservaDTOGuardar() {
    }

    public ReservaDTOGuardar(Calendar fechaReserva, Calendar horaInicio, ComputadoraDTO computadoraDTO, EstudianteDTO estudianteDTO, HorarioDTO horario) {
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.computadoraDTO = computadoraDTO;
        this.estudianteDTO = estudianteDTO;
        this.horario = horario;
    }

    public Calendar getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Calendar fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Calendar getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Calendar horaFin) {
        this.horaFin = horaFin;
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
        return "ReservaDTOGuardar{" + "fechaReserva=" + fechaReserva + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", computadoraDTO=" + computadoraDTO + ", estudianteDTO=" + estudianteDTO + ", horario=" + horario + '}';
    }
    
    
    
    
}
