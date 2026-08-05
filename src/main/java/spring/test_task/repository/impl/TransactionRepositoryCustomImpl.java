package spring.test_task.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import spring.test_task.dto.TransactionFilterDto;
import spring.test_task.entity.Category;
import spring.test_task.entity.Transaction;
import spring.test_task.repository.TransactionRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Transaction> findFiltered(TransactionFilterDto filter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        Join<Transaction, Category> categoryJoin = (Join<Transaction, Category>) (Object)root.fetch("category", JoinType.LEFT);
        List<Predicate> predicates = buildPredicates(cb, root, categoryJoin, filter);

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : pageable.getSort()) {
                String property = sortOrder.getProperty();
                Path<?> path = property.equals("category") ? categoryJoin.get("name") : root.get(property);

                orders.add(sortOrder.isAscending() ? cb.asc(path) : cb.desc(path));
            }
            query.orderBy(orders);
        }

        TypedQuery<Transaction> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Transaction> resultList = typedQuery.getResultList();
        Long totalCount = countTotal(cb, filter);

        return new PageImpl<>(resultList, pageable, totalCount);
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Transaction> root, From<Transaction, Category> categoryJoin, TransactionFilterDto filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getType() != null) {
            predicates.add(cb.equal(categoryJoin.get("type"), filter.getType()));
        }

        if (filter.getCategoryId() != null) {
            predicates.add(cb.equal(categoryJoin.get("id"), filter.getCategoryId()));
        }

        if (filter.getFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("transactionDate"), filter.getFrom()));
        }

        if (filter.getTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("transactionDate"), filter.getTo()));
        }

        return predicates;
    }

    private Long countTotal(CriteriaBuilder cb, TransactionFilterDto filter) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Transaction> countRoot = countQuery.from(Transaction.class);
        Join<Transaction, Category> countCategoryJoin = countRoot.join("category", JoinType.LEFT);

        countQuery.select(cb.count(countRoot));

        List<Predicate> predicates = buildPredicates(cb, countRoot, countCategoryJoin, filter);

        if (!predicates.isEmpty()) {
            countQuery.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}