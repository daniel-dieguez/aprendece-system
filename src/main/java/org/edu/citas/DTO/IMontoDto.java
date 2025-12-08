package org.edu.citas.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IMontoDto {
    Integer getId();
    Integer getIdUsuario();
    String getNombre();
    int getEstado();
    float getMonto();
    LocalDate getCreado();
    Integer getAnio();
    Integer getMes();
    LocalDate getActualizado();
}
