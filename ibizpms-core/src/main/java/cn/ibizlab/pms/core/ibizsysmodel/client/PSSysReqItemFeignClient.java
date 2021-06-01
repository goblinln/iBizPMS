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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqItem;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqItemSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSysReqItem] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSysReqItem", fallback = PSSysReqItemFallback.class)
public interface PSSysReqItemFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqitems/select")
    Page<PSSysReqItem> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqitems")
    PSSysReqItem create(@RequestBody PSSysReqItem pssysreqitem);

    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqitems/batch")
    Boolean createBatch(@RequestBody List<PSSysReqItem> pssysreqitems);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssysreqitems/{pssysreqitemid}")
    PSSysReqItem update(@PathVariable("pssysreqitemid") String pssysreqitemid,@RequestBody PSSysReqItem pssysreqitem);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssysreqitems/batch")
    Boolean updateBatch(@RequestBody List<PSSysReqItem> pssysreqitems);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysreqitems/{pssysreqitemid}")
    Boolean remove(@PathVariable("pssysreqitemid") String pssysreqitemid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssysreqitems/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqitems/{pssysreqitemid}")
    PSSysReqItem get(@PathVariable("pssysreqitemid") String pssysreqitemid);


    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqitems/getdraft")
    PSSysReqItem getDraft(PSSysReqItem entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqitems/checkkey")
    Boolean checkKey(@RequestBody PSSysReqItem pssysreqitem);


    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqitems/save")
    Object saveEntity(@RequestBody PSSysReqItem pssysreqitem);

    default Boolean save(@RequestBody PSSysReqItem pssysreqitem) { return saveEntity(pssysreqitem)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssysreqitems/savebatch")
    Boolean saveBatch(@RequestBody List<PSSysReqItem> pssysreqitems);



    @RequestMapping(method = RequestMethod.GET, value = "/pssysreqitems/fetchdefault")
    Page<PSSysReqItem> fetchDefault(@SpringQueryMapPSSysReqItemSearchContext context);



}
