package net.krg.ri.cancerregistry.registry.infrastructure;

import net.krg.ri.cancerregistry.registry.domain.CategoricalVariable;
import net.krg.ri.cancerregistry.registry.domain.NumericVariable;
import net.krg.ri.cancerregistry.registry.domain.Variable;
import net.krg.ri.cancerregistry.registry.domain.VariableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class VariableRepositoryImpl implements VariableRepository {

    private final VariableJpaRepository jpaRepository;

    VariableRepositoryImpl(VariableJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Variable> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Variable> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Variable save(Variable variable) {
        VariableJpaEntity entity = toEntity(variable);
        VariableJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Variable toDomain(VariableJpaEntity entity) {
        return switch (entity.getVariableType()) {
            case CATEGORICAL -> new CategoricalVariable(
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getCodeSystem(),
                    entity.getAllowedValues()
            );
            case NUMERIC -> new NumericVariable(
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getCodeSystem(),
                    entity.getMinValue(),
                    entity.getMaxValue(),
                    entity.getUnit()
            );
        };
    }

    private VariableJpaEntity toEntity(Variable variable) {
        VariableJpaEntity entity = new VariableJpaEntity();
        if (variable.id() != null) {
            entity.setId(variable.id());
        }
        entity.setName(variable.name());
        entity.setDescription(variable.description());
        entity.setCodeSystem(variable.codeSystem());
        switch (variable) {
            case CategoricalVariable cv -> {
                entity.setVariableType(VariableType.CATEGORICAL);
                entity.setAllowedValues(cv.allowedValues());
            }
            case NumericVariable nv -> {
                entity.setVariableType(VariableType.NUMERIC);
                entity.setMinValue(nv.minValue());
                entity.setMaxValue(nv.maxValue());
                entity.setUnit(nv.unit());
            }
        }
        return entity;
    }
}
