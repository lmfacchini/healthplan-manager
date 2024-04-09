package nom.healthplanmanager.repository;

import nom.healthplanmanager.model.Beneficiary;

import java.util.Optional;
import java.util.Set;

public interface BeneficiaryRepository {

    Beneficiary save(Beneficiary beneficiary);
    Set<Beneficiary> findAll();
    Optional<Beneficiary> findById(Long id);
    void deleteById(Long id);
}
