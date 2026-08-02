package org.edu.citas.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotasCreateDto {
    private Integer pacienteId;
    private String nota;
    private int anio;

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNota() {
        return nota;
    }

    public int getAnio() {
        return anio ;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
