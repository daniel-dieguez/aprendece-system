package org.edu.aprendece.models.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO implements Serializable {


    @NotNull(message = "El DPI no puede ser nulo")
    private Long dpi_usuario;
    @NotEmpty(message = "debes de colocar un nombre de usuario ")
    private String nombre_usuario;
    @NotEmpty(message = "debes de color la edad del usuario")
    private String edad_usuario;
    @NotEmpty(message = "debes de agregar el correo del paciente")
    private String correo_usuario;

}
