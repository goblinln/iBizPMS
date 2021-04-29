package cn.ibizlab.pms.util.rest;

import cn.ibizlab.pms.util.client.IBZUAAFeignClient;
import cn.ibizlab.pms.util.client.IPMSFeignClient;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.log.IBIZTraceLog;
import cn.ibizlab.pms.util.security.AuthTokenUtil;
import cn.ibizlab.pms.util.security.AuthenticationInfo;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.AuthorizationLogin;
import cn.ibizlab.pms.util.service.AuthenticationUserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@IBIZTraceLog
@RestController
@ConditionalOnExpression("${ibiz.enablePermissionValid:false}")
public class UAAPipeResource {
    @Autowired
    private IBZUAAFeignClient uaaFeignClient;

    @Autowired
    private IPMSFeignClient pmsfeignClient;
    @Autowired
    AuthenticationUserService userDetailsService;
    @Autowired
    private AuthTokenUtil jwtTokenUtil;

    @Value("${ibiz.auth.pwencrymode:0}")
    private int pwencrymode;

    @Value("${ibiz.auth.ladp:false}")
    private boolean ladp;


    @PostMapping("/v7/login")
    public AuthenticationInfo login(@Validated @RequestBody AuthorizationLogin authenticationLogin) {
        if(!ladp) {
            String pwd = authenticationLogin.getPassword();
            authenticationLogin.getLoginname();
            if (pwencrymode == 1) {
                pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
            } else if (pwencrymode == 2) {
                pwd = DigestUtils.md5DigestAsHex(String.format("%1$s||%2$s", authenticationLogin.getUsername(), pwd).getBytes());
            }
            authenticationLogin.setPassword(pwd);
        }
        AuthenticationInfo info = uaaFeignClient.v7Login(authenticationLogin);

        if (info == null) {
            log.error("登录失败。");
            throw new BadRequestAlertException("系统异常，登录失败，请联系管理员", null, null);
        }

//        //需要登录禅道API
//        ztLogin(info);

        // 记录登陆日志
    //    recordLoginLog(info.getUser().getUsername());

        log.info("登录成功！");
        return info;
    }

    @PostMapping("/v7/changepwd")
    public Boolean changepwd(@Validated @RequestBody JSONObject jsonObject) {
        if (!ladp) {
            // 加密原密码和新密码
            String oldPwd = jsonObject.getString("oldPwd");
            String newPwd = jsonObject.getString("newPwd");

            if (pwencrymode == 1) {
                oldPwd = DigestUtils.md5DigestAsHex(oldPwd.getBytes());
                newPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
            } else if (pwencrymode == 2) {
                String userName = AuthenticationUser.getAuthenticationUser().getUsername();
                oldPwd = DigestUtils.md5DigestAsHex(String.format("%1$s||%2$s", userName, oldPwd).getBytes());
                newPwd = DigestUtils.md5DigestAsHex(String.format("%1$s||%2$s", userName, newPwd).getBytes());
            }

            jsonObject.put("oldPwd", oldPwd);
            jsonObject.put("newPwd", newPwd);
        }

        // 调用uaa的接口更改密码
        uaaFeignClient.changepwd(jsonObject);
        return true;
    }

    @GetMapping(value = {"/uaa/open/dingtalk/access_token"})
    public ResponseEntity<JSONObject> getDingtalkAppId(@RequestParam(value = "id", required = false) String id) {
        String openAccessId = StringUtils.isEmpty(id) ? getRefererURL() : id;
        log.info("[UAAPipeResource.getDingtalkAppId] openAccessId: " + openAccessId);
        JSONObject resp = uaaFeignClient.getDingtalkAppId(openAccessId);
        resp.put("regionid", resp.get("corp_id"));
        log.info("[UAAPipeResource.getDingtalkAppId] response: " + resp);
        return ResponseEntity.ok(resp);
    }

