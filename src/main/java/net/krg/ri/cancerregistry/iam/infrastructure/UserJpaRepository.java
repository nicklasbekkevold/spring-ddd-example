package net.krg.ri.cancerregistry.iam.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
}
