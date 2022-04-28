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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysRunSession;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysRunSessionSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSysRunSession] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSysRunSession", fallback = PSSysRunSessionFallback.class)
public interface PSSysRunSessionFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/pssysrunsessions")
    PSSysRunSession create(@RequestBody PSSysRunSession pssysrunsession);

    @RequestMapping(method = RequestMethod.POST, value = "/pssysrunsessions/batch")
    Boolean createBatch(@RequestBody List<PSSysRunSession> pssysrunsessions);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysrunsessions/{pssysrunsessionid}")
    PSSysRunSession get(@PathVariable("pssysrunsessionid") String pssysrunsessionid);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysrunsessions/{pssysrunsessionid}")
    Boolean remove(@PathVariable("pssysrunsessionid") String pssysrunsessionid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysrunsessions/batch")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssysrunsessions/{pssysrunsessionid}")
    PSSysRunSession update(@PathVariable("pssysrunsessionid") String pssysrunsessionid,@RequestBody PSSysRunSession pssysrunsession);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssysrunsessions/batch")
    Boolean updateBatch(@RequestBody List<PSSysRunSession> pssysrunsessions);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysrunsessions/checkkey")
    Boolean checkKey(@RequestBody PSSysRunSession pssysrunsession);



    @RequestMapping(method = RequestMethod.GET, value = "/pssysrunsessions/fetchdefault")
    Page<PSSysRunSession> searchDefault(@SpringQueryMap PSSysRunSessionSearchContext context);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysrunsessions/getdraft")
    PSSysRunSession getDraft(PSSysRunSession entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysrunsessions/save")
    Object saveEntity(@RequestBody PSSysRunSession pssysrunsession);

    default Boolean save(@RequestBody PSSysRunSession pssysrunsession) { return saveEntity(pssysrunsession)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssysrunsessions/savebatch")
    Boolean saveBatch(@RequestBody List<PSSysRunSession> pssysrunsessions);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysrunsessions/select")
    Page<PSSysRunSession> select();



}
