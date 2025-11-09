package org.edu.citas.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "notas", schema = "adm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotasModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat
    private int id;


    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private PersonaModel pacienteModel;

    public void setId(int id) {
        this.id = id;
    }

    public void setPacienteModel(PersonaModel pacienteModel) {
        this.pacienteModel = pacienteModel;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @JsonFormat
    private String nota;

    @JsonFormat
    private int estado;

    private LocalDate creado;
    public void setCreado(LocalDate creado) {
        this.creado = creado;
    }


}