    /**
     * 获取钉钉jsApi签名
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/uaa/dingtalk/jsapi/sign")
    public ResponseEntity<JSONObject> getDingTalkJSSign(@RequestParam(value = "id", required = false) String id){
        String openAccessId = StringUtils.isEmpty(id) ? getRefererURL() : id;
        String url = getRequestUrl();
        log.info("[UAAPipeResource.getDingTalkJSSign] openAccessId: " + openAccessId + ", url: " + url);
        JSONObject resp = uaaFeignClient.getDingTalkJSSign(openAccessId, url);
        log.info("[UAAPipeResource.getDingTalkJSSign] response: " + resp);
        return ResponseEntity.ok(resp);
    }

    @GetMapping(value = {"/uaa/open/dingtalk/auth/{code}"})
    public ResponseEntity<AuthenticationInfo> getUserByToken(@PathVariable(value = "code") String code, @RequestParam(value = "id", required = false) String id) {
        String openAccessId = StringUtils.isEmpty(id) ? getRefererURL() : id;
        log.info("[UAAPipeResource.getUserByToken] code: " + code + ", openAccessId: " + openAccessId);
        AuthenticationInfo info = uaaFeignClient.getUserByToken(code, openAccessId);
        log.info("[UAAPipeResource.getUserByToken] response: " + info);
//        ztLogin(info);
        // 记录登陆日志
        recordLoginLog(info.getUser().getUsername());
        return ResponseEntity.ok(info);

    }

    @PostMapping("/login/guest")
    public ResponseEntity<AuthenticationInfo> guestLogin(){
        final String guest = "pms_guest";

        userDetailsService.resetByUsername(guest);
        AuthenticationUser user = userDetailsService.loadUserByUsername(guest);

        final String token = jwtTokenUtil.generateToken(user);

        AuthenticationUser user2=new AuthenticationUser();
        CachedBeanCopier.copy(user,user2);
        user2.setAuthorities(null);
        user2.setPermissionList(null);

//        ztLogin(user.getLoginname(),token);
        //        // 记录登陆日志
        recordLoginLog(user.getUsername());

        // 返回 token
        return ResponseEntity.ok().body(new AuthenticationInfo(token,user2));
    }

    /**
     * 返回当前请求的来源URL
     *
     * @return 钉钉的域名，用于查看第三方平台开放相关配置（本地配置）
     */
    private String getRefererURL() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String fromUrl = request.getHeader("referer");
        log.info("请求来源：{}", fromUrl);
        return StringUtils.isEmpty(fromUrl) ? "dingtalk" : fromUrl;
    }

    /**
     * 返回当前请求的url的IP或者域名
     *
     * @return 钉钉的地址
     */
    private String getRequestUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String fromUrl = request.getHeader("referer");
        if (StringHelper.isNullOrEmptyString(fromUrl)) {
            if (!StringHelper.isNullOrEmptyString(request.getHeader("host"))) {
                fromUrl = request.getScheme() + "://" + request.getHeader("host");
            } else {
                fromUrl = request.getScheme() + "://127.0.0.1/";
            }
        }
        log.info("请求的url的IP或者域名：{}", fromUrl);
        return fromUrl;
    }

    private void recordLoginLog(String username) {
        try {
            if (!pmsfeignClient.recordLoginLog(username)) {
                log.info("登陆日志记录失败");
            }
        } catch (Exception e) {
            log.info("登陆日志记录失败，原因为[{}]", e);
        }


    }

    @Deprecated
    private void ztLogin(AuthenticationInfo authenticationInfo) {
        if (authenticationInfo == null) {
            log.error("系统发生异常，请联系程序猿。");
            throw new BadRequestAlertException("系统异常，请联系管理员。", null, null);
        }

        ztLogin(authenticationInfo.getUser().getLoginname(),authenticationInfo.getToken());
    }

    @Deprecated
    private void ztLogin(String account,String token){
        boolean isSucceed = false;
        try { //禅道登录。
            JSONObject resp = pmsfeignClient.doZTLogin(account, token);
            if (resp != null)
                isSucceed = true;
        } catch (Exception e) {
            log.info("禅道API 登录失败，原因为[{}]", e);
        }

        if (isSucceed) {
            log.info("禅道后台登录成功！");
        } else {
            log.error("禅道后台登录失败");
            throw new BadRequestAlertException("系统异常，请联系管理员。", null, null);
        }
    }
}

