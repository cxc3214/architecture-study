package net.imspring.study.minidao.dao.criteria.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import net.imspring.study.minidao.dao.criteria.QuerySpecification;

import org.springframework.data.jpa.domain.Specification;

/**
 * 如果为equal 或者notequery 则这样处理 
 * @author Administrator
 *
 */
public class EqualQuerySpecification implements QuerySpecification {


	@Override
	public Predicate assembly(CriteriaBuilder cb, Expression<?> express, Object value, Class valueType) {
		return cb.equal(express, value);
	}

}
