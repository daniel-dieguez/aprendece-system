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

    //---- citas

    @GetMapping("/allCitas/{anio}")
    public ResponseEntity<?> getAllCitas( @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<ICitasDto> citas = citasService.AllCitas(anio);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", citas);

           if(citas.isEmpty()){
               response.put("response", 0);
               response.put("mensaje", "No se han encontrado citas para el paciente.");
           }

            response.put("response", 1);
           response.put("data", citas);
            return  ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
    @GetMapping("/allCitasMensuales/{anio}/{mes}")
    public ResponseEntity<?> getCitasMensuales( @PathVariable int anio, @PathVariable int mes) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<ICitasDto> citas = citasService.citasMensuales(anio, mes);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", citas.size());


            if(citas.isEmpty()){
                response.put("response", 0);
                response.put("mensaje", "No se han encontrado citas  del año: {} del mes para el paciente.");
            }

            response.put("response", 1);
            response.put("data", citas);
            return  ResponseEntity.ok(response);


//            return ResponseEntity.ok(response);
//
        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/allCitasDiarias/{anio}/{mes}/{dia}")
    public ResponseEntity<?> getCitasDiarias( @PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<ICitasDto> citas = citasService.citasdiarias(anio, mes, dia );
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", citas.size());


            if(citas.isEmpty()){
                response.put("response", 0);
                response.put("mensaje", "No se han encontrado citas  del año: {} del mes para el paciente.");
            }

            response.put("response", 1);
            response.put("data", citas);
            return  ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


    //esta de abajo no se para que seria sabes, aqui deeberioamos de mostras un ALL pero de listado de pacientes, nombres
//    y datos de la cita
    @GetMapping("/citasTotalDiarias/{anio}/{mes}/{dia}")
    public ResponseEntity<?> getCitasTotallDiarias( @PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long citas = citasService.citasTotaldiarias(anio, mes, dia );
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}");


            if (citas == null) {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response", 1);
            response.put("data", citas.byteValue());

            return ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/citasTotalMensuales/{anio}/{mes}")
    public ResponseEntity<?> getCitasTotalMensuales( @PathVariable int anio, @PathVariable int mes) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long citas = citasService.citasTotalesMensuales(anio, mes);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}");


            if (citas == null) {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response", 1);
            response.put("data", citas.byteValue());

            return ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/citasTotalAnual/{anio}")
    public ResponseEntity<?> getCitasTotalAnual( @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long citas = citasService.citasTotalesAnuales(anio);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}");


            if (citas == null) {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response", 1);
            response.put("data", citas.byteValue());

            return ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/citasTotalDiario/{anio}/{mes}/{dia}")
    public ResponseEntity<?> getCitasTotalDiaario( @PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long citas = citasService.totalcitasdiarias(anio, mes, dia);
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}");


            if (citas == null) {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response", 1);
            response.put("data", citas.byteValue());

            return ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


    //creacion de nueva cita

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
            response.put("mensaje", "Cita creada con éxito la cita");
            response.put("Cita", dto);
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



    // Aqui va la creacion de post

}
