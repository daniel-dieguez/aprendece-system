package org.edu.citas.controller;

import org.edu.citas.dao.service.PersonaService;
import org.edu.citas.Models.PersonaModel;
import org.edu.citas.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/" + PersonaController.PATH)
public class PersonaController {

    public static final String PATH = "personas";

    @Autowired
    private PersonaService personaService;

    private Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private Utils utils;

    @GetMapping("/pacientes/{anio}")
    public ResponseEntity<?> getAllPersonas(@PathVariable int anio) {
                Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try {
            List<PersonaModel> pacientes = personaService.AllPacientes(anio);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", pacientes.size());
            return new ResponseEntity<List<PersonaModel>>(pacientes, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/pacientes/{anio}/{mes}")
    public ResponseEntity<?> getAllPersonasAnualYMensual(@PathVariable int anio, @PathVariable int mes) {
                Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try {
            List<PersonaModel> pacientes = personaService.pacientePormes(anio, mes);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", pacientes.size());
            return new ResponseEntity<List<PersonaModel>>(pacientes, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
    @GetMapping("/totalPacientes/{anio}")
    public ResponseEntity<?> totalAnuales(@PathVariable int anio) {
                Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try {
            Long pacientes = personaService.totalPacientesAnual(anio);
            logger.info("Se ha realizado consulta correctamente, total encontrados: {}", pacientes);
            return ResponseEntity.ok(pacientes);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/totalMensual/{anio}/{mes}")
    public ResponseEntity<?> totalMensualAnual(@PathVariable int anio, @PathVariable int mes) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try {
           Long pacientes = personaService.totalPacientesMensual(anio, mes);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}");
            return ResponseEntity.ok(pacientes);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/newUsuario")
    public ResponseEntity<?> newUsuario(@RequestBody PersonaModel persona) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try{

            persona.setCreado(LocalDate.now());
            PersonaModel nuevoUsuario = personaService.crearPersona(persona);
            response.put("mensaje", "Usuario creado con éxito");
            response.put("usuario", nuevoUsuario);
            response.put("success", 1);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
}
