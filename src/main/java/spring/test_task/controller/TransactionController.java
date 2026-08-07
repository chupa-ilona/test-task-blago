package spring.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Transaction management",
        description = "Endpoints for managing incomes and expenses")
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new transaction",
            description = "Adds a new income or expense record linked to an existing category.")
    public TransactionDto createTransaction(@RequestBody @Valid CreateTransactionRequestDto request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping
    @Operation(summary = "Get all transactions",
            description = "Retrieves a paginated list of transactions. "
                    + "Supports filtering by category, type, and date range.")
    public Page<TransactionDto> getAllTransactions(
            @ModelAttribute TransactionFilterDto filter,
            @PageableDefault(size = 20) Pageable pageable) {
        return transactionService.getAllTransactions(filter, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID",
            description = "Retrieves a specific transaction record by its ID.")
    public TransactionDto getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transaction",
            description = "Updates an existing transaction record by its ID.")
    public TransactionDto updateTransaction(@PathVariable Long id,
                                            @RequestBody @Valid CreateTransactionRequestDto request) {
        return transactionService.updateTransaction(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete transaction",
            description = "Soft deletes a transaction by its ID.")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
