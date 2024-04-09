package nom.healthplanmanager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditableDto implements DataTransferObject{

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
