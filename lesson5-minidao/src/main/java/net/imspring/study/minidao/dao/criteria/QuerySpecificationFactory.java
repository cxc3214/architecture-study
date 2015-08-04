package net.imspring.study.minidao.dao.criteria;

import net.imspring.study.minidao.annotation.CondtionType;
import net.imspring.study.minidao.dao.criteria.spec.EqualQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.GreaterThanOrEqualQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.GreaterThanQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.InQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.IsNullOrEmptyQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.LessThanQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.LikeQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.NotEqualQuerySpecification;
import net.imspring.study.minidao.dao.criteria.spec.LessThanOrEqualQuerySpecification;

public class QuerySpecificationFactory {

	public static QuerySpecification getInstance(CondtionType type) {
		switch (type) {
		case equal:
			return new EqualQuerySpecification();
		case notequal:
			return new NotEqualQuerySpecification();
		case greaterthan:
			return new GreaterThanQuerySpecification();
		case greaterthanOrequal:
			return new GreaterThanOrEqualQuerySpecification();
		case lessthan:
			return new LessThanQuerySpecification();
		case lessthanOrequal:
			return  new LessThanOrEqualQuerySpecification();
		case like:
			return new LikeQuerySpecification();
		case isnullorempty:
			return new IsNullOrEmptyQuerySpecification();
		case in:
			return new InQuerySpecification();
		default:
			return new EqualQuerySpecification();
		}
	}
}
