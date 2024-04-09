package nom.healthplanmanager.service.impl;

import nom.healthplanmanager.dto.BeneficiaryDto;
import nom.healthplanmanager.model.Beneficiary;
import nom.healthplanmanager.repository.BeneficiaryRepository;
import nom.healthplanmanager.service.AuditableDomainService;
import nom.healthplanmanager.service.BeneficiaryService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BeneficiaryServiceImpl extends AuditableDomainService<Beneficiary, BeneficiaryDto> implements BeneficiaryService {

    private final BeneficiaryRepository repository;

    public BeneficiaryServiceImpl(BeneficiaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(BeneficiaryDto dto) {
        Beneficiary domain = parse(dto);
        repository.save(domain);
    }

    @Override
    public Set<BeneficiaryDto> listAll() {
        Set<Beneficiary> result = repository.findAll();
        return result == null ? Set.of() : parse(result);
    }

    @Override
    public BeneficiaryDto getBeneficiaryById(Long id) {
        Optional<Beneficiary> result = repository.findById(id);
        return result.isPresent() ? parse(result.get()) : null;
    }

    @Override
    public void update(BeneficiaryDto dto) {
        Beneficiary domain = parse(dto);
        repository.save(domain);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
