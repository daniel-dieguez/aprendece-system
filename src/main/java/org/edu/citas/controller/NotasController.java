package org.edu.citas.controller;


import org.edu.citas.dao.service.NotasService;
import org.edu.citas.dao.service.PersonaService;
import org.edu.citas.models.NotasModel;
import org.edu.citas.models.PersonaModel;
import org.edu.citas.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notas")
public class NotasController {


    @Autowired
    private NotasService NotaService;

    private Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private Utils utils;
    @Autowired
    private NotasService notasService;

    @GetMapping("/pacientes")
    public ResponseEntity<?> getAllPersonas() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("iniciando consulta");

        try {
            List<NotasModel> notas = notasService.findAllNotas();
            logger.info("Se ha realizado consulta correctamente, registros encontrados: {}", notas.size());
            return new ResponseEntity<List<NotasModel>>(notas, HttpStatus.OK);

        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


}
