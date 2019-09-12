package com.example.demo;

import java.util.List;

import org.aspectj.weaver.Iterators;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
class ControladorUsuario {

    private final UsuarioRepositorio repositorio;

    ControladorUsuario(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }
//Agregar root

    @GetMapping("/usuarios")
    List<Usuario> all() {
        return repositorio.findAll();
    }

    @PostMapping("/usuarios")
    Usuario nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        return repositorio.save(nuevoUsuario);
    }

    // item unico
    /*@GetMapping("/usuarios/{id}")
    Usuario oneUsuario(@PathVariable Long id) {

        return repositorio.findById(id)
                .orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(id));
    }*/
    @GetMapping("/usuarios/{id}")
    Resource<Usuario> one(@PathVariable Long id) {

        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(id));

        return new Resource<>(usuario,
                linkTo(methodOn(ControladorUsuario.class).one(id)).withSelfRel(),
                linkTo(methodOn(ControladorUsuario.class).all()).withRel("usuarios"));
    }

    @PutMapping("/usuarios/{id}")
        Usuario replaceUsuario(@RequestBody Usuario nuevoUsuario, @PathVariable Long id) {

            return repositorio.findById(id)
                    .map(usuario -> {
                        usuario.setNombre(nuevoUsuario.getNombre());
                        usuario.setCargo(nuevoUsuario.getCargo());
                        return repositorio.save(usuario);
                    })
                    .orElseGet(() -> {
                        nuevoUsuario.setId(id);
                        return repositorio.save(nuevoUsuario);
                    });

    }

    @DeleteMapping("/usuarios/{id}")
    void deleteUsuario(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}