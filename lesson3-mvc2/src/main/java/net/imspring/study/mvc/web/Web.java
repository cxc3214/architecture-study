package net.imspring.study.mvc.web;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 抽象类 负责完成控制器的抽象功能
 * @author cxc
 */
public abstract class Web {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void init(HttpServletRequest rq,HttpServletResponse rp){
		this.request = rq;
		this.response = rp;
	}
	/**
	 * 往request中增加值
	 * @param name
	 * @param value
	 * @return
	 */
	public  Web setAttr(String name, Object value) {
		request.setAttribute(name, value);
		return this;
	}
	/**
	 * 跳转到视图
	 * @param view
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Web forward(String view){
		try {
			request.getRequestDispatcher(view).forward(request,response);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	/**
	 * 反射获取 构造 bean 对象
	 * @return
	 */
	public <T> T setBean(T  t){
	   try {
		   Field[] fields = t.getClass().getDeclaredFields();
		   for(Field field:fields){ 
			   field.setAccessible(true);
			   String key  = field.getName();
			   field.set(t, request.getParameter(key));
			   field.setAccessible(false);
		   }
		} catch (Exception e) {
			e.printStackTrace();			
		}
	   return t;
   }
	
	public abstract void render();
}
