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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqModuleSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSysReqModule] 服务对象接口
 */
@Component
public class PSSysReqModuleFallback implements PSSysReqModuleFeignClient {

    public Page<PSSysReqModule> select() {
        return null;
    }

    public PSSysReqModule create(PSSysReqModule pssysreqmodule) {
        return null;
    }
    public Boolean createBatch(List<PSSysReqModule> pssysreqmodules) {
        return false;
    }

    public PSSysReqModule update(String pssysreqmoduleid, PSSysReqModule pssysreqmodule) {
        return null;
    }
    public Boolean updateBatch(List<PSSysReqModule> pssysreqmodules) {
        return false;
    }


    public Boolean remove(String pssysreqmoduleid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSysReqModule get(String pssysreqmoduleid) {
        return null;
    }


    public PSSysReqModule getDraft(PSSysReqModule entity){
        return null;
    }



    public Boolean checkKey(PSSysReqModule pssysreqmodule) {
        return false;
    }


    public Object saveEntity(PSSysReqModule pssysreqmodule) {
        return null;
    }

    public Boolean save(PSSysReqModule pssysreqmodule) {
        return false;
    }
    public Boolean saveBatch(List<PSSysReqModule> pssysreqmodules) {
        return false;
    }

    public Page<PSSysReqModule> searchDefault(PSSysReqModuleSearchContext context) {
        return null;
    }


}
