package org.edu.aprendece.controller;


import org.edu.aprendece.dao.IPacienteReposi;
import org.edu.aprendece.dao.service.PacienteService;
import org.edu.aprendece.models.Agendar;
import org.edu.aprendece.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {


    @Autowired
    private IPacienteReposi pacienteReposi;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private Utils utils;

    Logger logger = LoggerFactory.getLogger(Agendar.class);

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> findByDay(@PathVariable String fecha) { // fecha en string
        Map<String, Object> response = new HashMap<>();
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(fecha); // Aquí conviertes el string a Date


            List<Agendar> agendars = pacienteReposi.findByDay(date);

            logger.info("Se a realizado consulta sobre busqueda de pacientes de la fecha ");
            response.put("mensaje", "Se ha consultado la fecha");
            response.put("pacientes", agendars);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (ParseException e) {
            response.put("mensaje", "Error en el formato de fecha");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (CannotCreateTransactionException e) {
            response = this.utils.getTrasactionExeption(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.utils.getDataAccessException(response, e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


}
