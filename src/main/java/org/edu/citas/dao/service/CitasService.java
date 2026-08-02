package org.edu.citas.dao.service;


import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.Models.CitasModel;
import org.edu.citas.dao.ICitasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CitasService {


    @Autowired
    private ICitasRepo citasRepo;

    public List<ICitasDto> AllCitas(int anio){
        return this.citasRepo.AllCitas(anio);
    }

    public List<ICitasDto> citasMensuales(int anio, int mes){
        return this.citasRepo.CitasMensuales(anio, mes);
    }

    public List<ICitasDto> citasdiarias(int anio, int mes, int dia){
        return this.citasRepo.CitasDiarias(anio, mes, dia);
    }

    public Long citasTotaldiarias(int anio, int mes, int dia){
        return this.citasRepo.CitasTotalDia(anio, mes, dia);
    }

    public Long citasTotalesMensuales(int anio, int mes){
        return this.citasRepo.CitasTotalMensuales(anio, mes);
    }

    public Long citasTotalesAnuales(int anio){
        return this.citasRepo.CitasTotalAnuales(anio);
    }

    public CitasModel createCita(CitasModel citaModal){
        citaModal.setCreado(LocalDate.now());
        return this.citasRepo.save(citaModal);

    }


}
