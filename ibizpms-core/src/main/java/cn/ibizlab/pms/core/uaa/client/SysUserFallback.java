package cn.ibizlab.pms.core.uaa.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import cn.ibizlab.pms.core.uaa.domain.SysUser;
import cn.ibizlab.pms.core.uaa.filter.SysUserSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[SysUser] 服务对象接口
 */
@Component
public class SysUserFallback implements SysUserFeignClient {

    public Page<SysUser> select() {
        return null;
    }

    public SysUser create(SysUser sysuser) {
        return null;
    }
    public Boolean createBatch(List<SysUser> sysusers) {
        return false;
    }

    public SysUser update(String userid, SysUser sysuser) {
        return null;
    }
    public Boolean updateBatch(List<SysUser> sysusers) {
        return false;
    }


    public Boolean remove(String userid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public SysUser get(String userid) {
        return null;
    }


    public SysUser getDraft(SysUser entity){
        return null;
    }



    public SysUser changePwd( String userid, SysUser sysuser) {
        return null;
    }

    public Boolean checkKey(SysUser sysuser) {
        return false;
    }


    public Object saveEntity(SysUser sysuser) {
        return null;
    }

    public Boolean save(SysUser sysuser) {
        return false;
    }
    public Boolean saveBatch(List<SysUser> sysusers) {
        return false;
    }

    public Page<SysUser> searchDefault(SysUserSearchContext context) {
        return null;
    }


    public Page<SysUser> searchMyWork(SysUserSearchContext context) {
        return null;
    }


    public Page<SysUser> searchPersonInfo(SysUserSearchContext context) {
        return null;
    }



}
