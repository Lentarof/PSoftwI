package com.example.demo;


import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class CargarDatos {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepositorio usuarioRepositorio, OrdenRepositorio ordenRepositorio) {
        return args -> {

             /*  Anterior forma de cargar al repositorio usuarios
            log.info("Precarga " + repositorio.save(new Usuario("Romulo","Teran", "Gerente de MNE")));
            log.info("Precarga " + repositorio.save(new Usuario("Abel","Tenaz", "Gerente de MNA")));
            */
            usuarioRepositorio.save(new Usuario("Romulo", "Teran", "MNE"));
            usuarioRepositorio.save(new Usuario("Abel", "Tenaz", "MNA"));

            usuarioRepositorio.findAll().forEach(usuario -> {
                log.info("Precarga " + usuario);
            });

            ordenRepositorio.save(new Orden("MacBook Pro", Estado.COMPLETED));
            ordenRepositorio.save(new Orden("iPhone", Estado.IN_PROGRESS));

            ordenRepositorio.findAll().forEach(orden -> {
                log.info("Precarga " + orden);
            });

        };
    }
}