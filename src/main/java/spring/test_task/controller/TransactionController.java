package spring.test_task.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.test_task.dto.CreateTransactionRequestDto;
import spring.test_task.dto.TransactionDto;
import spring.test_task.dto.TransactionFilterDto;
import spring.test_task.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestBody @Valid CreateTransactionRequestDto request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping
    public Page<TransactionDto> getAllTransactions(
            @ModelAttribute TransactionFilterDto filter,
            @PageableDefault(size = 20) Pageable pageable) {
        return transactionService.getAllTransactions(filter, pageable);
    }

    @GetMapping("/{id}")
    public TransactionDto getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable Long id,
                                            @RequestBody @Valid CreateTransactionRequestDto request) {
        return transactionService.updateTransaction(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
