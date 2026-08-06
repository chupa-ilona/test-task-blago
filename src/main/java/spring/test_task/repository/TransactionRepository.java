package spring.test_task.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.test_task.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {

    boolean existsByCategoryId(Long categoryId);

    @Query("SELECT t " +
            "FROM Transaction t " +
            "JOIN FETCH t.category " +
            "WHERE t.transactionDate >= :startDate AND t.transactionDate <= :endDate")
    List<Transaction> findAllByDateRange(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}
