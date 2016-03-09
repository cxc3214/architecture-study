package net.imspring.study.weixin.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.imspring.study.weixin.tool.BeanXmlKit;

@XmlRootElement(name="xml")
public class TextMsg extends BaseMsg{

	@XmlElement(required=true)
	private String Content;
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	
}
