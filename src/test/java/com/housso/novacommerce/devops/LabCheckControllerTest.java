package com.hossam.novacommerce.devops;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LabCheckControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void labCheckRequiresApiKey() throws Exception {
        mockMvc.perform(get("/api/devops/lab-check"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void labCheckReturnsStatusWhenApiKeyIsValid() throws Exception {
        mockMvc.perform(get("/api/devops/lab-check").header("X-Lab-Key", "devops-lab-key"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("running"));
    }
}
