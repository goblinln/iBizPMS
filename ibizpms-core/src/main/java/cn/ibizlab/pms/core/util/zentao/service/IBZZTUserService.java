package cn.ibizlab.pms.core.util.zentao.service;


import cn.ibizlab.pms.core.zentao.service.IUserService;
import cn.ibizlab.pms.util.errors.InternalServerErrorException;
import cn.ibizlab.pms.util.security.AuthTokenUtil;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.service.AuthenticationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 账户认证：ZT (account，password)
 * 用户信息：ZT (username，gender....)
 */
@Service("IBZZTUserService")
@ConditionalOnExpression("(!${ibiz.enablePermissionValid:false})&&'${ibiz.auth.service:SimpleUserService}'.equals('IBZZTUserService')")
public class IBZZTUserService implements AuthenticationUserService {

    @Autowired
    private IUserService userService;
    @Autowired
    private AuthTokenUtil jwtTokenUtil;

    @Override
    public AuthenticationUser loadUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new InternalServerErrorException("用户还未登录");
        }
        String[] accountInfo = username.split("[|]");
        String loginname = username;
        String domains = "";
        if (accountInfo.length == 2) {
            loginname = accountInfo[0].trim();
            domains = accountInfo[1].trim();
        }
        AuthenticationUser user = AuthenticationUser.getAuthenticationUser();
        if (user == null || user.getUsername() == null || !loginname.equals(user.getUsername())) {
            throw new InternalServerErrorException("用户还未登录");
        }

        return user;
    }

    @Override
    public AuthenticationUser loadUserByLogin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new InternalServerErrorException("请输入用户名密码");
        }
        String[] accountInfo = username.split("[|]");
        String loginname = username;
        String domains = "";

        if (accountInfo.length == 2) {
            loginname = accountInfo[0].trim();
            domains = accountInfo[1].trim();
        }

//        ZTResult rstSession = new ZTResult();
//        if (!ZTAPIHelper.getSessionID(rstSession)) {
//            throw new InternalServerErrorException("登录失败");
//        }
//
//        String zentaoSid1 = JSONObject.parseObject(rstSession.getResult().getString("data")).getString("sessionID");
//        ZTResult rstSession2 = new ZTResult();
//        if (!ZTAPIHelper.getSessionID(rstSession2,zentaoSid1)) {
//            throw new InternalServerErrorException("登录失败");
//        }

//        User ztUser = cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.UserHelper.class).getUserInfo(loginname);
//        if (!cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.UserHelper.class).login(ztUser)) {
//            throw new InternalServerErrorException("登录失败");
//        }

//        JSONObject userJO = (JSONObject) JSON.toJSON(ztUser);
//        AuthenticationUser user = new AuthenticationUser();
//        user.setUserid(userJO.getString("id"));
//        user.setUsername(userJO.getString("account"));
//        user.setLoginname(userJO.getString("account"));
//        user.setDomain(domains);
//        user.setEmail(userJO.getString("email"));
//        user.setAvatar(userJO.getString("avatar"));
//        user.setNickname(userJO.getString("nickname"));
//        if (userJO.getString("birthday") == null || userJO.getString("birthday").isEmpty() || "0000-00-00".equals(userJO.getString("birthday"))) {
//            user.setBirthday(null);
//        } else {
//            user.setBirthday(userJO.getTimestamp("birthday"));
//        }
//        user.setAddr(userJO.getString("address"));
//        user.setSex(userJO.getString("gender"));
//        Map<String,Object> sessionParams = user.getSessionParams();
//        sessionParams.put("ztuser", userJO);
//        sessionParams.put("zentaosid", zentaoSid1);
//        sessionParams.put("token", zentaoSid1);
//
//        user.setSessionParams(sessionParams);

        AuthenticationUser user = new AuthenticationUser();
        user.setUserid(loginname);
        user.setUsername(loginname);
        user.setLoginname(loginname);
        user.setDomain(domains);
        user.setEmail("");
        user.setAvatar("");
        user.setNickname(loginname);
        user.setBirthday(null);
        user.setAddr("");
        user.setSex("");
        user.setOrgid("0010");
        user.setPersonname(loginname);
        Map<String,Object> sessionParams = user.getSessionParams();

        user.setSessionParams(sessionParams);


        // 权限默认给管理员（权限未接入之前）
        user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_SUPERADMIN"));
        return user;
    }


    @Override
    public void resetByUsername(String username) {

    }

}
