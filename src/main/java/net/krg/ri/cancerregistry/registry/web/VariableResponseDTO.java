package net.krg.ri.cancerregistry.registry.web;

import java.util.List;
import java.util.UUID;

public record VariableResponseDTO(
        UUID id,
        String name,
        String description,
        String codeSystem,
        String variableType,
        List<String> allowedValues,
        Double minValue,
        Double maxValue,
        String unit
) {}
