package net.krg.ri.cancerregistry.registry.domain;

import java.util.UUID;

public sealed interface Variable permits CategoricalVariable, NumericVariable {
    UUID id();
    String name();
    String description();
    String codeSystem();
}
