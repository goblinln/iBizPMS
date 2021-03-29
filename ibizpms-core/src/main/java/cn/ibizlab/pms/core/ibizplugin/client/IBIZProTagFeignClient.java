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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProTag;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProTagSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[IBIZProTag] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.pmspro-pluginserviceapi:pmspro-pluginserviceapi}", contextId = "IBIZProTag", fallback = IBIZProTagFallback.class)
public interface IBIZProTagFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/select")
    Page<IBIZProTag> select();


    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags")
    IBIZProTag create(@RequestBody IBIZProTag et);

    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/batch")
    Boolean createBatch(@RequestBody List<IBIZProTag> ibizprotags);


    @RequestMapping(method = RequestMethod.PUT, value = "/ibizprotags/{id}")
    IBIZProTag update(@PathVariable("id") String id, @RequestBody IBIZProTag et);

    @RequestMapping(method = RequestMethod.PUT, value = "/ibizprotags/batch")
    Boolean updateBatch(@RequestBody List<IBIZProTag> ibizprotags);


    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizprotags/{id}")
    Boolean remove(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizprotags/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/{id}")
    IBIZProTag get(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/getbycodename/{id}")
    String getByCodeName(@PathVariable("id") String codeName);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/getdraft")
    IBIZProTag getDraft(IBIZProTag entity);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/checkkey")
    Boolean checkKey(@RequestBody IBIZProTag et);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/save")
    Boolean save(@RequestBody IBIZProTag et);

    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/savebatch")
    Boolean saveBatch(@RequestBody List<IBIZProTag> ibizprotags);



    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/searchdefault")
    Page<IBIZProTag> searchDefault(@RequestBody IBIZProTagSearchContext context);


}
