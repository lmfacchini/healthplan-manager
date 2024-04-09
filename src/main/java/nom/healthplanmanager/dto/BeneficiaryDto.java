package nom.healthplanmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
public class BeneficiaryDto extends AuditableDto {


    private Long id;

    private String name;

    private LocalDate birthDate;

}
