package org.edu.citas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario", schema = "adm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaModel implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tipoUsuario")
    private Integer tipoUsuario;

    @Column(name = "estado")
    private Integer estado;
    



    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "creado")
    private LocalDate creado;




}
