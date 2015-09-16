#这是一个简单代码生成器。

1.通过config.properties 设置代码模板的记录 和 生成代码的目录
2.扫描路径下所有模板替换代码的通用变量




#使用
```
	public static void main(String[] args) {
		//创建代码生成工具类
		FreemarkerKit fkit = new FreemarkerKit();
		//构造简单的代码参数
		Map<String, Object> tmap = new HashMap<String, Object>();
		Project p = new Project("cxc", "一个简单的项目");
		tmap.put("project", p);
		//解析并生成代码
		fkit.parse(tmap);
	}
```



