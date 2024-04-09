package nom.healthplan.manager.integration;

import nom.healthplanmanager.HealthplanManagerApplication;
import nom.healthplanmanager.dto.DocumentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static nom.healthplanmanager.constant.DocumentType.CPF;
import static nom.healthplanmanager.constant.DocumentType.RG;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HealthplanManagerApplication.class)
@AutoConfigureMockMvc
public class DocumentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListAllDocumentsByBeneficiaryId() throws Exception {
        String jsonRequest = "{\"name\":\"Eren Yeager\",\"birthDate\": \"2013-04-07\", \"documents\": [{\"type\": \"CPF\", \"description\": \"00000000000\"}]}";
        mockMvc.perform(post("/beneficiary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/document/listAllByBeneficiaryId/{beneficiaryId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("CPF"));
    }
}
