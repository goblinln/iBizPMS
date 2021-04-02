package cn.ibizlab.pms.util.client;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

@Component
public class IBZWFFallback implements IBZWFFeignClient {

    @Override
    public List<String> getbusinesskeys(String system, String appname, String entity, String processDefinitionKey, String taskDefinitionKey) {
        return null;
    }

    @Override
    public List<String> getbusinesskeysByUserId(String system, String userId, String entity, String processDefinitionKey, String taskDefinitionKey) {
        return null;
    }

    @Override
    public Boolean deployBpmnFile(List<Map<String, Object>> bpmnfiles) {
        return null;
    }

    @Override
    public JSONObject wfstart(String system, String appname, String entity, String businessKey, JSONObject instance) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getTaskByUserId(String system, String userId, String entity, String dynainstid) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getUnReadTaskByUserId(String system, String userId, String entity, String dynainstid) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getDoneTaskByUserId(String system, String userId, String entity, String dynainstid) {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getFinishTaskByUserId(String system, String userId, String entity, String dynainstid) {
        return null;
    }
}
