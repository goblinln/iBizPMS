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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDEFieldSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSDEField] 服务对象接口
 */
@Component
public class PSDEFieldFallback implements PSDEFieldFeignClient {

    public Page<PSDEField> select() {
        return null;
    }

    public PSDEField create(PSDEField psdefield) {
        return null;
    }
    public Boolean createBatch(List<PSDEField> psdefields) {
        return false;
    }

    public PSDEField update(String psdefieldid, PSDEField psdefield) {
        return null;
    }
    public Boolean updateBatch(List<PSDEField> psdefields) {
        return false;
    }


    public Boolean remove(String psdefieldid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSDEField get(String psdefieldid) {
        return null;
    }


    public PSDEField getDraft(PSDEField entity){
        return null;
    }



    public Boolean checkKey(PSDEField psdefield) {
        return false;
    }


    public Object saveEntity(PSDEField psdefield) {
        return null;
    }

    public Boolean save(PSDEField psdefield) {
        return false;
    }
    public Boolean saveBatch(List<PSDEField> psdefields) {
        return false;
    }

    public Page<PSDEField> searchDefault(PSDEFieldSearchContext context) {
        return null;
    }


}
