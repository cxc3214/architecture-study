package net.imspring.study.mvc.forclass;

import net.imspring.study.mvc.web.Web;

public class ControllerFilter implements ClassFilter {

	@Override
	public boolean accept(Class clazz) {		
		return Web.class.isAssignableFrom(clazz);
	}

}
