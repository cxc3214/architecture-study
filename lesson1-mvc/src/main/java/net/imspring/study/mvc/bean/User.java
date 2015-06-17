package net.imspring.study.mvc.bean;

import java.io.Serializable;
/**
 * user bean
 * @author cxc
 *
 */
public class User {
	
	
	private String name;
	private String sex;
	private String  descript;
	
	
	public User() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return "name="+name+"sex=" +sex+"descript="+descript;
	}
}
