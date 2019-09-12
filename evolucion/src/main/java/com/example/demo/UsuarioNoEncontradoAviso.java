package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UsuarioNoEncontradoAviso {

    @ResponseBody
    @ExceptionHandler(ExcepcionUsuarioNoEncontrado.class)   //configura el aviso si responde a ExcepcionUsuario
    @ResponseStatus(HttpStatus.NOT_FOUND)  //HTTP 404
    String employeeNotFoundHandler(ExcepcionUsuarioNoEncontrado ex) {
        return ex.getMessage();
    }
}
