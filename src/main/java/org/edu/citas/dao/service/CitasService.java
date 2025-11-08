package org.edu.citas.dao.service;


import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.dao.ICitasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitasService {


    @Autowired
    private ICitasRepo citasRepo;

    public List<ICitasDto> AllCitas(){
        return this.citasRepo.AllCitas();
    }


}
