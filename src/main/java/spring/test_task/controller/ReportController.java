package spring.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.test_task.dto.MonthlyReportDto;
import spring.test_task.service.ReportService;

@Tag(name = "Reports management",
        description = "Endpoints for generating financial reports")
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/monthly")
    @Operation(summary = "Get monthly financial report",
            description = "Returns total income, total expenses,"
                    + " balance, and expenses broken down by category for a specific month and year.")
    public MonthlyReportDto getMonthlyReport(
            @RequestParam int year,
            @RequestParam int month) {
        return reportService.getMonthlyReport(year, month);
    }
}
