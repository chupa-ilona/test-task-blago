package spring.test_task.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class MonthlyReportDto {
    private int year;
    private int month;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;
    private List<CategoryExpenseDto> expensesByCategory;
}
