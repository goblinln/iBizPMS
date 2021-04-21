package cn.ibizlab.pms.util.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

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
	Map<String, Map<String, Object>> getTaskByUserId(@PathVariable("system") String system, @PathVariable("userId") String userId,
													 @PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks")
	Map<String, Map<String, Object>> getTaskByStep(@PathVariable("system") String system, @PathVariable("userId") String userId,
													 @PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2 ,@RequestParam("n_taskdefinitionkey_eq") String srfwfstep);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks/unread")
	Map<String, Map<String, Object>> getUnReadTaskByUserId(@PathVariable("system") String system, @PathVariable("userId") String userId,
														   @PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks/done")
	Map<String, Map<String, Object>> getDoneTaskByUserId(@PathVariable("system") String system, @PathVariable("userId") String userId,
														 @PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks/finish")
	Map<String, Map<String, Object>> getFinishTaskByUserId(@PathVariable("system") String system, @PathVariable("userId") String userId,
														   @PathVariable("entity") String entity, @PathVariable("insttag") String instTag, @PathVariable("insttag2") String instTag2);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks/all")
	Map<String,Map<String,Object>> getAllTaskByUserId(@PathVariable("system") String system,@PathVariable("userId")String userId,
													  @PathVariable("entity") String entity,@PathVariable("insttag")String instTag,
													  @PathVariable("insttag2") String instTag2);

	@RequestMapping(method = RequestMethod.POST, value = "/{system}-user-{userId}/{insttag}/{insttag2}/{entity}/tasks/sendcopy")
	Map<String,Map<String,Object>> getSendCopyTask(@PathVariable("system") String system,@PathVariable("userId")String userId,
													  @PathVariable("entity") String entity,@PathVariable("insttag")String instTag,
													  @PathVariable("insttag2") String instTag2);
}
