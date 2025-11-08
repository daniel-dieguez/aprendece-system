package org.edu.citas.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "citas", schema = "adm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitasModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat
    private int id;

    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private PersonaModel pacienteModel;

    @JsonFormat
    private Date fechaCita;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate horaCitaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate horaCitaFin;

    @JsonFormat
    private int estado;


}
