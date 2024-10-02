package org.edu.aprendece.dao.service;

import org.edu.aprendece.dao.IPacienteReposi;
import org.edu.aprendece.dao.implement.IPacienteImpl;
import org.edu.aprendece.models.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteImpl {

    @Autowired
    private IPacienteReposi iPacienteReposi;

    @Override
    public List<Paciente> findAll() {
        return this.iPacienteReposi.findAll();
    }

    @Override
    public Paciente findById(Long dpi_paciente) {
        return this.iPacienteReposi.findById(String.valueOf(dpi_paciente)).orElse(null);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return this.iPacienteReposi.save(paciente);
    }

    @Override
    public void delete(Paciente paciente) {
        this.iPacienteReposi.delete(paciente);

    }
}
