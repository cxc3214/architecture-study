package net.imspring.study.minidao.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.imspring.study.minidao.annotation.CondtionExpression;
import net.imspring.study.minidao.annotation.Paged;
import net.imspring.study.minidao.annotation.SearchBean;
import net.imspring.study.minidao.annotation.Sorted;
import net.imspring.study.minidao.dao.criteria.QuerySpecificationFactory;
import net.imspring.study.minidao.model.Model;
import net.imspring.study.minidao.toolkit.StringKit;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;


/**
 * 利用注解进行 查询构造
 * 主要构造3个内容
 *  pageable 分页
 *  sort 排序
 *  specification 查询规则
 * @author cxc
 *
 * @param <T> 查询哪个model
 */
public class DefaultJpaQuery<T extends Model> implements JpaQuery<T> {

	private Map<String, Object> valuesMap;
	private Paged paged;
	private Sorted sorted;
	private Map<String, CondtionExpression> condtionExpressions = new HashMap<String, CondtionExpression>();
	private Map<String,Class> itemTypesMap = new HashMap<String, Class>();

	/**
	 * 构造方法
	 * 
	 * @param dto
	 * @param valuesMap
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public DefaultJpaQuery(Class<? extends Dto> dtoclass, Map<String, Object> valuesMap) {
		SearchBean searchbean = dtoclass.getAnnotation(SearchBean.class);
		Assert.notNull(searchbean, "该查询bean不是 @searchbean");
		paged = dtoclass.getAnnotation(Paged.class);
		sorted = dtoclass.getAnnotation(Sorted.class);
		for (Field field : dtoclass.getDeclaredFields()) {
			CondtionExpression cdtexpression = field.getAnnotation(CondtionExpression.class);
			if (cdtexpression == null)
				continue;
			else {
				condtionExpressions.put(field.getName(), cdtexpression);
				itemTypesMap.put(field.getName(), field.getType());
			}
		}
		this.valuesMap = valuesMap;
	}
	
	@Override
	public Specification<T> buildSpecification() {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				Set set = condtionExpressions.entrySet();
				for (Iterator iter = set.iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					//字段key
					String key = (String) entry.getKey();
					//字段注解
					CondtionExpression condtion = (CondtionExpression) entry.getValue();
					//查询条件factory
					QuerySpecificationFactory factory = new QuerySpecificationFactory();
					//对应值的类型 原则上应该跟bean一样
					String condtionValueName = condtion.value();
					condtionValueName = StringKit.isEmpty(condtionValueName)?key:condtionValueName;
					Expression express = root.get(condtionValueName);
					Object value  = valuesMap.get(key);					
					//如果为空则 不进行叠加条件
					if(!StringKit.isEmpty(value)){
						Predicate predicate  = factory.getInstance(condtion.type()).assembly(cb,express,value,itemTypesMap.get(key)) ;
						if(!condtion.result()) predicate = cb.isFalse(predicate);
						list.add(predicate);
					}
				}
				Predicate[] predicate = new Predicate[list.size()];
				return  cb.and(list.toArray(predicate));
			}
		};
	}

	@Override
	public Pageable buildPageable() {
		Assert.notNull(paged, "@searchbean 没有配置 @paged! ");
		Assert.notNull(sorted, "@searchbean 没有配置 @sorted，分页必须此配置! ");
		String parame1 = paged.pageParameName();
		String parame2 = paged.sizeParameName();
		int page = paged.defaultPage();
		int size = paged.defaultSize();
		Object pageobj = valuesMap.get(parame1);
		Object sizeobj = valuesMap.get(parame2);
		if (!StringKit.isEmpty(pageobj))
			page = Integer.valueOf(pageobj.toString());
		if (!StringKit.isEmpty(sizeobj))
			size = Integer.valueOf(sizeobj.toString());
		Pageable pageSpecification = new PageRequest(page - 1, size, buildSort());
		return pageSpecification;
	}

	@Override
	public Sort buildSort() {
		Assert.notNull(sorted, "@searchbean 没有配置 @sorted! ");
		String[] sortstrs = sorted.value();
		Assert.isTrue(sortstrs.length > 0, "@searchbean 没有配置 @sorted! ");
		Sort sorts = null;
		for (int i = 0; i < sortstrs.length; i++) {
			String str = sortstrs[i];
			if (i == 0)
				sorts = stringToSort(str);
			else
				sorts = sorts.and(stringToSort(str));
		}
		return sorts;
	}

	private Sort stringToSort(String sortstr) {
		Assert.notNull(sortstr, "sortstr不能为null");
		
		String lowsortstr = sortstr.toLowerCase();
		// 判断两个排序关键字
		int index = lowsortstr.indexOf("desc");
		if (index < 0)
			index = lowsortstr.indexOf("asc");
		//取排序类型
		String type = index > 0 ? lowsortstr.substring(index).trim() : "";
		//取排序字段 注意这里取的是原生的 字符 没有转小写
		String itemStr = index > 0 ? sortstr.substring(0, index) : sortstr;
		List<String> items = Arrays.asList(StringKit.trimArrayElements(itemStr.split(",")));
		Direction direction = Sort.Direction.ASC;
		if (type.equals("desc"))
			direction = Sort.Direction.DESC;
		return new Sort(direction, items);
	}

//	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//		Map<String, Object> tmap = new HashMap<String, Object>();
//		tmap.put("name", "1");
//		DefaultJpaQuery dq = new DefaultJpaQuery<Menu>(MenuDto.class, tmap);
//		Specification<Menu> s = dq.buildSpecification();
//		System.out.println(s.toString());
//	}
}
