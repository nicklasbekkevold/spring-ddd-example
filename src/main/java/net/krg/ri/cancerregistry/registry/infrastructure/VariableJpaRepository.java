package net.krg.ri.cancerregistry.registry.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface VariableJpaRepository extends JpaRepository<VariableJpaEntity, UUID> {
}
