package net.imspring.study.minidao.beans;

import java.util.Map;

import net.imspring.study.minidao.dao.DefaultJpaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


public class Client {
	@Autowired
	JpaInterface japDao;
	/**
	 * 构造一个分页的查询 只需要这3行代码，是不是简单多了？
	 * @param tmap controller传输过来的参数map
	 * @return
	 */
	public Page<ModelA> getList(Map<String, Object> tmap) {
		DefaultJpaQuery dq = new DefaultJpaQuery<ModelA>(ModelASearchBean.class, tmap);
		Page<ModelA> returnList =  japDao.findAll(dq.buildSpecification(), dq.buildPageable());
		return returnList;
	}
}
