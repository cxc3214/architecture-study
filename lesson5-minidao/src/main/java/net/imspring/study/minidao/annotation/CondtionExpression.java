package net.imspring.study.minidao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * searchbean的查询条件
 * @author cxc
 * 2015.07.20 创建
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CondtionExpression {
	//对应的bean的字段如果为“”模式 当前字段名
	String value() default "";
	
	CondtionType type() default CondtionType.equal;	
	
	boolean result() default true;
}
