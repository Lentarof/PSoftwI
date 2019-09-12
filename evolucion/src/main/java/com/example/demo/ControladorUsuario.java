package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.Iterators;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

    private final UsuarioRecursoAssembler assembler;

    ControladorUsuario(UsuarioRepositorio repositorio,
                       UsuarioRecursoAssembler assembler) {
        this.repositorio = repositorio;
        this.assembler = assembler;
    }
//Agregar root

    /*
    @GetMapping("/usuarios")
    List<Usuario> all() {
        return repositorio.findAll();
    }
    */
/*
    @GetMapping("/usuarios")
    Resources<Resource<Usuario>> all() {

        List<Resource<Usuario>> usuarios = repositorio.findAll().stream()
                .map(usuario -> new Resource<>(usuario,
                        linkTo(methodOn(ControladorUsuario.class).one(usuario.getId())).withSelfRel(),
                        linkTo(methodOn(ControladorUsuario.class).all()).withRel("usuarios")))
                .collect(Collectors.toList());

        return new Resources<>(usuarios,
                linkTo(methodOn(ControladorUsuario.class).all()).withSelfRel());
    }
 */
    @GetMapping("/usuarios")
    Resources<Resource<Usuario>> all() {

        List<Resource<Usuario>> usuarios = repositorio.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(usuarios,
                linkTo(methodOn(ControladorUsuario.class).all()).withSelfRel());
    }
//end root
    @PostMapping("/usuarios")
    Usuario nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        return repositorio.save(nuevoUsuario);
    }

    // item unico
/*
    @GetMapping("/usuarios/{id}")
    Usuario oneUsuario(@PathVariable Long id) {

        return repositorio.findById(id)
                .orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(id));
    }
*/
 /*   @GetMapping("/usuarios/{id}")
    Resource<Usuario> one(@PathVariable Long id) {

        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(id));

        return new Resource<>(usuario,
                linkTo(methodOn(ControladorUsuario.class).one(id)).withSelfRel(),
                linkTo(methodOn(ControladorUsuario.class).all()).withRel("usuarios"));
    }
*/
    @GetMapping("/usuarios/{id}")
    Resource<Usuario> one(@PathVariable Long id) {

        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ExcepcionUsuarioNoEncontrado(id));

        return assembler.toResource(usuario);
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