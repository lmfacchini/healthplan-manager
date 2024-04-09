package nom.healthplan.manager.service;

import nom.healthplanmanager.dto.BeneficiaryDto;
import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.model.Beneficiary;
import nom.healthplanmanager.model.Document;
import nom.healthplanmanager.repository.BeneficiaryRepository;
import nom.healthplanmanager.service.BeneficiaryService;
import nom.healthplanmanager.service.DocumentService;
import nom.healthplanmanager.service.impl.BeneficiaryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static nom.healthplanmanager.constant.DocumentType.CPF;
import static nom.healthplanmanager.constant.DocumentType.RG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeneficiaryServiceTest {

    private static BeneficiaryService beneficiaryService;

    private static DocumentService documentService;
    private static BeneficiaryRepository beneficiaryRepository;

    @BeforeAll
    public static void setUp() {
        documentService = mock(DocumentService.class);
        beneficiaryRepository = mock(BeneficiaryRepository.class);
        beneficiaryService = new BeneficiaryServiceImpl(beneficiaryRepository, documentService);
    }

    @Test
    public void testListAll() {

        Beneficiary beneficiary1 = new Beneficiary(1L, "Hange Zoe", LocalDate.now());
        Beneficiary beneficiary2 = new Beneficiary(2L, "Saitama", LocalDate.now());
        List<Beneficiary> beneficiaries = List.of(beneficiary1, beneficiary2);
        when(beneficiaryRepository.findAll()).thenReturn(beneficiaries);

        List<BeneficiaryDto> result = new ArrayList<>(beneficiaryService.listAll());


        assertEquals(2, result.size());

        assertEquals("Hange Zoe", result.get(0).getName());
        assertEquals("Saitama", result.get(1).getName());
    }

    @Test
    public void testGetBeneficiaryById() {

        Beneficiary beneficiary = new Beneficiary(1L, "Hange Zoe", LocalDate.now());
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));


        BeneficiaryDto result = beneficiaryService.getBeneficiaryById(1L);


        assertEquals("Hange Zoe", result.getName());
    }

    @Test
    public void testCreate(){
        DocumentDto document1 = new DocumentDto(null, CPF, "000000000");
        DocumentDto document2 = new DocumentDto(null, RG, "000000000");
        List<DocumentDto> documents = List.of(document1, document2);
        Beneficiary beneficiary = new Beneficiary(1L, "Hange Zoe", LocalDate.now());
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(beneficiary);


        BeneficiaryDto result = beneficiaryService.create(new BeneficiaryDto(null, "Hange Zoe", LocalDate.now(), documents));

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void testUpdate(){
        DocumentDto document1 = new DocumentDto(1L, CPF, "000000000");
        DocumentDto document2 = new DocumentDto(null, RG, "000000000");
        List<DocumentDto> documents = List.of(document1, document2);
        Beneficiary beneficiary = new Beneficiary(1L, "Hange Zoe", LocalDate.now());
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(beneficiary);


        beneficiaryService.update(new BeneficiaryDto(1L, "Saitama", LocalDate.now(), documents));

    }

    @Test
    public void testDelete(){
        beneficiaryService.delete(1L);

    }
}
