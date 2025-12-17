package org.edu.citas.controller;


import org.edu.citas.DTO.CitasCreateDto;
import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.Models.CitasModel;
import org.edu.citas.Models.PersonaModel;
import org.edu.citas.dao.service.CitasService;
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
@RequestMapping("/api/" + CitasController.PATH)
public class CitasController {

    public static final String PATH = "citas";

    @Autowired
    private CitasService citasService;

    private Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private Utils utils;

    @GetMapping("/allCitas/{anio}")
    public ResponseEntity<?> getAllCitas( @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<ICitasDto> citas = citasService.AllCitas(anio);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", citas.size());
            return new ResponseEntity<List<ICitasDto>>(citas, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
    @GetMapping("/citasMensuales/{anio}/{mes}")
    public ResponseEntity<?> getCitasMensuales( @PathVariable int anio, @PathVariable int mes) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<ICitasDto> citas = citasService.citasMensuales(anio, mes);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", citas.size());
            return new ResponseEntity<List<ICitasDto>>(citas, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/citasDiarias/{anio}/{mes}/{dia}")
    public ResponseEntity<?> getCitasDiarias( @PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<ICitasDto> citas = citasService.citasdiarias(anio, mes, dia );
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", citas.size());
            return new ResponseEntity<List<ICitasDto>>(citas, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/storeCita")
    public ResponseEntity<?> storeCita(@RequestBody CitasCreateDto dto) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            PersonaModel paciente = new PersonaModel();
            paciente.setId(dto.getPacienteId());

            CitasModel cita = new CitasModel();
            cita.setPacienteModel(paciente);
            cita.setFechaCita(dto.getFechaCita());
            cita.setHoraCitaInicio(dto.getHoraCitaInicio());
            cita.setHoraCitaFin(dto.getHoraCitaFinal());
            cita.setEstado(1);
            cita.setCreado(LocalDate.now());

            CitasModel Cita = citasService.createCita(cita);
            response.put("mensaje", "Cita creada con éxito");
            response.put("Cita", dto);
            response.put("success", 1);
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

}
