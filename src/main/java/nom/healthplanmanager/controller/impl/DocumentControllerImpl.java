package nom.healthplanmanager.controller.impl;

import nom.healthplanmanager.controller.DocumentController;
import nom.healthplanmanager.dto.DocumentDto;
import nom.healthplanmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("document")
@RestController
public class DocumentControllerImpl implements DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("listAllByBeneficiaryId/{beneficiaryId}")
    @Override
    public Collection<DocumentDto> listAllDocumentsByBeneficiaryId(@PathVariable("beneficiaryId") Long beneficiaryId) {
        return documentService.getAllDocumentsByBeneficiaryId(beneficiaryId);
    }
}
