package net.imspring.study.minidao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * searchbean分页参数配置
 * 分页参数
 * @author cxc
 * 2015.7.20 创建
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Paged {
	String pageParameName() default "page";
	String sizeParameName() default "rows";
	int defaultPage() default 1;
	int defaultSize() default 20;
}
