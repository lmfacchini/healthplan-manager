package nom.healthplanmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nom.healthplanmanager.constant.DocumentType;

@Data
@EqualsAndHashCode(of = "id")
public class DocumentDto extends AuditableDto {

    private Long id;

    private DocumentType type;

    private String description;


}
