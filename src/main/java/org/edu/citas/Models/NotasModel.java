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

    @JsonFormat
    private String nota;

    @JsonFormat
    private int estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creado;


}
