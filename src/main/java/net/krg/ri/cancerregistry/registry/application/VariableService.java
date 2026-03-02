package net.krg.ri.cancerregistry.registry.application;

import net.krg.ri.cancerregistry.registry.domain.Variable;
import net.krg.ri.cancerregistry.registry.domain.VariableNotFoundException;
import net.krg.ri.cancerregistry.registry.domain.VariableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class VariableService {

    private final VariableRepository variableRepository;

    VariableService(VariableRepository variableRepository) {
        this.variableRepository = variableRepository;
    }

    public List<Variable> findAll() {
        return variableRepository.findAll();
    }

    public Variable findById(UUID id) {
        return variableRepository.findById(id)
                .orElseThrow(() -> new VariableNotFoundException(id));
    }

    @Transactional
    public Variable create(Variable variable) {
        return variableRepository.save(variable);
    }

    @Transactional
    public void delete(UUID id) {
        findById(id);
        variableRepository.deleteById(id);
    }
}
