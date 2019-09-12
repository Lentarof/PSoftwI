package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "CUSTOMER_ORDER")
public class Orden {


    private @Id @GeneratedValue Long id;

    private String descripcion;
    private Estado estado;

    Orden() {}

    Orden(String descripcion, Estado estado) {

        this.descripcion = descripcion;
        this.estado = estado;
    }
}
