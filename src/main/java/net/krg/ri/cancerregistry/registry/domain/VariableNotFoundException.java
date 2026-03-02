package net.krg.ri.cancerregistry.registry.domain;

import java.util.UUID;

public class VariableNotFoundException extends RuntimeException {
    public VariableNotFoundException(UUID id) {
        super("Variable not found with id: " + id);
    }
}
