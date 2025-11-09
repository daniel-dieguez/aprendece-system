package org.edu.citas.dao.service;


import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.Models.CitasModel;
import org.edu.citas.dao.ICitasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitasService {


    @Autowired
    private ICitasRepo citasRepo;

    public List<ICitasDto> AllCitas(){
        return this.citasRepo.AllCitas();
    }

    public CitasModel createCita(CitasModel citaModal){
        citaModal.setCreado(LocalDate.now());
        return this.citasRepo.save(citaModal);

    }


}
