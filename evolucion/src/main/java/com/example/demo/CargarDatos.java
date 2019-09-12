package com.example.demo;


import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CargarDatos {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepositorio repositorio) {
        return args -> {
            log.info("Precarga " + repositorio.save(new Usuario("Romulo", "Gerente de MNE")));
            log.info("Precarga " + repositorio.save(new Usuario("Abel", "Gerente de MNA")));
        };
    }
}