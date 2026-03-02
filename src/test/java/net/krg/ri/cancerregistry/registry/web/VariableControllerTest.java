package net.krg.ri.cancerregistry.registry.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.krg.ri.cancerregistry.registry.application.VariableService;
import net.krg.ri.cancerregistry.registry.domain.CategoricalVariable;
import net.krg.ri.cancerregistry.registry.domain.Variable;
import net.krg.ri.cancerregistry.registry.domain.VariableNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VariableController.class)
class VariableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VariableService variableService;

    @Test
    void getAllVariables_returnsOk() throws Exception {
        UUID id = UUID.randomUUID();
        Variable variable = new CategoricalVariable(id, "DiagnosisCode", "Diagnosis", "ICD-10", List.of("C50", "C61"));
        when(variableService.findAll()).thenReturn(List.of(variable));

        mockMvc.perform(get("/api/v1/registry/variables"))
                .andExpect(status().isOk());
    }

    @Test
    void getVariableById_notFound_returns404() throws Exception {
        UUID id = UUID.randomUUID();
        when(variableService.findById(id)).thenThrow(new VariableNotFoundException(id));

        mockMvc.perform(get("/api/v1/registry/variables/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void createVariable_valid_returnsCreated() throws Exception {
        UUID id = UUID.randomUUID();
        Variable created = new CategoricalVariable(id, "DiagnosisCode", "Diagnosis", "ICD-10", List.of("C50"));
        when(variableService.create(any())).thenReturn(created);

        String requestBody = """
                {
                    "name": "DiagnosisCode",
                    "description": "Diagnosis",
                    "codeSystem": "ICD-10",
                    "variableType": "CATEGORICAL",
                    "allowedValues": ["C50"]
                }
                """;

        mockMvc.perform(post("/api/v1/registry/variables")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("DiagnosisCode"));
    }

    @Test
    void createVariable_missingName_returns400() throws Exception {
        String requestBody = """
                {
                    "description": "Diagnosis",
                    "codeSystem": "ICD-10",
                    "variableType": "CATEGORICAL"
                }
                """;

        mockMvc.perform(post("/api/v1/registry/variables")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
