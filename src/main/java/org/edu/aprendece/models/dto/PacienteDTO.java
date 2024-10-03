package org.edu.aprendece.models.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO implements Serializable {


    private Integer id_cita;
   @NotNull(message = "fecha")
    private Date fecha ;
    @NotNull(message = "hora_cita")
    private LocalTime hora_cita;
    @NotNull(message = "El DPI no puede ser nulo")
    private Long dpi_usuario;
    @NotEmpty(message = "debes de colocar un nombre de usuario ")
    private String nombre_usuario;
    @NotNull(message = "El numeoro de usuario debes de agregar")
    private Long numero_usuario;
    @NotNull(message = "debes de color la edad del usuario")
    private int edad_usuario;
    @Email(message = "debes de agregar el correo del paciente")
    private String correo_usuario;

}
