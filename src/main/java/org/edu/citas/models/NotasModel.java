package org.edu.citas.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "notas", schema = "adm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotasModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pacienteId")
    private int pacienteId;

    @Column(name = "nota")
    private String nota;

    @Column(name = "estado")
    private int estado;

    @Column(name = "creado")
    private LocalDate creado;

}
