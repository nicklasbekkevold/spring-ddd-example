package net.krg.ri.cancerregistry.registry.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.krg.ri.cancerregistry.registry.application.VariableService;
import net.krg.ri.cancerregistry.registry.domain.Variable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/registry/variables")
@Tag(name = "Variables", description = "Cancer Registry Variable management")
class VariableController {

    private final VariableService variableService;

    VariableController(VariableService variableService) {
        this.variableService = variableService;
    }

    @GetMapping
    CollectionModel<EntityModel<VariableResponseDTO>> getAll() {
        List<EntityModel<VariableResponseDTO>> variables = variableService.findAll().stream()
                .map(this::toEntityModel)
                .toList();
        return CollectionModel.of(variables,
                linkTo(methodOn(VariableController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<VariableResponseDTO> getById(@PathVariable UUID id) {
        Variable variable = variableService.findById(id);
        return toEntityModel(variable);
    }

    @PostMapping
    ResponseEntity<EntityModel<VariableResponseDTO>> create(@Valid @RequestBody VariableRequestDTO request) {
        Variable variable = variableService.create(VariableMapper.toDomain(request));
        EntityModel<VariableResponseDTO> model = toEntityModel(variable);
        return ResponseEntity.created(
                linkTo(methodOn(VariableController.class).getById(variable.id())).toUri()
        ).body(model);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        variableService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<VariableResponseDTO> toEntityModel(Variable variable) {
        VariableResponseDTO dto = VariableMapper.toResponse(variable);
        return EntityModel.of(dto,
                linkTo(methodOn(VariableController.class).getById(variable.id())).withSelfRel(),
                linkTo(methodOn(VariableController.class).getAll()).withRel("variables"));
    }
}
