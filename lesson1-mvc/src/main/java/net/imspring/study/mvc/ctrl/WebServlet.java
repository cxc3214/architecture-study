package net.imspring.study.mvc.ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.imspring.study.mvc.Config;
import net.imspring.study.mvc.web.Web;

public class WebServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WebServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html; charset=UTF-8");
		//response.setHeader("Content-Type", "text/html; charset=UTF-8"); 
		String path = request.getServletPath(); 
		System.out.println("开始解析路径:path="+path);
		//创建 控制器实体，并调用
		try {
			Class  cls =  Config.initMap().get(path);
			if(cls!=null){
				Web web =(Web) cls.newInstance();
				web.init(request,response);
				web.render();				
			}else{
				System.out.println("您访问的路径不存在或没有映射");
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// 
		System.out.println("servlet initing .......");
	}

}
