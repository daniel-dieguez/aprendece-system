package org.edu.citas.dao;


import jakarta.transaction.Transactional;
import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.Models.CitasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ICitasRepo extends JpaRepository<CitasModel, Integer> {

    @Query("""
    SELECT 
        c.pacienteModel.id AS idUsuario,
        c.pacienteModel.nombre AS nombre,
        c.fechaCita AS fechaCita,
        c.horaCitaInicio AS horaCitaInicio,
        c.horaCitaFin AS horaCitaFin,
        c.estado AS estado,
        c.anio as anio,
        c.mes as mes
    
    FROM CitasModel c
    WHERE c.anio = :anio
""")
    List<ICitasDto> AllCitas(@Param("anio") int anio);

    @Query("""
    SELECT 
c.pacienteModel.id AS idUsuario,
        c.pacienteModel.nombre AS nombre,
        c.fechaCita AS fechaCita,
        c.horaCitaInicio AS horaCitaInicio,
        c.horaCitaFin AS horaCitaFin,
        c.estado AS estado,
        c.anio as anio,
        c.mes as mes
    FROM CitasModel c
    WHERE c.anio = :anio
    and c.mes = :mes 
""")
    List<ICitasDto> CitasMensuales(@Param("anio") int anio, @Param("mes") int mes);

    @Query("""
    SELECT 
c.pacienteModel.id AS idUsuario,
        c.pacienteModel.nombre AS nombre,
        c.fechaCita AS fechaCita,
        c.horaCitaInicio AS horaCitaInicio,
        c.horaCitaFin AS horaCitaFin,
        c.estado AS estado,
        c.anio as anio,
        c.mes as mes
    FROM CitasModel c
    WHERE c.anio = :anio
    and c.mes = :mes 
    and DAY(c.fechaCita) = :dia
""")
    List<ICitasDto> CitasDiarias(@Param("anio") int anio, @Param("mes") int mes, @Param("dia") int dia);



    @Modifying
    @Transactional
    @Query(value = "INSERT INTO adm.notas (pacienteId, fechaCita, fechaCitaInicio, fechaCitaFin,estado, creado, anio,mes)" +
            "VALUES (:pacienteId, :fechaCita, :fechaCitaInicio, :fechaCitaFin,1, GETDATE(),anio, mes)", nativeQuery = true)
    int insertCita(
            @Param("pacienteId") int pacienteId,
            @Param("fechaCita") LocalDateTime fechaCita,
            @Param("fechaCitaInicio") LocalDateTime fechaCitaInicio,
            @Param("fechaCitaFin") LocalDateTime fechaCitaFin);


}
