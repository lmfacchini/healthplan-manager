package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.BeneficiaryDto;

import java.util.List;

public interface BeneficiaryService {

    BeneficiaryDto create(BeneficiaryDto dto);
    List<BeneficiaryDto> listAll();
    BeneficiaryDto getBeneficiaryById(Long id);
    void update(BeneficiaryDto dto);
    void delete(Long id);
}
