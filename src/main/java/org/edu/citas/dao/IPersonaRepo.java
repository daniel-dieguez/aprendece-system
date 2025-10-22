package org.edu.citas.dao;

import org.edu.citas.models.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaModel, Integer> {

    @Query("SELECT p FROM PersonaModel p WHERE p.estado = 1")
    List<PersonaModel> AllPacientes();

    @Query("SELECT P FROM PersonaModel P WHERE P.fechaCita = :fecha")
    List<PersonaModel> findByDate(@Param("fecha") LocalDate fecha);
}
