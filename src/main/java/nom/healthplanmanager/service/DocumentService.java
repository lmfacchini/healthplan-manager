package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.DocumentDto;

import java.util.Set;

public interface DocumentService {

    Set<DocumentDto> getAllDocumentsByBeneficiaryId(Long beneficiaryId);
}
