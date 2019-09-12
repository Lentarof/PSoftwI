package com.example.demo;

class ExcepcionOrdenNoEncontrada extends RuntimeException {

    ExcepcionOrdenNoEncontrada(Long id) {
        super("No se pudo encontrar la orden" + id);
    }
}
