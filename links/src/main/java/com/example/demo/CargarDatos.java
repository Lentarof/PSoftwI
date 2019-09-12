package com.example.demo;


import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class CargarDatos {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepositorio repositorio, OrdenRepositorio ordenRepositorio) {
        return args -> {
            log.info("Precarga " + repositorio.save(new Usuario("Romulo","Teran", "Gerente de MNE")));
            log.info("Precarga " + repositorio.save(new Usuario("Abel","Tenaz", "Gerente de MNA")));

            ordenRepositorio.save(new Orden("MacBook Pro", Estado.COMPLETED));
            ordenRepositorio.save(new Orden("iPhone", Estado.IN_PROGRESS));

            ordenRepositorio.findAll().forEach(orden -> {
                log.info("Precarga " + orden);
            });

        };
    }
}