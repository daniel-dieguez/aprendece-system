package org.edu.aprendece.controller;


import org.apache.coyote.Response;
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
import java.util.*;

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

    @GetMapping("/fecha/{fecha}") //BUSQUEDA DE PACIENTE
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

    @GetMapping("/generadoMonto/{fecha}") //CUANTO HEMOS GENERADO EN EL DIA
    ResponseEntity<?> sumaTotal(@PathVariable String fecha){
        Map<String, Object> response = new HashMap<>();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(fecha); // Aquí conviertes el string a Date

            Long sumaTotal = pacienteReposi.findSumaTotal(date);
            logger.info("Se a realizado consulta sobre busqueda de pacientes de la fecha ");
            response.put("mensaje", "Se ha consultado la fecha");
            response.put("pacientes", sumaTotal);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (ParseException e) {
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

    @GetMapping("/montoFecha/{fecha}") //CUANTO A GENERADO EN EL MES
    ResponseEntity<?> montoMes(@PathVariable String fecha){
        Map<String, Object> response = new HashMap<>();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date date = simpleDateFormat.parse(fecha); // Aquí conviertes el string a Date

            // Extraer mes y año de la fecha
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int mes = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH es base 0, por eso se suma 1
            int anio = calendar.get(Calendar.YEAR);

            Long montoTotal = pacienteReposi.sumaMontoPorMes(mes, anio);

            logger.info("Se a realizado consulta sobre cuanto a generado el paciente en el mes x a mes x ");
            response.put("mensaje", "Se ha consultado la fecha");
            response.put("pacientes", montoTotal);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (ParseException e) {
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

    @GetMapping("/consultaFechas/{fechaInicio}/{fechaFin}")
    ResponseEntity<?> consultaFechaAFecha(@PathVariable String fechaInicio,@PathVariable String fechaFin) {
        Map<String, Object> response = new HashMap<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Convertir las fechas de String a Date
            Date fechaInicioDate = dateFormat.parse(fechaInicio);
            Date fechaFinDate = dateFormat.parse(fechaFin);


            List<Agendar> cita = pacienteReposi.findBetween(fechaInicioDate, fechaFinDate);
            logger.info("Fecha inicio: {}, Fecha fin: {}", fechaInicioDate, fechaFinDate);
            response.put("mensaje", "Se ha realizado la consulta de fecha a fehca");
            response.put("pacientes de fecha a fecha", cita);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (ParseException e) {
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
