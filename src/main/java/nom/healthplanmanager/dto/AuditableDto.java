package nom.healthplanmanager.dto;

import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditableDto implements DataTransferObject{

    @Null
    private LocalDateTime creationDate;

    @Null
    private LocalDateTime updateDate;
}
