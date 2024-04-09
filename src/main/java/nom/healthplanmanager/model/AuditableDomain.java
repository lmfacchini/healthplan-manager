package nom.healthplanmanager.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditableDomain  implements Domain{

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
