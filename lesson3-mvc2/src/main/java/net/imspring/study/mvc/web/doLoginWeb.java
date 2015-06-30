package net.imspring.study.mvc.web;

import net.imspring.study.mvc.bean.User;



public class doLoginWeb extends Web {

	@Override
	public void render() {
		User tuser =  setBean(new User());
		System.out.println(tuser.toString());
		setAttr("user",tuser);
		forward("dologin.jsp");
	}
}
