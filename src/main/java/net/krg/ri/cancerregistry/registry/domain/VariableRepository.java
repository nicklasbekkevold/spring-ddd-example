package net.krg.ri.cancerregistry.registry.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VariableRepository {
    Optional<Variable> findById(UUID id);
    List<Variable> findAll();
    Variable save(Variable variable);
    void deleteById(UUID id);
}
