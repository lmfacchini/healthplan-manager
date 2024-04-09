package nom.healthplanmanager.service.impl;

import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.model.Beneficiary;
import nom.healthplanmanager.model.Document;
import nom.healthplanmanager.repository.DocumentRepository;
import nom.healthplanmanager.service.AuditableDomainService;
import nom.healthplanmanager.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl extends AuditableDomainService<Document, DocumentDto> implements DocumentService {

    private final DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }


    @Override
    public Set<DocumentDto> getAllDocumentsByBeneficiaryId(Long beneficiaryId) {
        Set<Document> result = repository.findByBeneficiaryId(beneficiaryId);
        return result == null ? Set.of() : result.stream().map(this::parse).collect(Collectors.toSet());
    }

    @Override
    public Set<DocumentDto> save(final Long beneficiaryId, Collection<DocumentDto> documets) {
        //For Delete
        getAllDocumentsByBeneficiaryId(beneficiaryId).stream().filter(existsDoc->!documets.stream().anyMatch(unkDoc->unkDoc.equals(existsDoc)))
                .map(DocumentDto::getId).forEach(repository::deleteById);
        return documets.stream().map(dto -> save(beneficiaryId, dto)).collect(Collectors.toSet());
    }

    @Override
    public void deleteByBeneficiaryId(Long beneficiaryId) {
        repository.findByBeneficiaryId(beneficiaryId).forEach(repository::delete);
    }

    public DocumentDto save(Long beneficiaryId, DocumentDto dto) {
        Optional<Document> optional = dto.getId() == null ? Optional.empty() : repository.findById(dto.getId());
        Document domain = optional.isPresent() ? optional.get() : parse(dto);

        //if update case
        domain.setBeneficiary(new Beneficiary(beneficiaryId));
        domain.setDescription(dto.getDescription());
        domain.setType(dto.getType());

        domain = repository.save(domain);
        dto.setId(domain.getId());
        return dto;
    }


    @Override
    protected Document parse(DocumentDto dto) {
        Document domain = super.parse(dto);
        domain.setDescription(dto.getDescription());
        domain.setType(dto.getType());
        return domain;
    }

    @Override
    protected DocumentDto parse(Document domain) {
        DocumentDto dto = super.parse(domain);
        dto.setDescription(domain.getDescription());
        dto.setType(domain.getType());
        return dto;
    }
}
