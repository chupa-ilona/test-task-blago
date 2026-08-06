package spring.test_task.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryExpenseDto {
    private String category;
    private BigDecimal amount;
}