package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.BeneficiaryDto;

import java.util.Set;

public interface BeneficiaryService {

    void create(BeneficiaryDto dto);
    Set<BeneficiaryDto> listAll();
    BeneficiaryDto getBeneficiaryById(Long id);
    void update(BeneficiaryDto dto);
    void delete(Long id);
}
