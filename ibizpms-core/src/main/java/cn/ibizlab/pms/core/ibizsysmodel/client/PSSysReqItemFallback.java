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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqItem;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqItemSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSysReqItem] 服务对象接口
 */
@Component
public class PSSysReqItemFallback implements PSSysReqItemFeignClient {

    public PSSysReqItem create(PSSysReqItem et) {
        return null;
    }
    public Boolean createBatch(List<PSSysReqItem> pssysreqitems) {
        return false;
    }

    public PSSysReqItem get(String pssysreqitemid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public Boolean remove(String pssysreqitemid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSysReqItem update(String pssysreqitemid, PSSysReqItem et) {
        return null;
    }
    public Boolean updateBatch(List<PSSysReqItem> pssysreqitems) {
        return false;
    }


    public Boolean checkKey(PSSysReqItem et) {
        return false;
    }


    public Page<PSSysReqItem> searchDefault(PSSysReqItemSearchContext context) {
        return null;
    }


    public PSSysReqItem getDraft(PSSysReqItem entity){
        return null;
    }



    public Object saveEntity(PSSysReqItem et) {
        return null;
    }
    public Boolean save(PSSysReqItem et) {
        return false;
    }
    public Boolean saveBatch(List<PSSysReqItem> pssysreqitems) {
        return false;
    }

    public Page<PSSysReqItem> select() {
        return null;
    }

}
