package com.example.demo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrdenRecursoAssembler implements ResourceAssembler<Orden, Resource<Orden>> {

    @Override
    public Resource<Orden> toResource(Orden orden) {

        // link incondicional de un item recurso y agregado al root

        Resource<Orden> ordenRecurso = new Resource<>(orden,
                linkTo(methodOn(ControladorOrden.class).one(orden.getId())).withSelfRel(),
                linkTo(methodOn(ControladorOrden.class).all()).withRel("ordernes")
        );

        // Link condicional basado en el estado de la orden

        if (orden.getEstado() == Estado.IN_PROGRESS) {
            ordenRecurso.add(
                    linkTo(methodOn(ControladorOrden.class)
                            .cancel(orden.getId())).withRel("cancel"));
            ordenRecurso.add(
                    linkTo(methodOn(ControladorOrden.class)
                            .complete(orden.getId())).withRel("completo"));
        }

        return ordenRecurso;
    }
}
