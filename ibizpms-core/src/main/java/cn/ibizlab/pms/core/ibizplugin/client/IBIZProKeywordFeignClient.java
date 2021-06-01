package cn.ibizlab.pms.core.ibizplugin.client;

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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[IBIZProKeyword] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.pmspro-pluginserviceapi:pmspro-pluginserviceapi}", contextId = "IBIZProKeyword", fallback = IBIZProKeywordFallback.class)
public interface IBIZProKeywordFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/select")
    Page<IBIZProKeyword> select();


    @RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords")
    IBIZProKeyword create(@RequestBody IBIZProKeyword ibizprokeyword);

    @RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/batch")
    Boolean createBatch(@RequestBody List<IBIZProKeyword> ibizprokeywords);


    @RequestMapping(method = RequestMethod.PUT, value = "/ibizprokeywords/{id}")
    IBIZProKeyword update(@PathVariable("id") String id,@RequestBody IBIZProKeyword ibizprokeyword);

    @RequestMapping(method = RequestMethod.PUT, value = "/ibizprokeywords/batch")
    Boolean updateBatch(@RequestBody List<IBIZProKeyword> ibizprokeywords);


    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizprokeywords/{id}")
    Boolean remove(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizprokeywords/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/{id}")
    IBIZProKeyword get(@PathVariable("id") String id);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/getdraft")
    IBIZProKeyword getDraft(IBIZProKeyword entity);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/checkkey")
    Boolean checkKey(@RequestBody IBIZProKeyword ibizprokeyword);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/save")
    Object saveEntity(@RequestBody IBIZProKeyword ibizprokeyword);

    default Boolean save(@RequestBody IBIZProKeyword ibizprokeyword) { return saveEntity(ibizprokeyword)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/savebatch")
    Boolean saveBatch(@RequestBody List<IBIZProKeyword> ibizprokeywords);



    @RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/fetchdefault")
    Page<IBIZProKeyword> fetchDefault(@SpringQueryMap IBIZProKeywordSearchContext context);



}
