package org.edu.citas.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class CitasCreateDto {


    private Integer pacienteId;
    private LocalDateTime horaCitaInicio;
    private LocalDateTime horaCitaFinal;

    private LocalDateTime fechaCita;

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalDateTime getHoraCitaFinal() {
        return horaCitaFinal;
    }

    public void setHoraCitaFinal(LocalDateTime horaCitaFinal) {
        this.horaCitaFinal = horaCitaFinal;
    }

    public LocalDateTime getHoraCitaInicio() {
        return horaCitaInicio;
    }

    public void setHoraCitaInicio(LocalDateTime horaCitaInicio) {
        this.horaCitaInicio = horaCitaInicio;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }
    
}
