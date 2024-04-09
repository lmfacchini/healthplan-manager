package nom.healthplan.manager.service;

import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.model.Beneficiary;
import nom.healthplanmanager.model.Document;
import nom.healthplanmanager.repository.DocumentRepository;
import nom.healthplanmanager.service.DocumentService;
import nom.healthplanmanager.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static nom.healthplanmanager.constant.DocumentType.CPF;
import static nom.healthplanmanager.constant.DocumentType.RG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentServiceTest {

    private static DocumentService documentService;
    private static DocumentRepository documentRepository;

    @BeforeAll
    public static void setUp() {
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    @Test
    public void testGetAllDocumentsByBeneficiaryId() {

        Document document1 = new Document(1L, CPF, "000000000", new Beneficiary());
        Document document2 = new Document(2L, RG, "000000000", new Beneficiary());
        Set<Document> documents = Set.of(document1, document2);
        when(documentRepository.findByBeneficiaryId(1L)).thenReturn(documents);


        List<DocumentDto> result = new ArrayList<>(documentService.getAllDocumentsByBeneficiaryId(1L));


        assertEquals(2, result.size());
        assertEquals(CPF, result.get(0).getType());
        assertEquals(RG, result.get(1).getType());
    }


    @Test
    public void testSave() {

        Document document1 = new Document(1L, CPF, "000000000", new Beneficiary(1L));


        when(documentRepository.save(any(Document.class))).thenReturn(document1);



        List<DocumentDto> result = new ArrayList<>(documentService.save(1L, Set.of(new DocumentDto(null, CPF, "0000000"))));


        assertEquals(1, result.size());
        assertEquals(CPF, result.get(0).getType());


    }

    @Test
    public void testDeleteByBeneficiaryId(){
        documentService.deleteByBeneficiaryId(1L);
    }
}
