package nom.healthplanmanager.controller;

import nom.healthplanmanager.dto.DocumentDto;

import java.util.Collection;


public interface DocumentController {

    Collection<DocumentDto> listAllDocumentsByBeneficiaryId(Long beneficiaryId);
}
