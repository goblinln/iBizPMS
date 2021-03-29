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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysServiceAPISearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSysServiceAPI] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSysServiceAPI", fallback = PSSysServiceAPIFallback.class)
public interface PSSysServiceAPIFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssysserviceapis/select")
    Page<PSSysServiceAPI> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssysserviceapis")
    PSSysServiceAPI create(@RequestBody PSSysServiceAPI et);

    @RequestMapping(method = RequestMethod.POST, value = "/pssysserviceapis/batch")
    Boolean createBatch(@RequestBody List<PSSysServiceAPI> pssysserviceapis);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssysserviceapis/{pssysserviceapiid}")
    PSSysServiceAPI update(@PathVariable("pssysserviceapiid") String pssysserviceapiid, @RequestBody PSSysServiceAPI et);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssysserviceapis/batch")
    Boolean updateBatch(@RequestBody List<PSSysServiceAPI> pssysserviceapis);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysserviceapis/{pssysserviceapiid}")
    Boolean remove(@PathVariable("pssysserviceapiid") String pssysserviceapiid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysserviceapis/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysserviceapis/{pssysserviceapiid}")
    PSSysServiceAPI get(@PathVariable("pssysserviceapiid") String pssysserviceapiid);

    @RequestMapping(method = RequestMethod.GET, value = "/pssysserviceapis/getbycodename/{pssysserviceapiid}")
    String getByCodeName(@PathVariable("pssysserviceapiid") String codeName);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysserviceapis/getdraft")
    PSSysServiceAPI getDraft(PSSysServiceAPI entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysserviceapis/checkkey")
    Boolean checkKey(@RequestBody PSSysServiceAPI et);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysserviceapis/save")
    Boolean save(@RequestBody PSSysServiceAPI et);

    @RequestMapping(method = RequestMethod.POST, value = "/pssysserviceapis/savebatch")
    Boolean saveBatch(@RequestBody List<PSSysServiceAPI> pssysserviceapis);



    @RequestMapping(method = RequestMethod.POST, value = "/pssysserviceapis/searchdefault")
    Page<PSSysServiceAPI> searchDefault(@RequestBody PSSysServiceAPISearchContext context);


}
