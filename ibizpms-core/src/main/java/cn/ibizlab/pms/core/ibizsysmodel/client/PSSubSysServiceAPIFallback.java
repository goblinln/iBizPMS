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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysServiceAPISearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSubSysServiceAPI] 服务对象接口
 */
@Component
public class PSSubSysServiceAPIFallback implements PSSubSysServiceAPIFeignClient {

    public Page<PSSubSysServiceAPI> select() {
        return null;
    }

    public PSSubSysServiceAPI create(PSSubSysServiceAPI et) {
        return null;
    }
    public Boolean createBatch(List<PSSubSysServiceAPI> pssubsysserviceapis) {
        return false;
    }

    public PSSubSysServiceAPI update(String pssubsysserviceapiid, PSSubSysServiceAPI et) {
        return null;
    }
    public Boolean updateBatch(List<PSSubSysServiceAPI> pssubsysserviceapis) {
        return false;
    }


    public Boolean remove(String pssubsysserviceapiid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSubSysServiceAPI get(String pssubsysserviceapiid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public PSSubSysServiceAPI getDraft(PSSubSysServiceAPI entity){
        return null;
    }



    public Boolean checkKey(PSSubSysServiceAPI et) {
        return false;
    }


    public Boolean save(PSSubSysServiceAPI et) {
        return false;
    }
    public Boolean saveBatch(List<PSSubSysServiceAPI> pssubsysserviceapis) {
        return false;
    }

    public Page<PSSubSysServiceAPI> searchDefault(PSSubSysServiceAPISearchContext context) {
        return null;
    }


}
