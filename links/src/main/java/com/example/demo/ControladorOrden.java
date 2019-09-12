package com.example.demo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorOrden {

    private final OrdenRepositorio ordenRepositorio;
    private final OrdenRecursoAssembler assembler;

    ControladorOrden(OrdenRepositorio ordenRepositorio,
                    OrdenRecursoAssembler assembler) {

        this.ordenRepositorio = ordenRepositorio;
        this.assembler = assembler;
    }

    @GetMapping("/ordenes")
    Resources<Resource<Orden>> all() {

        List<Resource<Orden>> ordenes = ordenRepositorio.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(ordenes,
                linkTo(methodOn(ControladorOrden.class).all()).withSelfRel());
    }

    @GetMapping("/ordenes/{id}")
    Resource<Orden> one(@PathVariable Long id) {
        return assembler.toResource(
                ordenRepositorio.findById(id)
                        .orElseThrow(() -> new ExcepcionOrdenNoEncontrada(id)));
    }

    @PostMapping("/ordenes")
    ResponseEntity<Resource<Orden>> nuevaOrden(@RequestBody Orden orden) {

        orden.setEstado(Estado.IN_PROGRESS);
        Orden nuevaOrden = ordenRepositorio.save(orden);

        return ResponseEntity
                .created(linkTo(methodOn(ControladorOrden.class).one(nuevaOrden.getId())).toUri())
                .body(assembler.toResource(nuevaOrden));
    }

    @DeleteMapping("/ordenes/{id}/cancel")
    ResponseEntity<ResourceSupport> cancel(@PathVariable Long id) {

        Orden orden = ordenRepositorio.findById(id).orElseThrow(() -> new ExcepcionOrdenNoEncontrada(id));

        if (orden.getEstado() == Estado.IN_PROGRESS) {
            orden.setEstado(Estado.CANCELLED);
            return ResponseEntity.ok(assembler.toResource(ordenRepositorio.save(orden)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Metodo no permitido", " No puede cancelar la orden que esta en " + orden.getEstado() + " estado"));
    }
}
