package org.edu.citas.dao;

import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.DTO.IMontoDto;
import org.edu.citas.Models.MontosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IMontoRepo extends JpaRepository <MontosModel, Integer>{

    @Query("""
    SELECT 
    c.id as id,
        c.pacienteModel.id AS idUsuario,
        c.pacienteModel.nombre AS nombre,
        c.mes as mes,
        c.monto as monto,
        c.anio as anio,
        c.estado AS estado
    FROM MontosModel c
   WHERE c.anio = :anio
""")
    List<IMontoDto> MontoAnual(@Param("anio") int anio);

    @Query("""
    SELECT 
    c.id as id,
        c.pacienteModel.id AS idUsuario,
        c.pacienteModel.nombre AS nombre,
        c.mes as mes,
        c.monto as monto,
        c.anio as anio,
        c.estado AS estado
    FROM MontosModel c
   WHERE c.mes = :mes
   and c.anio = :anio
""")
    List<IMontoDto> MontoMensual(@Param("mes") int mes, @Param("anio") int anio);

    @Query("""
    SELECT 
    c.id as id,
        c.pacienteModel.id AS idUsuario,
        c.pacienteModel.nombre AS nombre,
        c.mes as mes,
        c.monto as monto,
        c.anio as anio,
        c.estado AS estado
    FROM MontosModel c
   WHERE c.pacienteModel.id = :pacienteId
   and c.anio = :anio
   
""")
    List<IMontoDto> MontoPersonalAnual(@Param("pacienteId") int pacienteId, @Param("anio") int anio);

    @Query("""
    SELECT 
    SUM(c.monto)
    FROM MontosModel c
   WHERE c.anio = :anio
""")
   Long TotalAnaul( @Param("anio") int anio);

    @Query("""
    SELECT SUM (c.monto)
    FROM MontosModel c
   WHERE c.mes = :mes
   and c.anio = :anio
   
""")
    Long TotalMensual(@Param("mes") int mes, @Param("anio") int anio);

    @Query("""
    SELECT SUM (c.monto)
    FROM MontosModel c
   WHERE c.pacienteModel.id = :pacienteId
   and c.anio = :anio
   
""")
    Long TotalPorPersona(@Param("anio") int anio,@Param("pacienteId") int pacienteId );





}
