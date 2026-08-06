package spring.test_task.service;

import spring.test_task.dto.MonthlyReportDto;

public interface ReportService {

    MonthlyReportDto getMonthlyReport(int year, int month);

}
