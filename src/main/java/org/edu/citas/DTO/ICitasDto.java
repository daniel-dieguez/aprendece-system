package org.edu.citas.DTO;


import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ICitasDto {

    Integer getIdUsuario();
    String getNombre();
    LocalDateTime getFechaCita();
    LocalDateTime getHoraCitaInicio();
    LocalDateTime getHoraCitaFin();
    Integer getEstado();
    Integer getAnio();
    Integer getMes();
}
