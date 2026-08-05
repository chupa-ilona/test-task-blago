package spring.test_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.test_task.dto.TransactionFilterDto;
import spring.test_task.entity.Transaction;

public interface TransactionRepositoryCustom {
    Page<Transaction> findFiltered(TransactionFilterDto filter, Pageable pageable);
}