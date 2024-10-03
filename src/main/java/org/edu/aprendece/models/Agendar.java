package org.edu.aprendece.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendar")
@Data
public class Agendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer id_cita;
    @Column(name = "fecha")
    private Date fecha ;
    @Column(name = "hora_cita")
    private LocalTime hora_cita;
    @Column(name = "dpi_usuario")
    private Long dpi_usuario;
    @Column(name = "nombre_usuario")
    private String nombre_usuario;
    @Column (name = "numero_usuario")
    private long numero_usuario;
    @Column(name = "edad_usuario")
    private int edad_usuario;
    @Column(name = "correo_usuario")
    private String correo_usuario;

}
