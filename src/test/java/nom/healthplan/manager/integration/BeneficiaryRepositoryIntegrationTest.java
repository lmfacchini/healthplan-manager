package nom.healthplan.manager.integration;

import nom.healthplanmanager.HealthplanManagerApplication;
import nom.healthplanmanager.model.Beneficiary;
import nom.healthplanmanager.repository.BeneficiaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = HealthplanManagerApplication.class)
@ActiveProfiles("utest")
public class BeneficiaryRepositoryIntegrationTest {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Test
    public void testSaveBeneficiary() {

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName("Saitama");
        beneficiary.setBirthDate(LocalDate.now());


        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);


        Optional<Beneficiary> retrievedBeneficiary = beneficiaryRepository.findById(savedBeneficiary.getId());
        assertTrue(retrievedBeneficiary.isPresent());
        assertEquals("Saitama", retrievedBeneficiary.get().getName());
    }

    @Test
    public void testFindById() {

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName("Eren Yeager");
        beneficiary.setBirthDate(LocalDate.now());
        beneficiaryRepository.save(beneficiary);


        Optional<Beneficiary> retrievedBeneficiary = beneficiaryRepository.findById(beneficiary.getId());


        assertTrue(retrievedBeneficiary.isPresent());
        assertEquals("Eren Yeager", retrievedBeneficiary.get().getName());

        beneficiaryRepository.deleteById(beneficiary.getId());
    }
}
