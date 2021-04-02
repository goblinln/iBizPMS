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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSModuleSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSModule] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSModule", fallback = PSModuleFallback.class)
public interface PSModuleFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/psmodules/select")
    Page<PSModule> select();


    @RequestMapping(method = RequestMethod.POST, value = "/psmodules")
    PSModule create(@RequestBody PSModule et);

    @RequestMapping(method = RequestMethod.POST, value = "/psmodules/batch")
    Boolean createBatch(@RequestBody List<PSModule> psmodules);


    @RequestMapping(method = RequestMethod.PUT, value = "/psmodules/{psmoduleid}")
    PSModule update(@PathVariable("psmoduleid") String psmoduleid, @RequestBody PSModule et);

    @RequestMapping(method = RequestMethod.PUT, value = "/psmodules/batch")
    Boolean updateBatch(@RequestBody List<PSModule> psmodules);


    @RequestMapping(method = RequestMethod.DELETE, value = "/psmodules/{psmoduleid}")
    Boolean remove(@PathVariable("psmoduleid") String psmoduleid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/psmodules/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/psmodules/{psmoduleid}")
    PSModule get(@PathVariable("psmoduleid") String psmoduleid);

    @RequestMapping(method = RequestMethod.GET, value = "/psmodules/getbycodename/{psmoduleid}")
    String getByCodeName(@PathVariable("psmoduleid") String codeName);


    @RequestMapping(method = RequestMethod.GET, value = "/psmodules/getdraft")
    PSModule getDraft(PSModule entity);


    @RequestMapping(method = RequestMethod.POST, value = "/psmodules/checkkey")
    Boolean checkKey(@RequestBody PSModule et);


    @RequestMapping(method = RequestMethod.POST, value = "/psmodules/save")
    Object saveEntity(@RequestBody PSModule et);

    default Boolean save(@RequestBody PSModule et) { return saveEntity(et)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/psmodules/savebatch")
    Boolean saveBatch(@RequestBody List<PSModule> psmodules);



    @RequestMapping(method = RequestMethod.POST, value = "/psmodules/searchdefault")
    Page<PSModule> searchDefault(@RequestBody PSModuleSearchContext context);


}
