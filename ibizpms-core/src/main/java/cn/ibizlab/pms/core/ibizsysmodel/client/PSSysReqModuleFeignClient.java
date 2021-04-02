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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqModuleSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSysReqModule] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSysReqModule", fallback = PSSysReqModuleFallback.class)
public interface PSSysReqModuleFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqmodules/select")
    Page<PSSysReqModule> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqmodules")
    PSSysReqModule create(@RequestBody PSSysReqModule et);

    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqmodules/batch")
    Boolean createBatch(@RequestBody List<PSSysReqModule> pssysreqmodules);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssysreqmodules/{pssysreqmoduleid}")
    PSSysReqModule update(@PathVariable("pssysreqmoduleid") String pssysreqmoduleid, @RequestBody PSSysReqModule et);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssysreqmodules/batch")
    Boolean updateBatch(@RequestBody List<PSSysReqModule> pssysreqmodules);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysreqmodules/{pssysreqmoduleid}")
    Boolean remove(@PathVariable("pssysreqmoduleid") String pssysreqmoduleid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysreqmodules/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqmodules/{pssysreqmoduleid}")
    PSSysReqModule get(@PathVariable("pssysreqmoduleid") String pssysreqmoduleid);

    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqmodules/getbycodename/{pssysreqmoduleid}")
    String getByCodeName(@PathVariable("pssysreqmoduleid") String codeName);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqmodules/getdraft")
    PSSysReqModule getDraft(PSSysReqModule entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqmodules/checkkey")
    Boolean checkKey(@RequestBody PSSysReqModule et);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqmodules/save")
    Object saveEntity(@RequestBody PSSysReqModule et);

    default Boolean save(@RequestBody PSSysReqModule et) { return saveEntity(et)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqmodules/savebatch")
    Boolean saveBatch(@RequestBody List<PSSysReqModule> pssysreqmodules);



    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqmodules/searchdefault")
    Page<PSSysReqModule> searchDefault(@RequestBody PSSysReqModuleSearchContext context);


}
