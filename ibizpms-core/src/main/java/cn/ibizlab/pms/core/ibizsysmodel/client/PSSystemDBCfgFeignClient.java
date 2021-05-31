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
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSystemDBCfg] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSystemDBCfg", fallback = PSSystemDBCfgFallback.class)
public interface PSSystemDBCfgFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/select")
    Page<PSSystemDBCfg> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs")
    PSSystemDBCfg create(@RequestBody PSSystemDBCfg pssystemdbcfg);

    @RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/batch")
    Boolean createBatch(@RequestBody List<PSSystemDBCfg> pssystemdbcfgs);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssystemdbcfgs/{pssystemdbcfgid}")
    PSSystemDBCfg update(@PathVariable("pssystemdbcfgid") String pssystemdbcfgid,@RequestBody PSSystemDBCfg pssystemdbcfg);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssystemdbcfgs/batch")
    Boolean updateBatch(@RequestBody List<PSSystemDBCfg> pssystemdbcfgs);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssystemdbcfgs/{pssystemdbcfgid}")
    Boolean remove(@PathVariable("pssystemdbcfgid") String pssystemdbcfgid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssystemdbcfgs/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/{pssystemdbcfgid}")
    PSSystemDBCfg get(@PathVariable("pssystemdbcfgid") String pssystemdbcfgid);


    @RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/getdraft")
    PSSystemDBCfg getDraft(PSSystemDBCfg entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/checkkey")
    Boolean checkKey(@RequestBody PSSystemDBCfg pssystemdbcfg);


    @RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/save")
    Object saveEntity(@RequestBody PSSystemDBCfg pssystemdbcfg);

    default Boolean save(@RequestBody PSSystemDBCfg pssystemdbcfg) { return saveEntity(pssystemdbcfg)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssystemdbcfgs/savebatch")
    Boolean saveBatch(@RequestBody List<PSSystemDBCfg> pssystemdbcfgs);



    @RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/fetchbuild")
    Page<PSSystemDBCfg> searchBuild(@RequestBody PSSystemDBCfgSearchContext context);



    @RequestMapping(method = RequestMethod.GET, value = "/pssystemdbcfgs/fetchdefault")
    Page<PSSystemDBCfg> searchDefault(@RequestBody PSSystemDBCfgSearchContext context);



}
