package org.edu.aprendece.controller;


import jakarta.validation.Valid;
import jakarta.websocket.OnClose;
import org.edu.aprendece.dao.IPacienteReposi;
import org.edu.aprendece.dao.service.PacienteService;
import org.edu.aprendece.models.Paciente;
import org.edu.aprendece.models.dto.PacienteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteReposi iPacienteReposi;

    @Autowired
    private PacienteService pacienteService;

    private Logger logger = LoggerFactory.getLogger(Paciente.class);

    @GetMapping("/AllPacientes")
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<Paciente> pacientes = pacienteService.findAll();
            logger.info("se a realizado consulta");
            return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/newpaciente")
    public ResponseEntity<?> newPaciente(@Valid @RequestBody PacienteDTO value) {
        Map<String, Object> response = new HashMap<>();
        try {
            Paciente newPaciente = new Paciente();
            newPaciente.setDpi_usuario(value.getDpi_usuario());
            newPaciente.setNombre_usuario(value.getNombre_usuario());
            newPaciente.setEdad_usuario(value.getEdad_usuario());
            newPaciente.setCorreo_usuario(value.getCorreo_usuario());
            this.iPacienteReposi.save(newPaciente);
            logger.info("se a realizado la creacion de un nuevo paciente");
            response.put("mensaje","se a realizado un nuevo paciente");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PutMapping("/updatePaciente/{dpi_usuario}")
    public ResponseEntity<?> updatePaciente(@Valid @RequestBody Paciente value, @PathVariable long dpi_usuario) {//directo al entity
        Map<String, Object> response = new HashMap<>();
        try {
            Paciente paciente = this.pacienteService.findById(dpi_usuario);

            paciente.setNombre_usuario(value.getNombre_usuario());
            paciente.setEdad_usuario(value.getEdad_usuario());
            paciente.setCorreo_usuario(value.getCorreo_usuario());
            this.iPacienteReposi.save(paciente);
            logger.info("se a realizado la modificacion de un nuevo paciente");
            response.put("mensaje","se a realizado actualizacion de un paciente");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @DeleteMapping("/delete/{dpi_usuario}")
    public ResponseEntity<?>delete(@PathVariable long dpi_usuario){
        Map<String, Object> response = new HashMap<>();
        try {
            Paciente paciente = this.pacienteService.findById(dpi_usuario);

            this.pacienteService.delete(paciente);
            logger.info("Se a eliminado el paciente");
            response.put("mensaje", "se a eliminado el paciente..".concat(paciente.getNombre_usuario()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    private Map<String, Object> getTransactionExepcion(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("Error al momento de conectarse a la base de datos");
        response.put("mensajee", "error al moneotno de contectarse a la");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;
    }

    private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e){
        logger.error("El error al momento de ejecutar la consulta a la base d adatos");
        response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;

    }


}
