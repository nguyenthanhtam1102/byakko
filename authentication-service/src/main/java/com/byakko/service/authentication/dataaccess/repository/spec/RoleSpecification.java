package com.byakko.service.authentication.dataaccess.repository.spec;

import com.byakko.service.authentication.dataaccess.entity.RoleEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RoleSpecification implements Specification<RoleEntity> {

    private final String query;

    public RoleSpecification(String query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(this.query != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.query + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
