package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.impl.UserServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[用户] 自定义服务对象
 */
@Slf4j
@Primary
@Service("UserExService")
public class UserExService extends UserServiceImpl {

    @Autowired
    IActionService iActionService;

    @Autowired
    IIbzproConfigService iIbzproConfigService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SyncAccount:同步账号] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public User syncAccount(User et) {
        return super.syncAccount(et);
    }

    public Boolean login(String username) {
        // 创建日志
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.USER.getValue());
        action.setObjectid(0L);
        action.setAction(StaticDict.Action__type.LOGIN.getValue());
        action.setComment("");
        action.setExtra("");
        action.setActor(username);
        iActionService.createHis(action);
//        iActionService.create(StaticDict.Action__object_type.USER.getValue(), 0L, StaticDict.Action__type.LOGIN.getValue(), "", "", username, true);
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

