package net.imspring.study.mvc.web;



public class LoginWeb extends Web {

	@Override
	public void render() {
		setAttr("hehe", "hellword");
		forward("login.jsp");
	}
}
