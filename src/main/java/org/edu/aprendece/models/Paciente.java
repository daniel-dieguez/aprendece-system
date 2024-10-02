package org.edu.aprendece.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente")
@Data
public class Paciente implements Serializable {

    @Id
    @Column(name = "dpi_usuario")
    private Long dpi_usuario;
    @Column(name = "nombre_usuario")
    private String nombre_usuario;
    @Column(name = "edad_usuario")
    private String edad_usuario;
    @Column(name = "correo_usuario")
    private String correo_usuario;

}
