package org.edu.aprendece.dao.implement;

import org.edu.aprendece.models.Paciente;

import java.util.List;

public interface IPacienteImpl {

    List<Paciente> findAll();
    public Paciente findById(Long dpi_paciente);
    public Paciente save(Paciente paciente);
    public void delete(Paciente paciente);
}
