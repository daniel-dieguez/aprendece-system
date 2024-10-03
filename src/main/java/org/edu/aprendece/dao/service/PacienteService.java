package org.edu.aprendece.dao.service;

import org.edu.aprendece.dao.IPacienteReposi;
import org.edu.aprendece.dao.implement.IPacienteImpl;
import org.edu.aprendece.models.Agendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PacienteService implements IPacienteImpl{

    @Autowired
    private IPacienteReposi iPacienteReposi;

    @Override
    public List<Agendar> findAll() {
        return this.iPacienteReposi.findAll();
    }

    @Override
    public Agendar findById(Integer id_cita) {
        return this.iPacienteReposi.findById(id_cita).orElse(null);
    }

    @Override
    public Agendar save(Agendar agendar) {
        return this.iPacienteReposi.save(agendar);
    }

    @Override
    public void delete(Agendar agendar) {
        this.iPacienteReposi.delete(agendar);

    }

    public List<Agendar> findByDay(Date fecha) {
        return this.iPacienteReposi.findByDay(fecha);
    }


}
