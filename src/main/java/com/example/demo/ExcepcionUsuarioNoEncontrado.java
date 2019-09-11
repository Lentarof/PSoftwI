package com.example.demo;

class ExcepcionUsuarioNoEncontrado {

    ExcepcionUsuarioNoEncontrado(Long id) {
        super("No se pudo hallar al usuario " + id);
    }
}
