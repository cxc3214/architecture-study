package net.imspring.study.gencode.kit;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerKit {

	private Configuration config;

	private String basepath = PropertieKit.readValue("config.properties", "tpl.basepath.default");
	private String outBasePath = PropertieKit.readValue("config.properties", "output.basepath");
	public FreemarkerKit() {
		this.config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		this.config.setDefaultEncoding("UTF-8");
		this.config.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
		this.config.setNumberFormat("0.##");
		
		
		//this.config.setClassForTemplateLoading(this.getClass(), basepath);
	}
	/**
	 * 解析模板
	 * @param parmes
	 * @return
	 */
	public FreemarkerKit parse(Map<String,Object> parmes){
		String tplpath = this.getClass().getResource("/").getPath() + File.separator + this.basepath;
		Template tl;
		try {
			File basedir = new File(tplpath);
			tplpath =basedir.getPath();
			//设置freemarker扫描的基路径
			this.config.setTemplateLoader(new FileTemplateLoader(basedir));
			//获取所有的模板代码
			List<String> listFile = FileKit.getFiles(tplpath);
			for(String path : listFile){
				String tplname = path.replace(tplpath, "");
				if(!tplname.equals("")) tplname = tplname.substring(1);
				tl = config.getTemplate(tplname);
				Writer out = new StringWriter();
				tl.process(parmes, out);
				String outfilename = outBasePath+File.separator + tplname ;
				//创建生成路径
				FileKit.mkDir(outBasePath);
				FileKit.wirteFile(outfilename, out.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return this;
	}
}
