package org.edu.aprendece.dao;


import org.edu.aprendece.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteReposi extends JpaRepository<Paciente, String> {

}
