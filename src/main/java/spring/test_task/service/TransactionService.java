package spring.test_task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.test_task.dto.CreateTransactionRequestDto;
import spring.test_task.dto.TransactionDto;
import spring.test_task.dto.TransactionFilterDto;

public interface TransactionService {

    TransactionDto createTransaction(CreateTransactionRequestDto request);

    Page<TransactionDto> getAllTransactions(TransactionFilterDto filter, Pageable pageable);

    TransactionDto getTransactionById(Long id);

    TransactionDto updateTransaction(Long id, CreateTransactionRequestDto request);

    void deleteTransaction(Long id);
}
