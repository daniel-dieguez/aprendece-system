package org.edu.citas.dao;


import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.Models.CitasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICitasRepo extends JpaRepository<CitasModel, Integer> {

    @Query("""
SELECT c.id as id,  
c.pacienteModel.nombre as nombre
, c.pacienteModel.id as idUsuario
, c.horaCitaInicio as horaCitaInicio
, c.horaCitaFin as horaCitaFin, 
    c.estado as estado FROM CitasModel c
    """)
    List<ICitasDto> AllCitas();


}
