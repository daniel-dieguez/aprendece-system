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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO adm.notas (pacienteId, nota, estado, creado)" +
            "VALUES (:pacienteId, :nota, 1, GETDATE())", nativeQuery = true)
    int insertNota(
            @Param("pacienteId") int pacienteId,
            @Param("nota") String nota);


}
