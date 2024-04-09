package nom.healthplanmanager.repository;

import nom.healthplanmanager.model.Document;

import java.util.Set;

public interface DocumentRepository {

    Set<Document> findByBeneficiaryId(Long beneficiaryId);
}
