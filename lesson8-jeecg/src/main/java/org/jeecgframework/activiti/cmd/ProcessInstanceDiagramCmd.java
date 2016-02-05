package org.jeecgframework.activiti.cmd;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.image.ProcessDiagramGenerator;

public class ProcessInstanceDiagramCmd implements Command<InputStream> {

	protected String processInstanceId;

	public ProcessInstanceDiagramCmd(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public InputStream execute(CommandContext commandContext) {

		ExecutionEntityManager executionEntityManager = commandContext.getExecutionEntityManager();
		ProcessEngineConfiguration processEngineConfiguration = commandContext.getProcessEngineConfiguration();
		ExecutionEntity executionEntity = executionEntityManager
				.findExecutionById(processInstanceId);
		List<String> activiityIds = executionEntity.findActiveActivityIds();
		String processDefinitionId = executionEntity.getProcessDefinitionId();
		GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
				processDefinitionId);
		BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration
				.getProcessDiagramGenerator();

		InputStream is = diagramGenerator.generateDiagram(bpmnModel, "png",
				activiityIds, Collections.<String> emptyList(),
				processEngineConfiguration.getActivityFontName(),
				processEngineConfiguration.getLabelFontName(),
				processEngineConfiguration.getClassLoader(), 1.0);
//		 InputStream is = ProcessDiagramGenerator.generateDiagram(bpmnModel,
//	                "png", activiityIds);

		return is;
	}

}
