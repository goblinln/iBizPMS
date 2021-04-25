package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.mapper.UserMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class UserHelper extends ZTBaseHelper<UserMapper, User> {
    @Autowired
    ActionHelper actionHelper;

    @Autowired
    ISysEmployeeService sysEmployeeService;

    @Autowired
    IIbzproConfigService iIbzproConfigService;

    public Boolean login(String username) {
        actionHelper.create(StaticDict.Action__object_type.USER.getValue(), 0L, StaticDict.Action__type.LOGIN.getValue(), "", "", username, true);
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        return true;
    }

    public JSONObject getSettings() {
        JSONObject  jsonObject = new JSONObject();
        List<IbzproConfig> list = iIbzproConfigService.list(new QueryWrapper<IbzproConfig>().eq("createman", AuthenticationUser.getAuthenticationUser().getUserid()).eq("Scope", StaticDict.ConfigScope.USER.getValue()).eq("vaild", StaticDict.YesNo.ITEM_1.getValue()).orderByDesc("updatedate"));
       if(list.size() > 0) {
           jsonObject.put("srfmstatus", list.get(0).getManagementstatus());
       }else {
           jsonObject.put("srfmstatus", StaticDict.ConfigManagementstatus.PRODUCT_PROJECT.getValue());
       }
       return jsonObject;
    }

}
