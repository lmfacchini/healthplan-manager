package nom.healthplan.manager.integration;

import nom.healthplanmanager.HealthplanManagerApplication;
import nom.healthplanmanager.model.Document;
import nom.healthplanmanager.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = HealthplanManagerApplication.class)
@ActiveProfiles("utest")
public class DocumentRepositoryIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void testFindByBeneficiaryId(){
        Set<Document> result = documentRepository.findByBeneficiaryId(1L);

        assertNotNull(result);
        assertEquals(0, result.size());


    }
}
