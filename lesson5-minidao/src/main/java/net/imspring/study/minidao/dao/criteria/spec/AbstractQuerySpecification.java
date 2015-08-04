package net.imspring.study.minidao.dao.criteria.spec;

import net.imspring.study.minidao.dao.criteria.QuerySpecification;

public abstract class AbstractQuerySpecification implements QuerySpecification{
	
	<Y extends Comparable<? super Y>>  Y ConvertTo(Object obj) {
		Y reobj = (Y)"" ; 
		if(obj ==null) return reobj;
		try {	
			reobj = (Y) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	reobj;
	}	
}
