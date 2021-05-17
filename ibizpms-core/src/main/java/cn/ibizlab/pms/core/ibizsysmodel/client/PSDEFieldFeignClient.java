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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDEFieldSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[PSDEField] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi:ibizpssysmodelapi-sysmodelapi}", contextId = "PSDEField", fallback = PSDEFieldFallback.class)
public interface PSDEFieldFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/psdefields/select")
    Page<PSDEField> select();


    @RequestMapping(method = RequestMethod.POST, value = "/psdefields")
    PSDEField create(@RequestBody PSDEField psdefield);

    @RequestMapping(method = RequestMethod.POST, value = "/psdefields/batch")
    Boolean createBatch(@RequestBody List<PSDEField> psdefields);


    @RequestMapping(method = RequestMethod.PUT, value = "/psdefields/{psdefieldid}")
    PSDEField update(@PathVariable("psdefieldid") String psdefieldid,@RequestBody PSDEField psdefield);

    @RequestMapping(method = RequestMethod.PUT, value = "/psdefields/batch")
    Boolean updateBatch(@RequestBody List<PSDEField> psdefields);


    @RequestMapping(method = RequestMethod.DELETE, value = "/psdefields/{psdefieldid}")
    Boolean remove(@PathVariable("psdefieldid") String psdefieldid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/psdefields/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/psdefields/{psdefieldid}")
    PSDEField get(@PathVariable("psdefieldid") String psdefieldid);


    @RequestMapping(method = RequestMethod.GET, value = "/psdefields/getdraft")
    PSDEField getDraft(PSDEField entity);


    @RequestMapping(method = RequestMethod.POST, value = "/psdefields/checkkey")
    Boolean checkKey(@RequestBody PSDEField psdefield);


    @RequestMapping(method = RequestMethod.POST, value = "/psdefields/save")
    Object saveEntity(@RequestBody PSDEField psdefield);

    default Boolean save(@RequestBody PSDEField psdefield) { return saveEntity(psdefield)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/psdefields/savebatch")
    Boolean saveBatch(@RequestBody List<PSDEField> psdefields);



    @RequestMapping(method = RequestMethod.POST, value = "/psdefields/searchdefault")
    Page<PSDEField> searchDefault(@RequestBody PSDEFieldSearchContext context);



}
