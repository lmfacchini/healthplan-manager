package nom.healthplanmanager.repository;

import nom.healthplanmanager.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Set<Document> findByBeneficiaryId(Long beneficiaryId);
}
