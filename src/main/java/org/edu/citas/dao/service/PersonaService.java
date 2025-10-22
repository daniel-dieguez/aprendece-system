package org.edu.citas.dao.service;

import org.edu.citas.dao.IPersonaRepo;
import org.edu.citas.dao.implement.IPersonaImpl;
import org.edu.citas.models.PersonaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonaService implements IPersonaImpl {

    @Autowired
    private IPersonaRepo repo;

    @Override
    public List<PersonaModel> findAllCitas() { // esta no sirve de nada
        return List.of();
    }

    public List<PersonaModel> AllPacientes() {
        return this.repo.AllPacientes();
    }

    public List<PersonaModel> findByDate(LocalDate fecha){
        return this.repo.findByDate(fecha);
    }


}
