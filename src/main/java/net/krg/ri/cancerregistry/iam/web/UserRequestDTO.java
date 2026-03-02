package net.krg.ri.cancerregistry.iam.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import net.krg.ri.cancerregistry.iam.domain.Role;

import java.util.Set;

public record UserRequestDTO(
        @NotBlank String username,
        @NotBlank @Email String email,
        Set<Role> roles
) {}
