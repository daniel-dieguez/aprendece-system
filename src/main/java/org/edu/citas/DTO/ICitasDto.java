package org.edu.citas.DTO;


import java.time.LocalDate;

public interface ICitasDto {

    Integer getIdUsuario();
    LocalDate getInicioCita();
    LocalDate getFinalCita();
    String getEstado ();


}
