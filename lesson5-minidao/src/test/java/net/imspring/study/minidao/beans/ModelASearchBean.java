package net.imspring.study.minidao.beans;

import net.imspring.study.minidao.annotation.CondtionExpression;
import net.imspring.study.minidao.annotation.CondtionType;
import net.imspring.study.minidao.annotation.Paged;
import net.imspring.study.minidao.annotation.SearchBean;
import net.imspring.study.minidao.annotation.Sorted;
import net.imspring.study.minidao.dao.Dto;

@SearchBean
@Paged
@Sorted({"id desc"})
public class ModelASearchBean extends Dto{
	
	@CondtionExpression
	private String name;
	@CondtionExpression(value="age",type=CondtionType.greaterthan)
	private Integer ageFrom;
	@CondtionExpression(value="age",type=CondtionType.lessthan)
	private Integer ageTo;
	
}
