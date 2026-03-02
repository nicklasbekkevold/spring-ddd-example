package net.krg.ri.cancerregistry.iam.web;

import net.krg.ri.cancerregistry.iam.domain.User;

import java.util.Set;

class UserMapper {

    private UserMapper() {}

    static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRoles());
    }

    static User toDomain(UserRequestDTO dto) {
        return new User(null, dto.username(), dto.email(),
                dto.roles() != null ? dto.roles() : Set.of());
    }
}
