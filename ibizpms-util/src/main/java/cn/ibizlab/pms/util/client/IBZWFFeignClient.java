package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.domain.WFInstance;
import cn.ibizlab.pms.util.domain.DataAccessMode;
import cn.ibizlab.pms.util.filter.WFTaskSearchContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import java.io.Serializable;


@FeignClient(value = "${ibiz.ref.service.wf:ibzwf-api}",contextId = "wf",fallback = IBZWFFallback.class)
public interface IBZWFFeignClient
{
	@RequestMapping(method = RequestMethod.GET, value = "/{system}-app-{appname}/{entity}/process-definitions/{processDefinitionKey}/usertasks/{taskDefinitionKey}/tasks")
	List<String> getbusinesskeys(@PathVariable("system") String system, @PathVariable("appname") String appname,
														@PathVariable("entity") String entity, @PathVariable("processDefinitionKey") String processDefinitionKey, @PathVariable("taskDefinitionKey") String taskDefinitionKey);


	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{entity}/process-definitions/{processDefinitionKey}/usertasks/{taskDefinitionKey}/tasks")
	List<String> getbusinesskeysByUserId(@PathVariable("system") String system,@PathVariable("userId") String userId,
																@PathVariable("entity") String entity,@PathVariable("processDefinitionKey") String processDefinitionKey,@PathVariable("taskDefinitionKey") String taskDefinitionKey);

	@RequestMapping(method = RequestMethod.POST, value = "/deploybpmn")
	Boolean deployBpmnFile(@RequestBody List<Map<String,Object>> bpmnfiles);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-app-{appname}/{entity}/{businessKey}/process-instances")
	JSONObject wfstart(@PathVariable("system") String system, @PathVariable("appname") String appname,
					   @PathVariable("entity") String entity,
					   @PathVariable("businessKey") String businessKey, @RequestBody JSONObject instance);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks")
	@Deprecated
	Map<String, Map<String, Object>> getTask(@PathVariable("system") String system, @PathVariable("userId") String userId,
													 @PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2, @RequestBody WFTaskSearchContext context);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks")
	feign.Response getWFTask(@PathVariable("system") String system, @PathVariable("userId") String userId,
													@PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2, @RequestBody WFTaskSearchContext context);

	@RequestMapping(method = RequestMethod.GET, value = "/{system}-{entity}/{businessKey}/dataaccessmode")
	Integer getDataAccessMode(@PathVariable("system") String system, @PathVariable("entity") String entity, @PathVariable("businessKey") Serializable businessKey);

	@RequestMapping(method = RequestMethod.GET, value = "/{system}-{entity}/{businessKey}/editfields")
	DataAccessMode getEditFields(@PathVariable("system") String system, @PathVariable("entity") String entity, @PathVariable("businessKey") Serializable businessKey);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-{entity}/{businessKey}/register")
    WFInstance WFRegister(@PathVariable("system") String system,
        @PathVariable("entity") String entity,@PathVariable("businessKey") Serializable businessKey , @RequestBody WFInstance instance);

	@RequestMapping(method = RequestMethod.DELETE, value = "/{system}-{entity}/{businessKey}/unregister")
	Boolean WFUnregister(@PathVariable("system") String system, @PathVariable("entity") String entity,@PathVariable("businessKey") Object businessKey);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-app-{appname}/{entity}/{businessKey}/tasks/{taskId}/read")
	Boolean readTask(@PathVariable("system") String system,@PathVariable("appname") String appname, @PathVariable("entity") String entity,
											@PathVariable("businessKey") String businessKey,@PathVariable("taskId") String taskId,
											@RequestBody Object taskWay);
}
