package net.imspring.study.minidao.dao.criteria.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import net.imspring.study.minidao.dao.criteria.QuerySpecification;
import net.imspring.study.minidao.toolkit.StringKit;


public class LikeQuerySpecification implements QuerySpecification {

	@Override
	public Predicate assembly(CriteriaBuilder cb, Expression<?> express, Object value, Class valueType) {
		String val = StringKit.isEmpty(value)? "" :value.toString();
		val = val.toLowerCase();
		return cb.like(express.as(String.class), getLikePattern(val));
	}
	private String getLikePattern(final String searchTerm) {
        StringBuilder pattern = new StringBuilder();
        //如果客户自己带了 % 则去除默认配置 全模糊
        if(searchTerm.indexOf("%")>=0) return searchTerm.toLowerCase();
        pattern.append("%");
        pattern.append(searchTerm.toLowerCase());
        pattern.append("%");
        return pattern.toString();
    }


	
}
