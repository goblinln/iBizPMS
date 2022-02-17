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
import cn.ibizlab.pms.core.ou.domain.SysTeam;
import cn.ibizlab.pms.core.ou.filter.SysTeamSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[SysTeam] 服务对象接口
 */
@Component
public class SysTeamFallback implements SysTeamFeignClient {

    public SysTeam create(SysTeam et) {
        return null;
    }
    public Boolean createBatch(List<SysTeam> systeams) {
        return false;
    }

    public SysTeam get(String teamid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public Boolean remove(String teamid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public SysTeam update(String teamid, SysTeam et) {
        return null;
    }
    public Boolean updateBatch(List<SysTeam> systeams) {
        return false;
    }


    public Boolean checkKey(SysTeam et) {
        return false;
    }


    public Page<SysTeam> searchDefault(SysTeamSearchContext context) {
        return null;
    }


    public SysTeam getDraft(SysTeam entity){
        return null;
    }



    public Object saveEntity(SysTeam et) {
        return null;
    }
    public Boolean save(SysTeam et) {
        return false;
    }
    public Boolean saveBatch(List<SysTeam> systeams) {
        return false;
    }

    public Page<SysTeam> select() {
        return null;
    }

}
