package spring.test_task.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate transactionDate;
    private String categoryName;
}
