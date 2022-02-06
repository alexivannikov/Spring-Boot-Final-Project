package root.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import root.validation.Currency;
import root.validation.EntityCreated;
import root.validation.EntityUpdated;
import root.validation.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BankBookDto {
    @Null(groups = EntityCreated.class)
    @NotNull(groups = EntityUpdated.class)
    private Integer id;

    @User
    private String user;

    @NotBlank(message = "Ошибка! Счет не может быть пустым")
    private String number;

    @PositiveOrZero
    @NotNull
    private BigDecimal amount;

    @Currency
    private String currency;

    @Null
    private String amountRUR;
}
