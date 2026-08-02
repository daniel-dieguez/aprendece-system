package org.edu.citas.controller;


import jakarta.websocket.server.PathParam;
import org.edu.citas.DTO.NotasCreateDto;
import org.edu.citas.Models.NotasModel;
import org.edu.citas.Models.PersonaModel;
import org.edu.citas.dao.service.NotasService;
import org.edu.citas.DTO.InotasDto;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/" + NotasController.PATH)
public class NotasController {

    public static final String PATH = "notas";
    private Logger logger = LoggerFactory.getLogger(NotasController.class);

    @Autowired
    private Utils utils;

    @Autowired
    private NotasService notasService;

    @GetMapping("/allNotas")
    public ResponseEntity<?> getAllNotas() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try {
            List<InotasDto> notas = notasService.AllNotas();
            logger.info("Se ha realizado consulta correctamente, registros encontrados", notas.size());
            return new ResponseEntity<List<InotasDto>>(notas, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/storeNota")
    public ResponseEntity<?> storeNota(@RequestBody NotasCreateDto dto) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try{
            PersonaModel paciente = new PersonaModel();
            paciente.setId(dto.getPacienteId());

            NotasModel nota = new NotasModel();
            nota.setPacienteModel(paciente);
            nota.setNota(dto.getNota());
            nota.setEstado(1);
            nota.setAnio(dto.getAnio());
            nota.setCreado(LocalDate.now());

            NotasModel nuevaNota = notasService.createNota(nota);
            response.put("mensaje", "Nota creado con éxito");
            response.put("Nota", dto);
            response.put("response", 1);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (CannotCreateTransactionException e) {
            response.put("fail", 0);
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response.put("fail", 0);
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    @GetMapping("/oneNotaxPersona/{pacienteId}")
    public ResponseEntity<?> oneNotaxPersona(@PathVariable int pacienteId) {

        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando consulta");

        try {

            Optional<InotasDto> oneNotas = notasService.oneNotasXPersona(pacienteId);

            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", oneNotas);

            if (oneNotas.isEmpty()) {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response", 1);
            response.put("data", oneNotas.get());

            return ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);

        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/oneNota/{id}")
    public ResponseEntity<?> oneNota(@PathVariable int id) {

        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando consulta");

        try {

            Optional<InotasDto> oneNotas = notasService.oneNotas(id);

            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", oneNotas);

            if (oneNotas.isEmpty()) {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response", 1);
            response.put("data", oneNotas.get());

            return ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);

        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
