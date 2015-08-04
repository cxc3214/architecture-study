package net.imspring.study.minidao.dao.criteria.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class GreaterThanOrEqualQuerySpecification extends AbstractQuerySpecification {

	@Override
	public Predicate assembly(CriteriaBuilder cb, Expression<?> express, Object value, Class valueType) {
		return cb.greaterThanOrEqualTo(express.as(valueType), ConvertTo(value)) ;
	}

}
