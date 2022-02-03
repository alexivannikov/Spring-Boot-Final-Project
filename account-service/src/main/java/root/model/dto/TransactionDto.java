package root.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import root.validation.EntityCreated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {
    @Null(groups = EntityCreated.class)
    private Integer id;

    @Null(groups = EntityCreated.class)
    private String sourceBankBookId;

    @Null(groups = EntityCreated.class)
    private String targetBankBookId;

    @PositiveOrZero
    @NotNull
    private BigDecimal amount;

    @Null(groups = EntityCreated.class)
    private LocalDateTime initiationDate;

    @Null(groups = EntityCreated.class)
    private LocalDateTime completionDate;

    @Null(groups = EntityCreated.class)
    private String status;
}
