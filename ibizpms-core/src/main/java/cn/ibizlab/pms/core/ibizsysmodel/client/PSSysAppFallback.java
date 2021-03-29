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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysAppSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSysApp] 服务对象接口
 */
@Component
public class PSSysAppFallback implements PSSysAppFeignClient {

    public Page<PSSysApp> select() {
        return null;
    }

    public PSSysApp create(PSSysApp et) {
        return null;
    }
    public Boolean createBatch(List<PSSysApp> pssysapps) {
        return false;
    }

    public PSSysApp update(String pssysappid, PSSysApp et) {
        return null;
    }
    public Boolean updateBatch(List<PSSysApp> pssysapps) {
        return false;
    }


    public Boolean remove(String pssysappid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSysApp get(String pssysappid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public PSSysApp getDraft(PSSysApp entity){
        return null;
    }



    public Boolean checkKey(PSSysApp et) {
        return false;
    }


    public Boolean save(PSSysApp et) {
        return false;
    }
    public Boolean saveBatch(List<PSSysApp> pssysapps) {
        return false;
    }

    public Page<PSSysApp> searchBuild(PSSysAppSearchContext context) {
        return null;
    }


    public Page<PSSysApp> searchDefault(PSSysAppSearchContext context) {
        return null;
    }


}
