package nom.healthplanmanager.model;

import lombok.Data;
import nom.healthplanmanager.constant.DocumentType;

@Data
public class Document extends AuditableDomain{

    private Long id;

    private DocumentType type;

    private String description;
}
