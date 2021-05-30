package cn.ibizlab.pms.util.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@FeignClient(value = "${ibiz.ref.service.dict:ibzdict-api}",contextId = "dict",fallback = IBZDictFallback.class)
public interface IBZDictFeignClient
{
	@RequestMapping(method = RequestMethod.POST, value = "/dictionarys/catalogs/sync")
	Boolean syncRuntimeDict(@RequestBody JSONArray catalogs);
}
