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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysSFPub;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysSFPubSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSysSFPub] 服务对象接口
 */
@Component
public class PSSysSFPubFallback implements PSSysSFPubFeignClient {

    public Page<PSSysSFPub> select() {
        return null;
    }

    public PSSysSFPub create(PSSysSFPub pssyssfpub) {
        return null;
    }
    public Boolean createBatch(List<PSSysSFPub> pssyssfpubs) {
        return false;
    }

    public PSSysSFPub update(String pssyssfpubid, PSSysSFPub pssyssfpub) {
        return null;
    }
    public Boolean updateBatch(List<PSSysSFPub> pssyssfpubs) {
        return false;
    }


    public Boolean remove(String pssyssfpubid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSysSFPub get(String pssyssfpubid) {
        return null;
    }


    public PSSysSFPub getDraft(PSSysSFPub entity){
        return null;
    }



    public Boolean checkKey(PSSysSFPub pssyssfpub) {
        return false;
    }


    public Object saveEntity(PSSysSFPub pssyssfpub) {
        return null;
    }

    public Boolean save(PSSysSFPub pssyssfpub) {
        return false;
    }
    public Boolean saveBatch(List<PSSysSFPub> pssyssfpubs) {
        return false;
    }

    public Page<PSSysSFPub> searchBuild(PSSysSFPubSearchContext context) {
        return null;
    }


    public Page<PSSysSFPub> searchDefault(PSSysSFPubSearchContext context) {
        return null;
    }


}
