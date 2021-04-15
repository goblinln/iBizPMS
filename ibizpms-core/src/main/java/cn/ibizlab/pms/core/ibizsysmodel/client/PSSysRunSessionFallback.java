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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysRunSession;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysRunSessionSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSysRunSession] 服务对象接口
 */
@Component
public class PSSysRunSessionFallback implements PSSysRunSessionFeignClient {

    public Page<PSSysRunSession> select() {
        return null;
    }

    public PSSysRunSession create(PSSysRunSession pssysrunsession) {
        return null;
    }
    public Boolean createBatch(List<PSSysRunSession> pssysrunsessions) {
        return false;
    }

    public PSSysRunSession update(String pssysrunsessionid, PSSysRunSession pssysrunsession) {
        return null;
    }
    public Boolean updateBatch(List<PSSysRunSession> pssysrunsessions) {
        return false;
    }


    public Boolean remove(String pssysrunsessionid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSysRunSession get(String pssysrunsessionid) {
        return null;
    }


    public PSSysRunSession getDraft(PSSysRunSession entity){
        return null;
    }



    public Boolean checkKey(PSSysRunSession pssysrunsession) {
        return false;
    }


    public Object saveEntity(PSSysRunSession pssysrunsession) {
        return null;
    }

    public Boolean save(PSSysRunSession pssysrunsession) {
        return false;
    }
    public Boolean saveBatch(List<PSSysRunSession> pssysrunsessions) {
        return false;
    }

    public Page<PSSysRunSession> searchDefault(PSSysRunSessionSearchContext context) {
        return null;
    }


}
