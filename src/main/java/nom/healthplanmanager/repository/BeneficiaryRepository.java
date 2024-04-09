package nom.healthplanmanager.repository;

import nom.healthplanmanager.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    Beneficiary save(Beneficiary beneficiary);
    void deleteById(Long id);
}
