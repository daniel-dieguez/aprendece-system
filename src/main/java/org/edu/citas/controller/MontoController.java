package org.edu.citas.controller;


import org.edu.citas.DTO.CitasCreateDto;
import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.DTO.IMontoDto;
import org.edu.citas.DTO.MontoCreateDto;
import org.edu.citas.Models.CitasModel;
import org.edu.citas.Models.MontosModel;
import org.edu.citas.Models.PersonaModel;
import org.edu.citas.dao.service.MontoService;
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
@RequestMapping("/api/" + MontoController.PATH)
public class MontoController {

    public static final String PATH = "montos";

    @Autowired
    private MontoService montoService;

    private Logger logger = LoggerFactory.getLogger(MontoController.class);

    @Autowired
    private Utils utils;


    @GetMapping("/anio/{anio}")
    public ResponseEntity<?> listarPorAnio(@PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<IMontoDto> montos = montoService.getMontosAnual(anio);
            this.logger.info("Consulta exitosa, registros encontrados: {}", montos.size());

            if(montos.isEmpty())
            {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response",1);
            response.put("data", montos);

            return  ResponseEntity.ok(response);

//            return new ResponseEntity<List<IMontoDto>>(montos, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
    @GetMapping("/mes/{mes}/anio/{anio}")
    public ResponseEntity<?> listarPormes(@PathVariable int mes, @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<IMontoDto> montos = montoService.getMontosMensual(mes, anio);
            this.logger.info("Consulta exitosa, registros encontrados: {}", montos.size());
            if(montos.isEmpty())
            {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response",1);
            response.put("data", montos);

            return  ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }
    @GetMapping("/usuario/{pacienteId}/anio/{anio}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable int pacienteId, @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<IMontoDto> montos = montoService.getMontosMensual(pacienteId, anio);
            this.logger.info("Consulta exitosa, registros encontrados: {}", montos.size());
            if(montos.isEmpty())
            {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response",1);
            response.put("data", montos);

            return  ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


    //Motos totales
    @GetMapping("/totalAnual/{anio}")
    public ResponseEntity<?> totalAnual( @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long montos = montoService.totalAnual( anio);
            this.logger.info("Consulta exitosa, registros encontrados: {}");

            if(montos == null)
            {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response",1);
            response.put("data", montos.byteValue());

            return  ResponseEntity.ok(response);

//            return  ResponseEntity.ok(montos);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    //monto por mes
    @GetMapping("/totalMensual/{anio}/{mes}")
    public ResponseEntity<?> totalAnual( @PathVariable int anio, @PathVariable int mes) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long montos = montoService.totalMensual( anio, mes);
            this.logger.info("Consulta exitosa, registros encontrados: {}");

            if(montos == null)
            {
                response.put("response", 0);
                response.put("mensaje", "No se encontró ninguna nota para el paciente.");

                return ResponseEntity.ok(response);
            }

            response.put("response",1);
            response.put("data", montos);

            return  ResponseEntity.ok(response);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }



//    monto por persona, por año
    @GetMapping("/totalPorPersona/{anio}/{pacienteId}")
    public ResponseEntity<?> totalPorPersona( @PathVariable int anio, @PathVariable int pacienteId) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long montos = montoService.totalPaciente( anio, pacienteId);
            this.logger.info("Consulta exitosa, registros encontrados: {}");

            return  ResponseEntity.ok(montos);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/storeMonto")
    public ResponseEntity<?> storeMonto(@RequestBody MontoCreateDto dto) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            PersonaModel paciente = new PersonaModel();
            paciente.setId(dto.getPacienteId());
//            paciente.setId(dto.getPacienteId());


            MontosModel montoss = new MontosModel();
//            montoss.getMonto()
            montoss.setPacienteModel(paciente);
            montoss.setMonto(dto.getMonto());
            montoss.setMes(dto.getMes());
            montoss.setAnio(dto.getAnio());
            montoss.setEstado(1);
            montoss.setCreado(LocalDate.now());

            response.put("mensaje", "Monto Agregado");
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

}
