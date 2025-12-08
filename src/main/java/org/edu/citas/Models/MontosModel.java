package org.edu.citas.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "montos", schema = "adm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MontosModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat
    private int id;

    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private PersonaModel pacienteModel;

     @JoinColumn
    private float monto;

//     @JoinColumn
//    private String moneda;

    @JoinColumn
    private int  estado;

    @JoinColumn
    private int  anio;

    @JoinColumn
    private int  mes;

    @JoinColumn
    private LocalDate creado;



    public void setPacienteModel(PersonaModel pacienteModel) {
        this.pacienteModel = pacienteModel;
    }


}
