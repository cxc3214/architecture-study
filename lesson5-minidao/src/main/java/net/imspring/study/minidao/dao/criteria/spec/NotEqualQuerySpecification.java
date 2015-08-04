package net.imspring.study.minidao.dao.criteria.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import net.imspring.study.minidao.dao.criteria.QuerySpecification;


public class NotEqualQuerySpecification implements QuerySpecification {
	@Override
	public Predicate assembly(CriteriaBuilder cb, Expression<?> express, Object value, Class valueType) {
		return cb.notEqual(express, value);
	}

}
