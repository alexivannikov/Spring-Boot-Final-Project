package root.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction", schema = "bank")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "source_bank_book_id", referencedColumnName = "id")
    private BankBookEntity sourceBankBookId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "target_bank_book_id", referencedColumnName = "id")
    private BankBookEntity targetBankBookId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "inititation_date", nullable = false)
    private LocalDateTime initiationDate;

    @Column(name = "completion_date", nullable = false)
    private LocalDateTime completionDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private StatusEntity status;
}
