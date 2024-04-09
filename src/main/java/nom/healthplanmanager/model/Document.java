package nom.healthplanmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nom.healthplanmanager.constant.DocumentType;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dcmnt")
public class Document extends AuditableDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private DocumentType type;

    @Column(name = "dscrptn")
    private String description;

    @ManyToOne
    @JoinColumn(name = "bnfcry_id", nullable = false)
    private Beneficiary beneficiary;
}
