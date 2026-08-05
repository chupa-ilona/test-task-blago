package spring.test_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.test_task.dto.CreateTransactionRequestDto;
import spring.test_task.dto.TransactionDto;
import spring.test_task.dto.TransactionFilterDto;
import spring.test_task.entity.Category;
import spring.test_task.entity.Transaction;
import spring.test_task.exception.EntityNotFoundException;
import spring.test_task.mapper.TransactionMapper;
import spring.test_task.repository.CategoryRepository;
import spring.test_task.repository.TransactionRepository;
import spring.test_task.service.TransactionService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionDto createTransaction(CreateTransactionRequestDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: "
                        + request.getCategoryId()));
        Transaction transaction = transactionMapper.toEntity(request, category);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction);

    }

    @Override
    public Page<TransactionDto> getAllTransactions(TransactionFilterDto filter, Pageable pageable) {
        return transactionRepository.findFiltered(filter, pageable)
                .map(transactionMapper::toDto); // Page має зручний вбудований метод map()
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        return transactionMapper.toDto(transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: "
                        + id)));
    }

    @Override
    @Transactional
    public TransactionDto updateTransaction(Long id, CreateTransactionRequestDto request) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + request.getCategoryId()));

        transactionMapper.updateEntity(request, category, transaction);
        return transactionMapper.toDto(transactionRepository.save(transaction));

    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
