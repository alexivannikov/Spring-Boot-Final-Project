package root.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bank_book", schema = "bank")
public class BankBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_email", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency", referencedColumnName = "id")
    private CurrencyEntity currency;
}
