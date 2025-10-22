package org.edu.citas.dao.service;

import org.edu.citas.dao.INotaRepo;
import org.edu.citas.dao.IPersonaRepo;
import org.edu.citas.dao.implement.INotasImpl;
import org.edu.citas.models.NotasModel;
import org.edu.citas.models.PersonaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotasService implements INotasImpl {



    @Autowired
    private INotaRepo NotaRepo;


    @Override
    public List<NotasModel> findAllNotas() {
        return List.of();
    }

    public List<NotasModel> AllNotas() {
        return this.NotaRepo.AllNotas();
    }
}
