package org.edu.aprendece.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.Map;

@Component
public class Utils {

    Logger logger = LoggerFactory.getLogger(Utils.class);

    public Map<String,Object> getTrasactionExeption(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("error al moemto de conectarse a la base de datos");
        response.put("mensaje", "error");
        response.put("error", e.getMessage().concat(":").concat((e.getMostSpecificCause().getMessage())));
        return  response;
    }

    public Map<String,Object> getDataAccessException(Map<String,Object> response, DataAccessException e ){
        logger.error("eror  al moemtjo de ejecutar consulta");
        response.put("mensaje", "no se realizo bien la consulta, hay un dato que no lo esta aceptando");
        response.put("erorr", e.getMessage().concat(":".concat(e.getMostSpecificCause().getMessage())));
        return response;
    }

}
