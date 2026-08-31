package org.edu.citas.dao.service;

import org.edu.citas.dao.IPersonaRepo;
import org.edu.citas.dao.implement.IPersonaImpl;
import org.edu.citas.Models.PersonaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;

@Service
public class PersonaService implements IPersonaImpl {

    @Autowired
    private IPersonaRepo PersonaRepo;


    Pageable pageable = (Pageable) PageRequest.of(0, 15); // paginacion

    @Override
    public List<PersonaModel> findAllCitas() { // esta no sirve de nada
        return List.of();
    }

    public List<PersonaModel> AllP() {
        return this.PersonaRepo.AllPersonas();
    }

    public List<PersonaModel> AllPacientes(int anio, int page) {

        Pageable pageable = PageRequest.of(page, 15);

        return this.PersonaRepo.AllPacientes(anio, pageable);
    }

public List<PersonaModel> pacientePormes( int anio, int mes) {
        return this.PersonaRepo.pacientesPorAnioYMes(anio, mes);
    }

    public Long totalPacientesAnual( int anio) {
        return this.PersonaRepo.totalPacientesAnual(anio);
    }
    public Long totalPacientesMensual( int anio, int mes) {
        return this.PersonaRepo.totalPacientesMesual(anio, mes);
    }


    public PersonaModel crearPersona(PersonaModel persona) {
        persona.setCreado(LocalDate.now());  // Establecer fecha actual (sin hora, solo fecha)
        return PersonaRepo.save(persona);
    }


}
