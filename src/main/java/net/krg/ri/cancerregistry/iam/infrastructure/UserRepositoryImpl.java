package net.krg.ri.cancerregistry.iam.infrastructure;

import net.krg.ri.cancerregistry.iam.domain.Role;
import net.krg.ri.cancerregistry.iam.domain.User;
import net.krg.ri.cancerregistry.iam.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getRoles());
    }

    private UserJpaEntity toEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles());
        return entity;
    }
}
