package org.edu.citas.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/" + NotasController.PATH)
public class NotasController {

    public static final String PATH = "notas";
    private Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private Utils utils;

    @Autowired
    private NotasService notasService;

    @GetMapping("/allnotas")
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


}
