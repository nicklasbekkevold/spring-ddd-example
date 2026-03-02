package net.krg.ri.cancerregistry.registry.infrastructure;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import net.krg.ri.cancerregistry.common.infrastructure.GenericDAO;

import java.util.List;

@Entity
@Table(name = "variables")
public class VariableJpaEntity extends GenericDAO {

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String codeSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "variable_type", nullable = false)
    private VariableType variableType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "variable_allowed_values", joinColumns = @JoinColumn(name = "variable_id"))
    @Column(name = "allowed_value")
    private List<String> allowedValues;

    private Double minValue;
    private Double maxValue;
    private String unit;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCodeSystem() { return codeSystem; }
    public void setCodeSystem(String codeSystem) { this.codeSystem = codeSystem; }

    public VariableType getVariableType() { return variableType; }
    public void setVariableType(VariableType variableType) { this.variableType = variableType; }

    public List<String> getAllowedValues() { return allowedValues; }
    public void setAllowedValues(List<String> allowedValues) { this.allowedValues = allowedValues; }

    public Double getMinValue() { return minValue; }
    public void setMinValue(Double minValue) { this.minValue = minValue; }

    public Double getMaxValue() { return maxValue; }
    public void setMaxValue(Double maxValue) { this.maxValue = maxValue; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
