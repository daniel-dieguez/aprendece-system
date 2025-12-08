package org.edu.citas.controller;


import org.edu.citas.DTO.ICitasDto;
import org.edu.citas.DTO.IMontoDto;
import org.edu.citas.Models.MontosModel;
import org.edu.citas.dao.service.MontoService;
import org.edu.citas.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/{anio}")
    public ResponseEntity<?> listarPorAnio(@PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            List<IMontoDto> montos = montoService.getMontosAnual(anio);
            this.logger.info("Consulta exitosa, registros encontrados: {}", montos.size());

            return new ResponseEntity<List<IMontoDto>>(montos, HttpStatus.OK);

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

            return new ResponseEntity<List<IMontoDto>>(montos, HttpStatus.OK);

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

            return new ResponseEntity<List<IMontoDto>>(montos, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @GetMapping("/totalAnual/{anio}")
    public ResponseEntity<?> totalAnual( @PathVariable int anio) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long montos = montoService.totalAnual( anio);
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

    @GetMapping("/totalMensual/{anio}/{mes}")
    public ResponseEntity<?> totalAnual( @PathVariable int anio, @PathVariable int mes) {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");
        try {
            Long montos = montoService.totalMensual( anio, mes);
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

}
