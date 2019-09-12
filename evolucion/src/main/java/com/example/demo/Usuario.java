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
    private String apellido;
    private String cargo;

    Usuario() {}

    Usuario(String nombre,String apellido, String cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
    }

    public String getNombre(){
        return this.nombre +" " + this.apellido;
    }
    public void setNombre(String name){
        String[] partes = name.split(" ");
        this.nombre = partes[0];
        this.apellido = partes[1];
    }

}