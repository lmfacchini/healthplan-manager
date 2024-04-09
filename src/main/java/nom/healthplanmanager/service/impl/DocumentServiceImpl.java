package nom.healthplanmanager.service.impl;

import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.model.Document;
import nom.healthplanmanager.repository.DocumentRepository;
import nom.healthplanmanager.service.AuditableDomainService;
import nom.healthplanmanager.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DocumentServiceImpl extends AuditableDomainService<Document, DocumentDto> implements DocumentService {

    private final DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }


    @Override
    public Set<DocumentDto> getAllDocumentsByBeneficiaryId(Long beneficiaryId) {
        Set<Document> result = repository.findByBeneficiaryId(beneficiaryId);
        return result == null ? Set.of() : parse(result);
    }
}
