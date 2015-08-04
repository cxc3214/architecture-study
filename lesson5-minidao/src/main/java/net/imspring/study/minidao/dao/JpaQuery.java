package net.imspring.study.minidao.dao;

import net.imspring.study.minidao.model.Model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


/**
 * dto 支持类
 * @author cxc
 *
 * @param <T> 所有的数据model 
 */
public interface  JpaQuery<T extends Model> {
	public Specification<T> buildSpecification();
	public Pageable buildPageable();
	public Sort buildSort();
}
