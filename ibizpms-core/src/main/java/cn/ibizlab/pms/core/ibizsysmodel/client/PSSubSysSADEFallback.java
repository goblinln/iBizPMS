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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysSADE;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysSADESearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSubSysSADE] 服务对象接口
 */
@Component
public class PSSubSysSADEFallback implements PSSubSysSADEFeignClient {

    public Page<PSSubSysSADE> select() {
        return null;
    }

    public PSSubSysSADE create(PSSubSysSADE et) {
        return null;
    }
    public Boolean createBatch(List<PSSubSysSADE> pssubsyssades) {
        return false;
    }

    public PSSubSysSADE update(String pssubsyssadeid, PSSubSysSADE et) {
        return null;
    }
    public Boolean updateBatch(List<PSSubSysSADE> pssubsyssades) {
        return false;
    }


    public Boolean remove(String pssubsyssadeid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSubSysSADE get(String pssubsyssadeid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public PSSubSysSADE getDraft(PSSubSysSADE entity){
        return null;
    }



    public Boolean checkKey(PSSubSysSADE et) {
        return false;
    }


    public Object saveEntity(PSSubSysSADE et) {
        return null;
    }
    public Boolean save(PSSubSysSADE et) {
        return false;
    }
    public Boolean saveBatch(List<PSSubSysSADE> pssubsyssades) {
        return false;
    }

    public Page<PSSubSysSADE> searchDefault(PSSubSysSADESearchContext context) {
        return null;
    }


}
