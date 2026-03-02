package net.krg.ri.cancerregistry.iam.web;

import net.krg.ri.cancerregistry.iam.domain.Role;

import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        Set<Role> roles
) {}
