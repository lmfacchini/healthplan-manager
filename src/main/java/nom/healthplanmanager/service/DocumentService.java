package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.DocumentDto;

import java.util.Collection;
import java.util.Set;

public interface DocumentService {

    Set<DocumentDto> getAllDocumentsByBeneficiaryId(Long beneficiaryId);

    Set<DocumentDto> save(Long beneficiaryId, Collection<DocumentDto> documets);

    void deleteByBeneficiaryId(Long beneficiaryId);
}
