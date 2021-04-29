package cn.ibizlab.pms.util.client;

import cn.ibizlab.pms.util.security.AuthenticationInfo;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.AuthorizationLogin;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.MultiValueMap;
import com.alibaba.fastjson.JSONObject;

@FeignClient(value = "${ibiz.ref.service.uaa:ibzuaa-api}",contextId = "uaa",fallback = IBZUAAFallback.class)
public interface IBZUAAFeignClient
{
	/**
	 * 同步系统资源到uaa
	 * @param system 系统资源信息
	 * @return
	 */
	@PostMapping("/syspssystems/save")
	Boolean syncSysAuthority(@RequestBody JSONObject system);

	/**
	 * 用户登录
	 * @param authorizationLogin 登录信息
	 * @return
	 */
	@PostMapping(value = "/uaa/login")
	AuthenticationUser login(@RequestBody AuthorizationLogin authorizationLogin);

	@PostMapping(value = "/v7/login")
	AuthenticationInfo v7Login(@RequestBody AuthorizationLogin authorizationLogin);


	@GetMapping(value = "/uaa/{instid}/dynamodel")
	String getDynaModelIdByInstId(@PathVariable("instid") String instid);

	@PostMapping(value = "/uaa/loginbyusername")
	AuthenticationUser loginByUsername(@RequestHeader(name = "srfsystemid") String system,@RequestHeader(name = "srforgid") String orgid ,@RequestBody String username);

    @Cacheable(value="ibzuaa_publickey")
	@GetMapping(value = "/uaa/publickey")
	String getPublicKey();

	@GetMapping(value = {"/uaa/open/dingtalk/access_token"})
	JSONObject getDingtalkAppId(@RequestParam(value = "id", required = false) String id);

	@GetMapping(value = {"/uaa/open/dingtalk/auth/{code}"})
	AuthenticationInfo getUserByToken(@PathVariable(value = "code") String code, @RequestParam(value = "id",required = false) String id);

	@RequestMapping(method = RequestMethod.GET, value = "/uaa/dingtalk/jsapi/sign")
	public JSONObject getDingTalkJSSign(@RequestParam ("openaccessid") String openAccessId, @RequestParam ("url")String url);


	@PostMapping(value = "/v7/changepwd")
	Boolean changepwd(@RequestBody JSONObject jsonObject);

}
