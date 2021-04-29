package cn.ibizlab.pms.util.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${ibiz.ref.service.pms:pms-webapi}", fallback = IPMSFeignFallback.class)
public interface IPMSFeignClient {

    /**
     * ZT API系统登录
     *
     * @param account 统一认证登录名
     * @param token   生成的token，用于生成sessionId
     * @return 正常返回，说明成功登录、否则抛出异常。
     */
    @PostMapping(value = "/ztlogin")
    JSONObject doZTLogin(@RequestParam("account") String account, @RequestParam("token") String token);


    /**
     * 查询ZT账户信息
     *
     * @param uaaloginname uaa认证登录名
     * @return 账号信息
     */
    @GetMapping(value = "/ztusers/uaaloginname")
    JSONObject getZTUser(@RequestParam("uaaloginname") String uaaloginname);

    /**
     * 记录
     *
     * @return true：成功
     */
    @PostMapping(value = "/recordloginlog")
    Boolean recordLoginLog(@RequestParam("username") String username);

    /**
     *  获取管理模式
     *
     * @return
     */
    @GetMapping(value = "/getsrfmstatus")
    JSONObject getSrfMStatus();
}