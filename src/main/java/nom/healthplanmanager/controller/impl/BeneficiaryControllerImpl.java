package nom.healthplanmanager.controller.impl;

import nom.healthplanmanager.controller.BeneficiaryController;
import nom.healthplanmanager.dto.BeneficiaryDto;
import nom.healthplanmanager.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RequestMapping("beneficiary")
@RestController
public class BeneficiaryControllerImpl implements BeneficiaryController<ResponseEntity> {


    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping
    @Override
    public ResponseEntity create(@RequestBody BeneficiaryDto dto) {
        beneficiaryService.create(dto);
        return ResponseEntity.created(URI.create("/" + dto.getId())).build();
    }

    @GetMapping
    @Override
    public Collection<BeneficiaryDto> listAll() {
        return beneficiaryService.listAll();
    }

    @GetMapping("{id}")
    @Override
    public BeneficiaryDto getBeneficiaryById(@PathVariable Long id) {
        return beneficiaryService.getBeneficiaryById(id);
    }

    @PutMapping
    @Override
    public ResponseEntity update(@RequestBody BeneficiaryDto dto) {
        beneficiaryService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity delete(@PathVariable Long id) {
        beneficiaryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
