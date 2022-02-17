package cn.ibizlab.pms.core.ou.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import cn.ibizlab.pms.core.ou.domain.SysTeamMember;
import cn.ibizlab.pms.core.ou.filter.SysTeamMemberSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[SysTeamMember] 服务对象接口
 */
@Component
public class SysTeamMemberFallback implements SysTeamMemberFeignClient {

    public SysTeamMember create(SysTeamMember et) {
        return null;
    }
    public Boolean createBatch(List<SysTeamMember> systeammembers) {
        return false;
    }

    public SysTeamMember get(String teammemberid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public Boolean remove(String teammemberid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public SysTeamMember update(String teammemberid, SysTeamMember et) {
        return null;
    }
    public Boolean updateBatch(List<SysTeamMember> systeammembers) {
        return false;
    }


    public Boolean checkKey(SysTeamMember et) {
        return false;
    }


    public Page<SysTeamMember> searchDefault(SysTeamMemberSearchContext context) {
        return null;
    }


    public SysTeamMember getDraft(SysTeamMember entity){
        return null;
    }



    public Object saveEntity(SysTeamMember et) {
        return null;
    }
    public Boolean save(SysTeamMember et) {
        return false;
    }
    public Boolean saveBatch(List<SysTeamMember> systeammembers) {
        return false;
    }

    public Page<SysTeamMember> select() {
        return null;
    }

}
