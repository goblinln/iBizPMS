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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDataEntity;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDataEntitySearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSDataEntity] 服务对象接口
 */
@Component
public class PSDataEntityFallback implements PSDataEntityFeignClient {

    public Page<PSDataEntity> select() {
        return null;
    }

    public PSDataEntity create(PSDataEntity et) {
        return null;
    }
    public Boolean createBatch(List<PSDataEntity> psdataentities) {
        return false;
    }

    public PSDataEntity update(String psdataentityid, PSDataEntity et) {
        return null;
    }
    public Boolean updateBatch(List<PSDataEntity> psdataentities) {
        return false;
    }


    public Boolean remove(String psdataentityid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSDataEntity get(String psdataentityid) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public PSDataEntity getDraft(PSDataEntity entity){
        return null;
    }



    public Boolean checkKey(PSDataEntity et) {
        return false;
    }


    public Object saveEntity(PSDataEntity et) {
        return null;
    }
    public Boolean save(PSDataEntity et) {
        return false;
    }
    public Boolean saveBatch(List<PSDataEntity> psdataentities) {
        return false;
    }

    public Page<PSDataEntity> searchDefault(PSDataEntitySearchContext context) {
        return null;
    }


}
