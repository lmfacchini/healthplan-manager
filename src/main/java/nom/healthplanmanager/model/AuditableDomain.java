package nom.healthplanmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AuditableDomain  implements Domain{

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="crtn_dt", nullable = false)
    private LocalDateTime creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updte_dt", nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    public void prePerist(){
        creationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateDate = LocalDateTime.now();
    }
}
