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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysServiceAPISearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSysServiceAPI] 服务对象接口
 */
@Component
public class PSSysServiceAPIFallback implements PSSysServiceAPIFeignClient {

    public PSSysServiceAPI create(PSSysServiceAPI et) {
        return null;
    }
    public Boolean createBatch(List<PSSysServiceAPI> pssysserviceapis) {
        return false;
    }

    public PSSysServiceAPI get(String pssysserviceapiid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public Boolean remove(String pssysserviceapiid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSysServiceAPI update(String pssysserviceapiid, PSSysServiceAPI et) {
        return null;
    }
    public Boolean updateBatch(List<PSSysServiceAPI> pssysserviceapis) {
        return false;
    }


    public Boolean checkKey(PSSysServiceAPI et) {
        return false;
    }


    public Page<PSSysServiceAPI> searchDefault(PSSysServiceAPISearchContext context) {
        return null;
    }


    public PSSysServiceAPI getDraft(PSSysServiceAPI entity){
        return null;
    }



    public Object saveEntity(PSSysServiceAPI et) {
        return null;
    }
    public Boolean save(PSSysServiceAPI et) {
        return false;
    }
    public Boolean saveBatch(List<PSSysServiceAPI> pssysserviceapis) {
        return false;
    }

    public Page<PSSysServiceAPI> select() {
        return null;
    }

}
