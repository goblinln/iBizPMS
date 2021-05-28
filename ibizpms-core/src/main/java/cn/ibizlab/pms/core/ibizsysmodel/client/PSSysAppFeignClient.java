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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysAppSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSysApp] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSysApp", fallback = PSSysAppFallback.class)
public interface PSSysAppFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssysapps/select")
    Page<PSSysApp> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssysapps")
    PSSysApp create(@RequestBody PSSysApp pssysapp);

    @RequestMapping(method = RequestMethod.POST, value = "/pssysapps/batch")
    Boolean createBatch(@RequestBody List<PSSysApp> pssysapps);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssysapps/{pssysappid}")
    PSSysApp update(@PathVariable("pssysappid") String pssysappid,@RequestBody PSSysApp pssysapp);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssysapps/batch")
    Boolean updateBatch(@RequestBody List<PSSysApp> pssysapps);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysapps/{pssysappid}")
    Boolean remove(@PathVariable("pssysappid") String pssysappid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysapps/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysapps/{pssysappid}")
    PSSysApp get(@PathVariable("pssysappid") String pssysappid);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysapps/getdraft")
    PSSysApp getDraft(PSSysApp entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysapps/checkkey")
    Boolean checkKey(@RequestBody PSSysApp pssysapp);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysapps/save")
    Object saveEntity(@RequestBody PSSysApp pssysapp);

    default Boolean save(@RequestBody PSSysApp pssysapp) { return saveEntity(pssysapp)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssysapps/savebatch")
    Boolean saveBatch(@RequestBody List<PSSysApp> pssysapps);



    @RequestMapping(method = RequestMethod.GET, value = "/pssysapps/fetchbuild")
    Page<PSSysApp> searchBuild(@RequestBody PSSysAppSearchContext context);



    @RequestMapping(method = RequestMethod.GET, value = "/pssysapps/fetchdefault")
    Page<PSSysApp> searchDefault(@RequestBody PSSysAppSearchContext context);



}
