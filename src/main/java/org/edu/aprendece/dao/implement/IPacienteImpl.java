package org.edu.aprendece.dao.implement;

import org.edu.aprendece.models.Paciente;

import java.util.List;

public interface IPacienteImpl {

    List<Paciente> findAll();
    public Paciente findById(Long dpi_usuario);
    public Paciente save(Paciente paciente);
    public void delete(Paciente paciente);
}
