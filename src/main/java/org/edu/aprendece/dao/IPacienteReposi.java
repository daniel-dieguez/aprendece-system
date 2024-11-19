package org.edu.aprendece.dao;


import org.edu.aprendece.models.Agendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IPacienteReposi extends JpaRepository<Agendar, Integer> {


    @Query("SELECT p FROM Agendar p WHERE DATE(p.fecha) = :fecha")
    List<Agendar> findByDay(@Param("fecha") Date fecha);

    @Query("SELECT SUM(a.monto_sesion) FROM Agendar a WHERE a.fecha = :fecha")
    Long findSumaTotal(@Param("fecha") Date fecha);


    @Query("SELECT SUM(a.monto_sesion) FROM Agendar a WHERE MONTH(a.fecha) = :mes AND YEAR(a.fecha) = :anio")
    Long sumaMontoPorMes(@Param("mes") int mes, @Param("anio") int anio);

    /*seleccionar de fecha a fecha*/
    @Query("SELECT p FROM Agendar p WHERE p.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Agendar> findBetween(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);





}
