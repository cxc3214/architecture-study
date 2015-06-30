package net.imspring.study.mvc;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import net.imspring.study.mvc.annotation.Controller;
import net.imspring.study.mvc.annotation.ControllerScan;
import net.imspring.study.mvc.forclass.ClassUtils;
import net.imspring.study.mvc.forclass.ControllerFilter;
import net.imspring.study.mvc.web.LoginWeb;
import net.imspring.study.mvc.web.Web;
import net.imspring.study.mvc.web.doLoginWeb;

@ControllerScan(Config.class)
public class Config {

	public Config() {
		String[] pkgs = getSanBasePakcage();
		if(pkgs.length>0){
			for(String pkg :pkgs){
				initMapByAnnotation(pkg);
			}
		}
	}
	private static Map<String,Class<? extends Web>> urlMap =  new HashMap<String, Class<? extends Web>>();
	
	public Map<String, Class<? extends Web>> initMap(){		
		//ClassUtils.scanPackage(packageName, classFilter)
		//urlMap.put("", LoginWeb.class);
		urlMap.put("/dologin", doLoginWeb.class);		
		return urlMap;
	}
	
	private String[] getSanBasePakcage(){
		List<String> relist = new ArrayList<String>();
		Class<Config> classz = Config.class;
		if(classz.isAnnotationPresent(ControllerScan.class)){
			ControllerScan scan = classz.getAnnotation(ControllerScan.class);
			Class[] classes = scan.value();
			if(classes.length>0){
				for(Class cs :classes){
					relist.add(cs.getPackage().getName());
				}
			}
		}
		if(relist.size()>0 ) return relist.toArray(new String[]{});
		return new String[]{this.getClass().getPackage().getName()};
	}
	
	private void initMapByAnnotation(String packagename){
		List<Class> classes = ClassUtils.scanPackage(packagename, new ControllerFilter());
		if(classes.size()>0){
			for(Class cs :classes){
				if(cs.isAnnotationPresent(Controller.class)){
					Controller ctrl = (Controller) cs.getAnnotation(Controller.class);
					urlMap.put(ctrl.path(), cs);
				}
			}
		}
	}
}
