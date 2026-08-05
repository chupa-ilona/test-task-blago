package spring.test_task.dto;

import java.time.LocalDate;
import lombok.Data;
import spring.test_task.entity.CategoryType;

@Data
public class TransactionFilterDto {
    private CategoryType type;
    private Long categoryId;
    private LocalDate from;
    private LocalDate to;
}
