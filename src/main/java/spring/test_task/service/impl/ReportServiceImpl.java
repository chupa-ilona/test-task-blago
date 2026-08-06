package spring.test_task.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.test_task.dto.CategoryExpenseDto;
import spring.test_task.dto.MonthlyReportDto;
import spring.test_task.entity.CategoryType;
import spring.test_task.entity.Transaction;
import spring.test_task.repository.TransactionRepository;
import spring.test_task.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TransactionRepository transactionRepository;

    @Override
    public MonthlyReportDto getMonthlyReport(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Transaction> transactions = transactionRepository.findAllByDateRange(startDate, endDate);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getCategory() != null && t.getCategory().getType() == CategoryType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getCategory() != null && t.getCategory().getType() == CategoryType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        Map<String, BigDecimal> expenseMap = transactions.stream()
                .filter(t -> t.getCategory() != null && t.getCategory().getType() == CategoryType.EXPENSE)
                .collect(Collectors.toMap(
                        t -> t.getCategory().getName(),
                        Transaction::getAmount,
                        BigDecimal::add
                ));

        List<CategoryExpenseDto> expensesByCategory = expenseMap.entrySet().stream()
                .map(entry -> new CategoryExpenseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        MonthlyReportDto report = new MonthlyReportDto();
        report.setYear(year);
        report.setMonth(month);
        report.setTotalIncome(totalIncome);
        report.setTotalExpense(totalExpense);
        report.setBalance(balance);
        report.setExpensesByCategory(expensesByCategory);

        return report;
    }
}
