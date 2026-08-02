package org.edu.citas.dao;


import jakarta.transaction.Transactional;
import org.edu.citas.Models.NotasModel;
import org.edu.citas.DTO.InotasDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INotaRepo extends JpaRepository<NotasModel, Integer> {

    @Query("""
    SELECT n.id as id,
     n.pacienteModel.id AS idUsuario,
           n.pacienteModel.nombre AS nombre,
           n.estado AS estado,
           n.nota AS nota,
           n.creado AS creado
    FROM NotasModel n
""")
    List<InotasDto> AllNotas();

    /// ----- create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO adm.notas (pacienteId, nota, estado,anio, creado)" +
            "VALUES (:pacienteId, :nota, 1,:anio, GETDATE())", nativeQuery = true)
    int insertNota(
            @Param("pacienteId") int pacienteId,
            @Param("anio") int anio,
            @Param("nota") String nota);


    //--- One nota x persona
    @Query("""
SELECT n.id as id,
               n.pacienteModel.id AS idUsuario,
               
               n.pacienteModel.nombre AS nombre,
               n.estado AS estado,
               n.nota AS nota,
               n.creado AS creado
        FROM NotasModel n

WHERE n.pacienteModel.id = :pacienteId
""")
    Optional<InotasDto> oneNotasXPersona(@Param("pacienteId") int pacienteId);


    //-- oneOneNota
    @Query("""
SELECT n.id as id,
               n.pacienteModel.id AS idUsuario,
               
               n.pacienteModel.nombre AS nombre,
               n.estado AS estado,
               n.nota AS nota,
               n.creado AS creado
        FROM NotasModel n

WHERE n.id = :id
""")
    Optional<InotasDto> oneNotas(@Param("id") int id);




}
