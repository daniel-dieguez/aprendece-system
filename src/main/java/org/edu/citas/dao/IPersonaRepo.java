package org.edu.citas.dao;

import jakarta.transaction.Transactional;
import org.edu.citas.Models.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaModel, Integer> {

    @Query("SELECT p FROM PersonaModel p WHERE p.estado = 1")
    List<PersonaModel> AllPacientes();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO adm.Usuario (nombre, edad, correo, direccion, telefono, tipoUsuario, estado, creado) " +
            "VALUES (:nombre, :edad, :correo, :direccion, :telefono, :tipoUsuario, 1, GETDATE())", nativeQuery = true)
    int insertarUsuario(
            @Param("nombre") String nombre,
            @Param("edad") Integer edad,
            @Param("correo") String correo,
            @Param("direccion") String direccion,
            @Param("telefono") String telefono,
            @Param("tipoUsuario") Integer tipoUsuario);

}
