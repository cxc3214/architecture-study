package net.imspring.study.gencode.core;

import java.util.HashMap;
import java.util.Map;

import net.imspring.study.gencode.bean.Project;
import net.imspring.study.gencode.kit.FreemarkerKit;

public class Generator {
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
}
