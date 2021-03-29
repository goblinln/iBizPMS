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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysSADE;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysSADESearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSubSysSADE] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSubSysSADE", fallback = PSSubSysSADEFallback.class)
public interface PSSubSysSADEFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssubsyssades/select")
    Page<PSSubSysSADE> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssubsyssades")
    PSSubSysSADE create(@RequestBody PSSubSysSADE et);

    @RequestMapping(method = RequestMethod.POST, value = "/pssubsyssades/batch")
    Boolean createBatch(@RequestBody List<PSSubSysSADE> pssubsyssades);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssubsyssades/{pssubsyssadeid}")
    PSSubSysSADE update(@PathVariable("pssubsyssadeid") String pssubsyssadeid, @RequestBody PSSubSysSADE et);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssubsyssades/batch")
    Boolean updateBatch(@RequestBody List<PSSubSysSADE> pssubsyssades);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssubsyssades/{pssubsyssadeid}")
    Boolean remove(@PathVariable("pssubsyssadeid") String pssubsyssadeid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssubsyssades/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssubsyssades/{pssubsyssadeid}")
    PSSubSysSADE get(@PathVariable("pssubsyssadeid") String pssubsyssadeid);

    @RequestMapping(method = RequestMethod.GET, value = "/pssubsyssades/getbycodename/{pssubsyssadeid}")
    String getByCodeName(@PathVariable("pssubsyssadeid") String codeName);


    @RequestMapping(method = RequestMethod.GET, value = "/pssubsyssades/getdraft")
    PSSubSysSADE getDraft(PSSubSysSADE entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssubsyssades/checkkey")
    Boolean checkKey(@RequestBody PSSubSysSADE et);


    @RequestMapping(method = RequestMethod.POST, value = "/pssubsyssades/save")
    Object saveEntity(@RequestBody PSSubSysSADE et);

    default Boolean save(@RequestBody PSSubSysSADE et) { return saveEntity(et)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssubsyssades/savebatch")
    Boolean saveBatch(@RequestBody List<PSSubSysSADE> pssubsyssades);



    @RequestMapping(method = RequestMethod.POST, value = "/pssubsyssades/searchdefault")
    Page<PSSubSysSADE> searchDefault(@RequestBody PSSubSysSADESearchContext context);


}
