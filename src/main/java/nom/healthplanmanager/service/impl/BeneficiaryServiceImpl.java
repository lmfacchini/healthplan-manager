package nom.healthplanmanager.service.impl;

import nom.healthplanmanager.dto.BeneficiaryDto;
import nom.healthplanmanager.model.Beneficiary;
import nom.healthplanmanager.repository.BeneficiaryRepository;
import nom.healthplanmanager.service.AuditableDomainService;
import nom.healthplanmanager.service.BeneficiaryService;
import nom.healthplanmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nom.healthplanmanager.exception.BusinessException;

@Service
public class BeneficiaryServiceImpl extends AuditableDomainService<Beneficiary, BeneficiaryDto> implements BeneficiaryService {

    private final BeneficiaryRepository repository;


    private final DocumentService documentService;

    public BeneficiaryServiceImpl(BeneficiaryRepository repository, DocumentService documentService) {
        this.repository = repository;
        this.documentService = documentService;
    }

    @Override
    public BeneficiaryDto create(BeneficiaryDto dto) {
        Beneficiary domain = parse(dto);

        domain = repository.save(domain);
        documentService.save(domain.getId(), dto.getDocuments());
        dto.setId(domain.getId());
        return dto;
    }

    @Override
    public List<BeneficiaryDto> listAll() {
        List<Beneficiary> result = repository.findAll();
        return result == null ? List.of() : result.stream().map(this::parse).collect(Collectors.toList());
    }

    @Override
    public BeneficiaryDto getBeneficiaryById(Long id) {
        Optional<Beneficiary> result = repository.findById(id);
        return result.isPresent() ? parse(result.get()) : null;
    }

    @Override
    public void update(BeneficiaryDto dto) {
        Optional<Beneficiary> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new BusinessException("B00001");

        }

        Beneficiary domain = optional.get();
        domain.setName(dto.getName());
        domain.setBirthDate(dto.getBirthDate());

        repository.save(domain);
    }

    @Override
    public void delete(Long id) {
        documentService.deleteByBeneficiaryId(id);
        repository.deleteById(id);
    }

    @Override
    protected Beneficiary parse(BeneficiaryDto dto) {
        Beneficiary domain = super.parse(dto);
        domain.setBirthDate(dto.getBirthDate());
        domain.setName(dto.getName());
        return domain;
    }

    @Override
    protected BeneficiaryDto parse(Beneficiary domain) {
        BeneficiaryDto dto = super.parse(domain);
        dto.setBirthDate(domain.getBirthDate());
        dto.setName(domain.getName());
        return dto;
    }
}
