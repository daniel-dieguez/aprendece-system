package org.edu.citas.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaCita;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime horaCitaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime horaCitaFin;

    @JsonFormat
    private int estado;

    @JoinColumn
    private int  anio;

    @JoinColumn
    private int  mes;

    @JoinColumn
    private int  dia;

    private LocalDate creado;


    public void setId(int id) {
        this.id = id;
    }

    public void setPacienteModel(PersonaModel pacienteModel) {
        this.pacienteModel = pacienteModel;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public void setHoraCitaInicio(LocalDateTime horaCitaInicio) {
        this.horaCitaInicio = horaCitaInicio;
    }

    public void setHoraCitaFin(LocalDateTime horaCitaFin) {
        this.horaCitaFin = horaCitaFin;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setCreado(LocalDate creado) {
        this.creado = creado;
    }
}
