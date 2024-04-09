package nom.healthplan.manager.integration;

import nom.healthplanmanager.HealthplanManagerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = HealthplanManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class BeneficiaryControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;




    @Test
    public void testCreateBeneficiary() throws Exception {
        String jsonRequest = "{\"name\":\"Eren Yeager\",\"birthDate\": \"2013-04-07\", \"documents\": [{\"type\": \"CPF\", \"description\": \"00000000000\"}]}";
        mockMvc.perform(post("/beneficiary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListAllBeneficiaries() throws Exception {
        mockMvc.perform(get("/beneficiary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Eren Yeager"));
    }

    @Test
    public void testGetDocumentsByBeneficiaryId() throws Exception {
        mockMvc.perform(get("/beneficiary/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Eren Yeager"));
    }

    @Test
    public void testUpdateBeneficiary() throws Exception {
        String jsonRequest = "{\"id\": 1, \"name\": \"Saitama\", \"birthDate\": \"2016-07-16\", \"documents\": [{\"type\": \"CPF\", \"description\": \"00000000000\"}]}";
        mockMvc.perform(put("/beneficiary", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBeneficiary() throws Exception {
        mockMvc.perform(delete("/beneficiary/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
