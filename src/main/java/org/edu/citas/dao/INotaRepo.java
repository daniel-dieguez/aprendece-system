package org.edu.citas.dao;


import org.edu.citas.models.NotasModel;
import org.edu.citas.models.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotaRepo extends JpaRepository<NotasModel, Integer> {



@Query(value = """
    SELECT 
        p.id AS idUsuario,
        p.nombre,
        n.estado,
        n.nota,
        n.creado,
        n.actualizado
    FROM adm.Notas n
    INNER JOIN adm.Usuario p ON n.pacienteId = p.id
""", nativeQuery = true)
    List<NotasModel> AllNotas();

}
