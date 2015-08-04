package net.imspring.study.minidao.dao.criteria.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import net.imspring.study.minidao.dao.criteria.QuerySpecification;


public class IsNullOrEmptyQuerySpecification implements QuerySpecification  {

	@Override
	public Predicate assembly(CriteriaBuilder cb, Expression<?> express, Object value, Class valueType) {
		List<Predicate> tlist =new ArrayList<Predicate>();
		tlist.add(cb.isNull(express));
		tlist.add(cb.equal(express, ""));		
		Predicate[] predicate = new Predicate[tlist.size()];
		return cb.or(tlist.toArray(predicate));
	}
}
