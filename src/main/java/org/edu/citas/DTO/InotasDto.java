package org.edu.citas.DTO;

import java.time.LocalDate;

public interface InotasDto {


    Integer getIdUsuario();
    String getNombre();
    Integer getEstado();
    String getNota();
    LocalDate getCreado();


}
