package net.imspring.study.mvc;

import java.util.HashMap;
import java.util.Map;



import net.imspring.study.mvc.web.LoginWeb;
import net.imspring.study.mvc.web.Web;
import net.imspring.study.mvc.web.doLoginWeb;

public class Config {

	public Config() {
		
	}
	private static Map<String,Class<? extends Web>> urlMap =  new HashMap<String, Class<? extends Web>>();
	public static Map<String, Class<? extends Web>> initMap(){
		urlMap.put("/login", LoginWeb.class);
		urlMap.put("/dologin", doLoginWeb.class);		
		return urlMap;
	}
}
