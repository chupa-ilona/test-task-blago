package spring.test_task.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateTransactionRequestDto {
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255)
    private String description;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Transaction date cannot be blank")
    private LocalDate transactionDate;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}
