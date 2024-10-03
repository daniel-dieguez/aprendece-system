package org.edu.aprendece.controller;


import jakarta.validation.Valid;
import org.edu.aprendece.dao.IPacienteReposi;
import org.edu.aprendece.dao.service.PacienteService;
import org.edu.aprendece.models.Agendar;
import org.edu.aprendece.models.dto.PacienteDTO;
import org.edu.aprendece.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteReposi iPacienteReposi;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private Utils utils;

    private Logger logger = LoggerFactory.getLogger(Agendar.class);

    @GetMapping("/AllPacientes")
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<Agendar> agendars = pacienteService.findAll();
            logger.info("se a realizado consulta");
            return new ResponseEntity<List<Agendar>>(agendars, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/newPaciente")
    public ResponseEntity<?> newPaciente(@Valid @RequestBody PacienteDTO value) {
        Map<String, Object> response = new HashMap<>();
        try {
            Agendar newAgendar = new Agendar();
            newAgendar.setId_cita(value.getId_cita());
            newAgendar.setFecha(value.getFecha());
            newAgendar.setHora_cita(value.getHora_cita());
            newAgendar.setDpi_usuario(value.getDpi_usuario());
            newAgendar.setNombre_usuario(value.getNombre_usuario());
            newAgendar.setNumero_usuario(value.getNumero_usuario());
            newAgendar.setEdad_usuario(value.getEdad_usuario());
            newAgendar.setCorreo_usuario(value.getCorreo_usuario());
            this.iPacienteReposi.save(newAgendar);
            logger.info("se a realizado la creacion de un nuevo paciente");
            response.put("mensaje","se a realizado un nuevo paciente");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PutMapping("/actualizarCita/{id_cita}")
    public ResponseEntity<?> updatePaciente(@Valid @RequestBody Agendar value, @PathVariable Integer id_cita) {//directo al entity
        Map<String, Object> response = new HashMap<>();
        try {
            Agendar agendar = this.pacienteService.findById(id_cita);

            agendar.setFecha(value.getFecha());
            agendar.setHora_cita(value.getHora_cita());
            this.iPacienteReposi.save(agendar);
            logger.info("se a realizado la modificacion de un nuevo paciente");
            response.put("mensaje","se a realizado actualizacion de un paciente");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }



    @DeleteMapping("/delete/{id_cita}")
    public ResponseEntity<?>delete(@PathVariable Integer id_cita){
        Map<String, Object> response = new HashMap<>();
        try {
            Agendar agendar = this.pacienteService.findById(id_cita);

            this.pacienteService.delete(agendar);
            logger.info("Se a eliminado el paciente");
            response.put("mensaje", "se a eliminado el paciente..".concat(agendar.getNombre_usuario()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }




}
