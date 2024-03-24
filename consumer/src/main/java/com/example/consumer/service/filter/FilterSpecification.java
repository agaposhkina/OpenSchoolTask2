package com.example.consumer.service.filter;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
public class FilterSpecification<T> implements Specification<T> {
    private final FilterRequest filterRequest;
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(criteriaBuilder.literal(TRUE), TRUE);

        for (Filter filter : filterRequest.getFilters()) {
            Predicate andPredicate;
            switch (filter.getOperator()) {
                case EQUAL -> andPredicate = criteriaBuilder.equal(fieldValue(root, filter),
                        filter.getValue());
                case MATCH -> andPredicate = criteriaBuilder.like(fieldValue(root, filter),
                        "%"+filter.getValue()+"%");
                case GREATER -> andPredicate = criteriaBuilder.greaterThan(fieldValue(root, filter),
                        filter.getValue().toString());
                case LESS -> andPredicate = criteriaBuilder.lessThan(fieldValue(root, filter),
                        filter.getValue().toString());
                case GREATER_EQUAL -> andPredicate = criteriaBuilder.greaterThanOrEqualTo(fieldValue(root, filter),
                        filter.getValue().toString());
                case LESS_EQUAL -> andPredicate = criteriaBuilder.lessThanOrEqualTo(fieldValue(root, filter),
                        filter.getValue().toString());
                default -> throw new IllegalStateException("Unexpected value: " + filter.getOperator());
            }

            predicate = criteriaBuilder.and(andPredicate, predicate);
        }
        return predicate;
    }

    private static <R> Expression<R> fieldValue(Root<?> root, Filter filter) {
        if (filter.getJoinTable() != null)
            return getJoin(root, filter.getJoinTable()).get(filter.getKey());
        return root.get(filter.getKey());
    }

    // Takes care of not producing several joins for the same table
    // (which leads to incorrect result when user uses multiple filters to the same joined table fields)
    private static Join<?, ?> getJoin(Root<?> root, String joinTo) {
        return root.getJoins().stream()
                .filter(r -> r.getAttribute().getName().equals(joinTo))
                .findFirst()
                .orElseGet(() -> root.join(joinTo));
    }
}


