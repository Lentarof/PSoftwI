package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Usuario {

    private @Id @GeneratedValue Long id;
    private String nombre;
    private String cargo;

    Usuario() {}

    Usuario(String nombre, String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }

}