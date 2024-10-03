package org.edu.aprendece.dao;


import org.edu.aprendece.models.Agendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IPacienteReposi extends JpaRepository<Agendar, Integer> {


    @Query("SELECT p FROM Agendar p WHERE DATE(p.fecha) = :fecha")
    List<Agendar> findByDay(@Param("fecha") Date fecha);


}
