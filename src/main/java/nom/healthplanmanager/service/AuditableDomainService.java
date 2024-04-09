package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.AuditableDto;
import nom.healthplanmanager.model.AuditableDomain;

public abstract class AuditableDomainService<DOMAIN extends AuditableDomain, DTO extends AuditableDto> extends DomainService<DOMAIN, DTO>{

    @Override
    protected DOMAIN parse(DTO dto) {
        DOMAIN domain = super.parse(dto);
        domain.setCreationDate(dto.getCreationDate());
        domain.setUpdateDate(dto.getUpdateDate());
        return domain;
    }

    @Override
    protected DTO parse(DOMAIN domain) {
        DTO dto = super.parse(domain);
        dto.setCreationDate(domain.getCreationDate());
        dto.setUpdateDate(domain.getUpdateDate());
        return dto;
    }
}
