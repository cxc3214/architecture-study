package net.imspring.study.mongo;

public class Config {
	private String dbpath = this.getClass().getResource("/").getPath();
	private String charset = "UTF-8";

	public String getDbpath() {
		return dbpath;
	}

	public void setDbpath(String dbpath) {
		this.dbpath = dbpath;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}	
	
}