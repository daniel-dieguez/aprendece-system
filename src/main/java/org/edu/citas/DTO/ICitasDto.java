package org.edu.citas.DTO;


import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ICitasDto {

    Integer getIdUsuario();

    LocalDate getfechaCita();
    LocalDateTime gethoraCitaInicio();
    LocalDateTime gethoraCitaFin();
    String getEstado ();


}
