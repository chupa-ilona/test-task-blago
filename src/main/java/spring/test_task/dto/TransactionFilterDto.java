package spring.test_task.dto;

import lombok.Data;
import spring.test_task.entity.CategoryType;
import java.time.LocalDate;

@Data
public class TransactionFilterDto {
    private CategoryType type;
    private Long categoryId;
    private LocalDate from;
    private LocalDate to;
}
