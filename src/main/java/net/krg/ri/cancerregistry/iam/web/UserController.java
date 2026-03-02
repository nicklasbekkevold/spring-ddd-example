package net.krg.ri.cancerregistry.iam.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.krg.ri.cancerregistry.iam.application.UserService;
import net.krg.ri.cancerregistry.iam.domain.User;
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
@RequestMapping("/api/v1/iam/users")
@Tag(name = "Users", description = "Identity & Access Management")
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    CollectionModel<EntityModel<UserResponseDTO>> getAll() {
        List<EntityModel<UserResponseDTO>> users = userService.findAll().stream()
                .map(this::toEntityModel)
                .toList();
        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<UserResponseDTO> getById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return toEntityModel(user);
    }

    @PostMapping
    ResponseEntity<EntityModel<UserResponseDTO>> create(@Valid @RequestBody UserRequestDTO request) {
        User user = userService.create(UserMapper.toDomain(request));
        EntityModel<UserResponseDTO> model = toEntityModel(user);
        return ResponseEntity.created(
                linkTo(methodOn(UserController.class).getById(user.getId())).toUri()
        ).body(model);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<UserResponseDTO> toEntityModel(User user) {
        UserResponseDTO dto = UserMapper.toResponse(user);
        return EntityModel.of(dto,
                linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"));
    }
}
