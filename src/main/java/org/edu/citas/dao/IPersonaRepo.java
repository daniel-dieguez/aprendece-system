package org.edu.citas.dao;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.edu.citas.Models.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaModel, Integer> {

    @Query("""

            SELECT p\s
    FROM PersonaModel p
    WHERE p.estado = 1
            """)
    List<PersonaModel> AllPersonas (); //Este es listado para todas las personas

    @Query("""
    SELECT p
    FROM PersonaModel p
    WHERE p.anio = :anio
      AND p.id NOT IN (1, 2)
    ORDER BY p.id
""")
    List<PersonaModel> AllPacientes(
            @Param("anio") int anio,
            Pageable pageable
    );

    @Query("""

            SELECT p\s
    FROM PersonaModel p
    WHERE p.estado = 1
      AND p.anio = :anio
      and p.mes = :mes
            """)
    List<PersonaModel> pacientesPorAnioYMes(@Param("anio") int anio, @Param("mes") int mes);

    @Query("""

            SELECT COUNT(p.id)
            FROM PersonaModel p
    WHERE p.estado = 1
      AND p.anio = :anio
     
            """)
    Long totalPacientesAnual(@Param("anio") int anio);

    @Query("""

            SELECT COUNT(p.id)
            FROM PersonaModel p
    WHERE p.estado = 1
      AND
           p.anio = :anio
      and p.mes = :mes
            """)
    Long totalPacientesMesual(@Param("anio") int anio, @Param("mes") int mes);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO adm.Usuario (nombre, edad, correo,pais, direccion, telefono, tipoUsuario, motivo, estado, creado) " +
            "VALUES (:nombre, :edad, :correo,:pais, :direccion, :telefono, :tipoUsuario, motivo,1, GETDATE())", nativeQuery = true)
    int insertarUsuario(
            @Param("nombre") String nombre,
            @Param("pais") String pais,
            @Param("edad") Integer edad,
            @Param("correo") String correo,
            @Param("direccion") String direccion,
            @Param("telefono") String telefono,
            @Param("tipoUsuario") Integer tipoUsuario,
            @Param("motivo") String motivo);


}
