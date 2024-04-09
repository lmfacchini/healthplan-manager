package nom.healthplan.manager.controller;


import nom.healthplanmanager.HealthplanManagerApplication;
import nom.healthplanmanager.controller.impl.BeneficiaryControllerImpl;
import nom.healthplanmanager.dto.BeneficiaryDto;
import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.service.BeneficiaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static nom.healthplanmanager.constant.DocumentType.CPF;
import static nom.healthplanmanager.constant.DocumentType.RG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HealthplanManagerApplication.class)
@AutoConfigureMockMvc
public class BeneficiaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeneficiaryService beneficiaryService;

    @InjectMocks
    private BeneficiaryControllerImpl beneficiaryController;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testListAllBeneficiaries() throws Exception {

        DocumentDto document1 = new DocumentDto(1L, CPF, "0000000000");
        DocumentDto document2 = new DocumentDto(2L, RG, "0000000000");
        BeneficiaryDto beneficiary1 = new BeneficiaryDto(1L, "Hange Zoe", LocalDate.now(), List.of(document1, document2));
        BeneficiaryDto beneficiary2 = new BeneficiaryDto(2L, "Eren Yeager", LocalDate.now(), List.of(document1, document2));
        List<BeneficiaryDto> beneficiaries = List.of(beneficiary1, beneficiary2);
        when(beneficiaryService.listAll()).thenReturn(beneficiaries);


        mockMvc.perform(get("/beneficiary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hange Zoe"))
                .andExpect(jsonPath("$[1].name").value("Eren Yeager"));
    }

    @Test
    public void testGetBeneficiaryById() throws Exception {
        DocumentDto document1 = new DocumentDto(1L, CPF, "0000000000");
        DocumentDto document2 = new DocumentDto(2L, RG, "0000000000");
        BeneficiaryDto beneficiary = new BeneficiaryDto(1L, "Hange Zoe", LocalDate.now(), List.of(document1, document2));
        when(beneficiaryService.getBeneficiaryById(1L)).thenReturn(beneficiary);


        mockMvc.perform(get("/beneficiary/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hange Zoe"));
    }

    @Test
    public void testCreateBeneficiary() throws Exception {
        String jsonBody = "{\"name\": \"Hange Zoe\", \"birthDate\": \"2013-04-07\", \"documents\": [{\"type\": \"CPF\", \"description\": \"00000000000\"}]}";
        DocumentDto document1 = new DocumentDto(1L, CPF, "0000000000");
        DocumentDto document2 = new DocumentDto(2L, RG, "0000000000");
        when(beneficiaryService.create(any(BeneficiaryDto.class))).thenReturn(new BeneficiaryDto(1L, "Hange Zoe", LocalDate.now(), List.of(document1, document2)));

        mockMvc.perform(post("/beneficiary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/beneficiary/1"));
    }

    @Test
    public void testUpdateBeneficiary() throws Exception {

        String jsonBody = "{\"id\": 1, \"name\": \"Saitama\", \"birthDate\": \"2016-07-16\", \"documents\": [{\"type\": \"CPF\", \"description\": \"00000000000\"}]}";

        doNothing().when(beneficiaryService).update(any(BeneficiaryDto.class));



        mockMvc.perform(put("/beneficiary", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBeneficiary() throws Exception {

        doNothing().when(beneficiaryService).delete(1L);


        mockMvc.perform(delete("/beneficiary/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
