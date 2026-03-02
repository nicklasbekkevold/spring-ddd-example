package net.krg.ri.cancerregistry.registry.web;

import net.krg.ri.cancerregistry.registry.domain.CategoricalVariable;
import net.krg.ri.cancerregistry.registry.domain.NumericVariable;
import net.krg.ri.cancerregistry.registry.domain.Variable;
import net.krg.ri.cancerregistry.registry.infrastructure.VariableType;

class VariableMapper {

    private VariableMapper() {}

    static VariableResponseDTO toResponse(Variable variable) {
        return switch (variable) {
            case CategoricalVariable cv -> new VariableResponseDTO(
                    cv.id(), cv.name(), cv.description(), cv.codeSystem(),
                    "CATEGORICAL", cv.allowedValues(), null, null, null
            );
            case NumericVariable nv -> new VariableResponseDTO(
                    nv.id(), nv.name(), nv.description(), nv.codeSystem(),
                    "NUMERIC", null, nv.minValue(), nv.maxValue(), nv.unit()
            );
        };
    }

    static Variable toDomain(VariableRequestDTO dto) {
        return switch (dto.variableType()) {
            case CATEGORICAL -> new CategoricalVariable(
                    null, dto.name(), dto.description(), dto.codeSystem(),
                    dto.allowedValues()
            );
            case NUMERIC -> new NumericVariable(
                    null, dto.name(), dto.description(), dto.codeSystem(),
                    dto.minValue(), dto.maxValue(), dto.unit()
            );
        };
    }
}
