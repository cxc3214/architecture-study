package org.jeecgframework.web.simpleflow;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDiagramCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.jeecgframework.activiti.cmd.ProcessInstanceDiagramCmd;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/flow")
public class simpleflowController extends BaseController{
	
	@Autowired
	ProcessEngine engine;
	
	@RequestMapping
	public ModelAndView index(){
		ModelAndView mav =new ModelAndView();
		String basePath=simpleflowController.class.getResource("/").getPath();
		basePath=basePath.substring(1,basePath.length());
		String[] list  = new File(basePath+File.separator+"flow").list();
		
		mav.addObject("list", list);		
		mav.setViewName("flow/index");
		return mav;
	}
	
	@RequestMapping("/deploy")
	public ModelAndView deploy(String processName, ModelAndView mav){		
		RepositoryService service = engine.getRepositoryService();
		if (null != processName)
			service.createDeployment().addClasspathResource("flow/" + processName).deploy();
		List<ProcessDefinition> list = service.createProcessDefinitionQuery().list();
		mav.addObject("list", list);
		mav.setViewName("/flow/deploy");
		return mav;
	}
	
	@RequestMapping("/graphics")
	public void graphics(String definitionId, String instanceId,
			String taskId, ModelAndView mav, HttpServletResponse response)
			throws IOException {		
		response.setContentType("image/png");
		Command<InputStream> cmd = null;
		if (definitionId != null) {
			cmd = new GetDeploymentProcessDiagramCmd(definitionId);
		}

		if (instanceId != null) {
			cmd = new ProcessInstanceDiagramCmd(instanceId);
		}

		if (taskId != null) {
			Task task = engine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
			cmd = new ProcessInstanceDiagramCmd(task.getProcessInstanceId());
		}

		if (cmd != null) {
			InputStream is = engine.getManagementService().executeCommand(cmd);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = is.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/start")
	public ModelAndView start(String id,ModelAndView mav){
		
		RuntimeService service = engine.getRuntimeService();

		service.startProcessInstanceById(id);

		List<ProcessInstance> list = service.createProcessInstanceQuery().list();

		mav.addObject("list", list);
		mav.setViewName("flow/started");
		return mav;		
	}
	
	@RequestMapping("/started")
	public ModelAndView started(String id,ModelAndView mav){
		
		RuntimeService service = engine.getRuntimeService();
		List<ProcessInstance> list = service.createProcessInstanceQuery().list();

		mav.addObject("list", list);
		mav.setViewName("flow/started");
		return mav;		
	}
	
	@RequestMapping("task")
	public ModelAndView task(ModelAndView mav){
		TaskService service=engine.getTaskService();
		List<Task> list=service.createTaskQuery().list();
		mav.addObject("list", list);
		mav.setViewName("flow/task");
		return mav;
	}
	
	
	@RequestMapping("mytask")
	public ModelAndView mytask(ModelAndView mav){
		TaskService taskService=engine.getTaskService();
		RuntimeService runtimeService=engine.getRuntimeService();	
	        // 根据当前人的ID查询
	        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned("admin");
	        List<Task> tasks = taskQuery.list();	
	        // 根据流程的业务ID查询实体并关联
	        for (Task task : tasks) {
	            String processInstanceId = task.getProcessInstanceId();
	            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	            String businessKey = processInstance.getBusinessKey();
	            if (businessKey == null) {
	            	tasks.remove(task);
	            	continue;
	            }
//	            Ttestqingjia Ttestqingjia = ttestqingjiaDao.findById(businessKey);
//	            System.out.println(Ttestqingjia.getQjyy());
	        }
	
		
		
		mav.addObject("list", tasks);
		mav.setViewName("flow/task");
		return mav;
	}
	
	@RequestMapping("complete")
	public ModelAndView complete(ModelAndView mav,String id){
		
		TaskService service=engine.getTaskService();		
		service.complete(id);	
		return new ModelAndView("redirect:task.do");
	}

	
}
