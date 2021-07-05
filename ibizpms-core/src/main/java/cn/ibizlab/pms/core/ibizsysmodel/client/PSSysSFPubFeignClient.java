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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysSFPub;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysSFPubSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSSysSFPub] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSSysSFPub", fallback = PSSysSFPubFallback.class)
public interface PSSysSFPubFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pssyssfpubs/select")
    Page<PSSysSFPub> select();


    @RequestMapping(method = RequestMethod.POST, value = "/pssyssfpubs")
    PSSysSFPub create(@RequestBody PSSysSFPub pssyssfpub);

    @RequestMapping(method = RequestMethod.POST, value = "/pssyssfpubs/batch")
    Boolean createBatch(@RequestBody List<PSSysSFPub> pssyssfpubs);


    @RequestMapping(method = RequestMethod.PUT, value = "/pssyssfpubs/{pssyssfpubid}")
    PSSysSFPub update(@PathVariable("pssyssfpubid") String pssyssfpubid,@RequestBody PSSysSFPub pssyssfpub);

    @RequestMapping(method = RequestMethod.PUT, value = "/pssyssfpubs/batch")
    Boolean updateBatch(@RequestBody List<PSSysSFPub> pssyssfpubs);


    @RequestMapping(method = RequestMethod.DELETE, value = "/pssyssfpubs/{pssyssfpubid}")
    Boolean remove(@PathVariable("pssyssfpubid") String pssyssfpubid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/pssyssfpubs/batch")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/pssyssfpubs/{pssyssfpubid}")
    PSSysSFPub get(@PathVariable("pssyssfpubid") String pssyssfpubid);


    @RequestMapping(method = RequestMethod.GET, value = "/pssyssfpubs/getdraft")
    PSSysSFPub getDraft(PSSysSFPub entity);


    @RequestMapping(method = RequestMethod.POST, value = "/pssyssfpubs/checkkey")
    Boolean checkKey(@RequestBody PSSysSFPub pssyssfpub);


    @RequestMapping(method = RequestMethod.POST, value = "/pssyssfpubs/save")
    Object saveEntity(@RequestBody PSSysSFPub pssyssfpub);

    default Boolean save(@RequestBody PSSysSFPub pssyssfpub) { return saveEntity(pssyssfpub)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/pssyssfpubs/savebatch")
    Boolean saveBatch(@RequestBody List<PSSysSFPub> pssyssfpubs);



    @RequestMapping(method = RequestMethod.GET, value = "/pssyssfpubs/fetchbuild")
    Page<PSSysSFPub> searchBuild(@SpringQueryMap PSSysSFPubSearchContext context);



    @RequestMapping(method = RequestMethod.GET, value = "/pssyssfpubs/fetchdefault")
    Page<PSSysSFPub> searchDefault(@SpringQueryMap PSSysSFPubSearchContext context);



}
