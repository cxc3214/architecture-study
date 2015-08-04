/**
 * 
 */
package net.imspring.study.minidao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询bean的排序
 * @author cxc
 * 2015.7.20 创建
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sorted {
	
	public String[] value() ;
	/**
	 * 动态排序 参数名称
	 * @return
	 */
	public  String prameName() default "sort";
	/**
	 * 是否 只用 prame进行排序
	 * @return
	 */
	public  boolean onlyPrameSort() default false;
}
