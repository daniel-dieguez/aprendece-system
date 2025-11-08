package org.edu.citas.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Usuario", schema = "adm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat
    private Integer id;

    private String nombre;
    @JsonFormat
    private Integer edad;
    @JsonFormat
    private String correo;
    @JsonFormat
    private String direccion;
    @JsonFormat
    private String telefono;
    @JsonFormat
    private Integer tipoUsuario;
    @JsonFormat
    private Integer estado;

    // Si quieres formatear la fecha en el JSON:
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creado;

}
//