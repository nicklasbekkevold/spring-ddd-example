package net.krg.ri.cancerregistry.registry.domain;

import java.util.List;
import java.util.UUID;

public record CategoricalVariable(
        UUID id,
        String name,
        String description,
        String codeSystem,
        List<String> allowedValues
) implements Variable {}
