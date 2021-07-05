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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[IBIZProPlugin] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.pmspro-pluginserviceapi:pmspro-pluginserviceapi}", contextId = "IBIZProPlugin", fallback = IBIZProPluginFallback.class)
public interface IBIZProPluginFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ibizproplugins/select")
    Page<IBIZProPlugin> select();


    @RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins")
    IBIZProPlugin create(@RequestBody IBIZProPlugin ibizproplugin);

    @RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/batch")
    Boolean createBatch(@RequestBody List<IBIZProPlugin> ibizproplugins);


    @RequestMapping(method = RequestMethod.PUT, value = "/ibizproplugins/{ibizpropluginid}")
    IBIZProPlugin update(@PathVariable("ibizpropluginid") String ibizpropluginid,@RequestBody IBIZProPlugin ibizproplugin);

    @RequestMapping(method = RequestMethod.PUT, value = "/ibizproplugins/batch")
    Boolean updateBatch(@RequestBody List<IBIZProPlugin> ibizproplugins);


    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizproplugins/{ibizpropluginid}")
    Boolean remove(@PathVariable("ibizpropluginid") String ibizpropluginid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizproplugins/batch")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizproplugins/{ibizpropluginid}")
    IBIZProPlugin get(@PathVariable("ibizpropluginid") String ibizpropluginid);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizproplugins/getdraft")
    IBIZProPlugin getDraft(IBIZProPlugin entity);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/checkkey")
    Boolean checkKey(@RequestBody IBIZProPlugin ibizproplugin);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/save")
    Object saveEntity(@RequestBody IBIZProPlugin ibizproplugin);

    default Boolean save(@RequestBody IBIZProPlugin ibizproplugin) { return saveEntity(ibizproplugin)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/savebatch")
    Boolean saveBatch(@RequestBody List<IBIZProPlugin> ibizproplugins);



    @RequestMapping(method = RequestMethod.GET, value = "/ibizproplugins/fetchdefault")
    Page<IBIZProPlugin> searchDefault(@SpringQueryMap IBIZProPluginSearchContext context);



}
