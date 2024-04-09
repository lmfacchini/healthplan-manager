package nom.healthplanmanager.controller;

import nom.healthplanmanager.dto.BeneficiaryDto;

import java.util.Collection;


public interface BeneficiaryController<T> {
    T create(BeneficiaryDto dto);
    Collection<BeneficiaryDto> listAll();
    BeneficiaryDto getBeneficiaryById(Long id);
    T update(BeneficiaryDto dto);
    T delete(Long id);
}
