package net.imspring.study.minidao.annotation;


public enum CondtionType  {
	//ge(">="),gt(">"),le("<="),lt("<"),eq("="),ne("!="),like("like"),is("is"),in("in");
	equal ,
	notequal ,
	like ,
	greaterthan,
	greaterthanOrequal,
	lessthan,
	lessthanOrequal,
	isnullorempty,
	in;
}
