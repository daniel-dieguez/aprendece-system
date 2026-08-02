package org.edu.citas.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    public void setId(Integer id) {
//        this.id = id;
//    }

    @JsonFormat
    private String nombre;
    @JsonFormat
    private Integer edad;
    @JsonFormat
    private String correo;
    @JsonFormat
    private String pais;
    @JsonFormat
    private String direccion;

    @JsonFormat
    private String motivo;
    @JsonFormat
    private String telefono;

    @JsonFormat
    private Integer tipoUsuario;

    @JsonFormat
    private Integer estado;


    @JsonFormat
    private Integer mes ;

    @JsonFormat
    private Integer anio;

    // Si quieres formatear la fecha en el JSON:
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creado;
    public void setCreado(LocalDate creado) {
        this.creado = creado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoUsuario", insertable = false, updatable = false)
    @JsonIgnore
    private TipoUsuarioModel tipoUsuarios;



}
//