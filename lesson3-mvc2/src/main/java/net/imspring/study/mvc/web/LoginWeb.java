package net.imspring.study.mvc.web;

import net.imspring.study.mvc.annotation.Controller;


@Controller(path="/login")
public class LoginWeb extends Web {

	@Override
	public void render() {
		setAttr("hehe", "hellword");
		forward("login.jsp");
	}
}
