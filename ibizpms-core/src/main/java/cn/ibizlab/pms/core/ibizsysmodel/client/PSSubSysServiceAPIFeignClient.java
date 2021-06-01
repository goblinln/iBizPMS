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
import org.springframework.cloud.openfeign.SpringQueryMap;
import com.alibaba.fastjson.JSONObject;
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysServiceAPISearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSubSysServiceAPI] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSubSysServiceAPI", fallback = PSSubSysServiceAPIFallback.class)
public interface PSSubSysServiceAPIFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssubsysserviceapis/select")
    Page<PSSubSysServiceAPI> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssubsysserviceapis")
    PSSubSysServiceAPI create(@RequestBody PSSubSysServiceAPI pssubsysserviceapi);

    @RequestMapping(method = RequestMethod.POST, value = "/pssubsysserviceapis/batch")
    Boolean createBatch(@RequestBody List<PSSubSysServiceAPI> pssubsysserviceapis);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssubsysserviceapis/{pssubsysserviceapiid}")
    PSSubSysServiceAPI update(@PathVariable("pssubsysserviceapiid") String pssubsysserviceapiid,@RequestBody PSSubSysServiceAPI pssubsysserviceapi);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssubsysserviceapis/batch")
    Boolean updateBatch(@RequestBody List<PSSubSysServiceAPI> pssubsysserviceapis);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssubsysserviceapis/{pssubsysserviceapiid}")
    Boolean remove(@PathVariable("pssubsysserviceapiid") String pssubsysserviceapiid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssubsysserviceapis/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssubsysserviceapis/{pssubsysserviceapiid}")
    PSSubSysServiceAPI get(@PathVariable("pssubsysserviceapiid") String pssubsysserviceapiid);


    @RequestMapping(method = RequestMethod.GET, value = "/pssubsysserviceapis/getdraft")
    PSSubSysServiceAPI getDraft(PSSubSysServiceAPI entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssubsysserviceapis/checkkey")
    Boolean checkKey(@RequestBody PSSubSysServiceAPI pssubsysserviceapi);


    @RequestMapping(method = RequestMethod.POST, value = "/pssubsysserviceapis/save")
    Object saveEntity(@RequestBody PSSubSysServiceAPI pssubsysserviceapi);

    default Boolean save(@RequestBody PSSubSysServiceAPI pssubsysserviceapi) { return saveEntity(pssubsysserviceapi)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssubsysserviceapis/savebatch")
    Boolean saveBatch(@RequestBody List<PSSubSysServiceAPI> pssubsysserviceapis);



    @RequestMapping(method = RequestMethod.GET, value = "/pssubsysserviceapis/fetchdefault")
    Page<PSSubSysServiceAPI> fetchDefault(@SpringQueryMapPSSubSysServiceAPISearchContext context);



}
