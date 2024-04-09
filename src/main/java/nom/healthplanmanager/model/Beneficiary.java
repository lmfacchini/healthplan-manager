package nom.healthplanmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bnfcry")
public class Beneficiary extends AuditableDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "nme", length = 90, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "brth_dt", nullable = false)
    private LocalDate birthDate;

    public Beneficiary(Long id) {
        this.id = id;
    }
}
