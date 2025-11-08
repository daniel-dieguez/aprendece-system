package org.edu.citas.dao.service;

import org.edu.citas.dao.INotaRepo;
import org.edu.citas.dao.implement.INotasImpl;
import org.edu.citas.Models.NotasModel;
import org.edu.citas.DTO.InotasDto;
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

    public List<InotasDto> AllNotas() {
        return this.NotaRepo.AllNotas();
    }
}
