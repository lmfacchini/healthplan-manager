package nom.healthplan.manager.controller;


import nom.healthplanmanager.HealthplanManagerApplication;
import nom.healthplanmanager.controller.impl.DocumentControllerImpl;
import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static nom.healthplanmanager.constant.DocumentType.CPF;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HealthplanManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @InjectMocks
    private DocumentControllerImpl documentController;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testGetAllDocumentsByBeneficiaryId() throws Exception {
        DocumentDto document1 = new DocumentDto(1L, CPF, "0000000000");

        Set<DocumentDto> documents = Set.of(document1);
        when(documentService.getAllDocumentsByBeneficiaryId(1L)).thenReturn(documents);


        mockMvc.perform(get("/document/listAllByBeneficiaryId/{beneficiaryId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("CPF"));
    }


}
