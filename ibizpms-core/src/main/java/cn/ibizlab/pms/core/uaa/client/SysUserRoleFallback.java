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
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[SysUserRole] 服务对象接口
 */
@Component
public class SysUserRoleFallback implements SysUserRoleFeignClient {

    public Page<SysUserRole> select() {
        return null;
    }

    public SysUserRole create(SysUserRole et) {
        return null;
    }
    public Boolean createBatch(List<SysUserRole> sysuserroles) {
        return false;
    }

    public SysUserRole update(String userroleid, SysUserRole et) {
        return null;
    }
    public Boolean updateBatch(List<SysUserRole> sysuserroles) {
        return false;
    }


    public Boolean remove(String userroleid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public SysUserRole get(String userroleid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public SysUserRole getDraft(SysUserRole entity){
        return null;
    }



    public Boolean checkKey(SysUserRole et) {
        return false;
    }


    public Boolean save(SysUserRole et) {
        return false;
    }
    public Boolean saveBatch(List<SysUserRole> sysuserroles) {
        return false;
    }

    public Page<SysUserRole> searchDefault(SysUserRoleSearchContext context) {
        return null;
    }


}
