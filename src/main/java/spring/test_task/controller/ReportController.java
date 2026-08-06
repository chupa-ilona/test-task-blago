package spring.test_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.test_task.dto.MonthlyReportDto;
import spring.test_task.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/monthly")
    public MonthlyReportDto getMonthlyReport(
            @RequestParam int year,
            @RequestParam int month) {
        return reportService.getMonthlyReport(year, month);
    }
}