package com.example.demo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorRoot {

    @GetMapping
    ResourceSupport index() {
        ResourceSupport rootRecurso = new ResourceSupport();
        rootRecurso.add(linkTo(methodOn(ControladorUsuario.class).all()).withRel("usuarios"));
        rootRecurso.add(linkTo(methodOn(ControladorOrden.class).all()).withRel("ordenes"));
        return rootRecurso;
    }
}
