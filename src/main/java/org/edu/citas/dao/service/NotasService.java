package org.edu.citas.dao.service;

import org.edu.citas.dao.INotaRepo;
import org.edu.citas.dao.implement.INotasImpl;
import org.edu.citas.Models.NotasModel;
import org.edu.citas.DTO.InotasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class NotasService implements INotasImpl {

    @Autowired
    private INotaRepo notaRepo;


    @Override
    public List<NotasModel> findAllNotas() {
        return List.of();
    }

    public List<InotasDto> AllNotas() {
        return this.notaRepo.AllNotas();
    }

    public NotasModel createNota(NotasModel notasModel) {
        notasModel.setCreado(LocalDate.now());
        return notaRepo.save(notasModel);

    }

    public Optional oneNotasXPersona(int pacienteId){
        return this.notaRepo.oneNotas(pacienteId);

    }

    public Optional oneNotas(int id){
        return this.notaRepo.oneNotas(id);

    }
}
