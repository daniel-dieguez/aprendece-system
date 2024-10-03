package org.edu.aprendece.dao.implement;

import org.edu.aprendece.models.Agendar;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IPacienteImpl {

    List<Agendar> findAll();
    public Agendar findById(Integer id_cita);
    public Agendar save(Agendar agendar);
    public void delete(Agendar agendar);

}
