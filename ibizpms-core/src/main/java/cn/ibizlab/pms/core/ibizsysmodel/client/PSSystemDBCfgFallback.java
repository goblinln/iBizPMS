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
import com.alibaba.fastjson.JSONObject;
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSystemDBCfg;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSystemDBCfgSearchContext;
import org.springframework.stereotype.Component;

/**
 * 实体[PSSystemDBCfg] 服务对象接口
 */
@Component
public class PSSystemDBCfgFallback implements PSSystemDBCfgFeignClient {

    public Page<PSSystemDBCfg> select() {
        return null;
    }

    public PSSystemDBCfg create(PSSystemDBCfg pssystemdbcfg) {
        return null;
    }
    public Boolean createBatch(List<PSSystemDBCfg> pssystemdbcfgs) {
        return false;
    }

    public PSSystemDBCfg update(String pssystemdbcfgid, PSSystemDBCfg pssystemdbcfg) {
        return null;
    }
    public Boolean updateBatch(List<PSSystemDBCfg> pssystemdbcfgs) {
        return false;
    }


    public Boolean remove(String pssystemdbcfgid) {
        return false;
    }
    public Boolean removeBatch(Collection<String> idList) {
        return false;
    }

    public PSSystemDBCfg get(String pssystemdbcfgid) {
        return null;
    }


    public PSSystemDBCfg getDraft(PSSystemDBCfg entity){
        return null;
    }



    public Boolean checkKey(PSSystemDBCfg pssystemdbcfg) {
        return false;
    }


    public Object saveEntity(PSSystemDBCfg pssystemdbcfg) {
        return null;
    }

    public Boolean save(PSSystemDBCfg pssystemdbcfg) {
        return false;
    }
    public Boolean saveBatch(List<PSSystemDBCfg> pssystemdbcfgs) {
        return false;
    }

    public Page<PSSystemDBCfg> searchBuild(PSSystemDBCfgSearchContext context) {
        return null;
    }


    public Page<PSSystemDBCfg> searchDefault(PSSystemDBCfgSearchContext context) {
        return null;
    }



}
