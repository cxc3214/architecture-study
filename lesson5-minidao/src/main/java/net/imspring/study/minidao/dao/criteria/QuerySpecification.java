package net.imspring.study.minidao.dao.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public interface QuerySpecification {
	public Predicate assembly(CriteriaBuilder cb,Expression<?> express,Object value,Class valueType);
}	
