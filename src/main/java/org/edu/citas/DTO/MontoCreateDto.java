package org.edu.citas.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MontoCreateDto {


    private Integer pacienteId;
    private float monto;
    private String moneda;
    private Integer anio;

    public Integer getPacienteId() {
        return pacienteId;
    }

    public float getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public Integer getAnio() {
        return anio;
    }

    public Integer getMes() {
        return mes;
    }

    private Integer mes;
}
