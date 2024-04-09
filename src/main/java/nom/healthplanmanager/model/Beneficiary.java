package nom.healthplanmanager.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Beneficiary extends AuditableDomain{

    private Long id;

    private String name;

    private LocalDate birthDate;
}
