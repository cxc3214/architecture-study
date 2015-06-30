#这是一个简单的mvc注解例子，从mvc的例子升级而来。

mvc2:
* 通过注解完成 配置map的创建
* 增加了 ControllerScan（设置扫描的基础路径） Controller 两个注解
* 



#使用
通过增加注解完成配置
```
//也可以不配置 默认从当前扫描
@ControllerScan(Config.class)
public class Config 
```

```
@Controller(path="/login")
public class LoginWeb extends Web 
```

config.java 手动配置静态映射
```
	public Map<String, Class<? extends Web>> initMap(){		
		//ClassUtils.scanPackage(packageName, classFilter)
		//urlMap.put("", LoginWeb.class);
		urlMap.put("/dologin", doLoginWeb.class);		
		return urlMap;
	}
```

mvn tomcat:run

访问 ： http://127.0.0.1:8080/login

