package cn.ibizlab.pms.core.ibizplugin.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[IBIZProKeyword] 服务对象接口
 */
@Component
public class IBIZProKeywordFallback implements IBIZProKeywordFeignClient {

    public Page<IBIZProKeyword> select() {
        return null;
    }

    public IBIZProKeyword create(IBIZProKeyword et) {
        return null;
    }
    public Boolean createBatch(List<IBIZProKeyword> ibizprokeywords) {
        return false;
    }

    public IBIZProKeyword update(String id, IBIZProKeyword et) {
        return null;
    }
    public Boolean updateBatch(List<IBIZProKeyword> ibizprokeywords) {
        return false;
    }


    public Boolean remove(String id) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public IBIZProKeyword get(String id) {
        return null;
    }


    public String getByCodeName(String codeName) {
            return null;
    }


    public IBIZProKeyword getDraft(IBIZProKeyword entity){
        return null;
    }



    public Boolean checkKey(IBIZProKeyword et) {
        return false;
    }


    public Object saveEntity(IBIZProKeyword et) {
        return null;
    }
    public Boolean save(IBIZProKeyword et) {
        return false;
    }
    public Boolean saveBatch(List<IBIZProKeyword> ibizprokeywords) {
        return false;
    }

    public Page<IBIZProKeyword> searchDefault(IBIZProKeywordSearchContext context) {
        return null;
    }


}
