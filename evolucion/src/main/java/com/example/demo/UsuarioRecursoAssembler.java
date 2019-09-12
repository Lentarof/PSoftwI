package com.example.demo;



import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class UsuarioRecursoAssembler implements ResourceAssembler<Usuario, Resource<Usuario>>  {

    @Override
    public Resource<Usuario> toResource(Usuario usuario) {

        return new Resource<>(usuario,
                linkTo(methodOn(ControladorUsuario.class).one(usuario.getId())).withSelfRel(),
                linkTo(methodOn(ControladorUsuario.class).all()).withRel("usuario"));
    }
}
