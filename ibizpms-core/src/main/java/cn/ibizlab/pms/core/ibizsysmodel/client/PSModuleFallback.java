package cn.ibizlab.pms.core.ibizsysmodel.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSModuleSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSModule] 服务对象接口
 */
@Component
public class PSModuleFallback implements PSModuleFeignClient {

    public Page<PSModule> select() {
        return null;
    }

    public PSModule create(PSModule et) {
        return null;
    }
    public Boolean createBatch(List<PSModule> psmodules) {
        return false;
    }

    public PSModule update(String psmoduleid, PSModule et) {
        return null;
    }
    public Boolean updateBatch(List<PSModule> psmodules) {
        return false;
    }


    public Boolean remove(String psmoduleid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSModule get(String psmoduleid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public PSModule getDraft(PSModule entity){
        return null;
    }



    public Boolean checkKey(PSModule et) {
        return false;
    }


    public Object saveEntity(PSModule et) {
        return null;
    }
    public Boolean save(PSModule et) {
        return false;
    }
    public Boolean saveBatch(List<PSModule> psmodules) {
        return false;
    }

    public Page<PSModule> searchDefault(PSModuleSearchContext context) {
        return null;
    }


}
