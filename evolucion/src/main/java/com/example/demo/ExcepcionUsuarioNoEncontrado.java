package com.example.demo;

class ExcepcionUsuarioNoEncontrado  extends RuntimeException {

    ExcepcionUsuarioNoEncontrado(Long id) {
        super("No se pudo hallar al usuario " + id);
    }
}
