package net.krg.ri.cancerregistry.registry.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.krg.ri.cancerregistry.registry.infrastructure.VariableType;

import java.util.List;

public record VariableRequestDTO(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String codeSystem,
        @NotNull VariableType variableType,
        List<String> allowedValues,
        Double minValue,
        Double maxValue,
        String unit
) {}
