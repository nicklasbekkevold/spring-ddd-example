package net.krg.ri.cancerregistry.registry.domain;

import java.util.UUID;

public record NumericVariable(
        UUID id,
        String name,
        String description,
        String codeSystem,
        Double minValue,
        Double maxValue,
        String unit
) implements Variable {}
