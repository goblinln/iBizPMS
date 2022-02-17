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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProMessageSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[IBIZProMessage] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.pmspro-pluginserviceapi:pmspro-pluginserviceapi}", contextId = "IBIZProMessage", fallback = IBIZProMessageFallback.class)
public interface IBIZProMessageFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages")
    IBIZProMessage create(@RequestBody IBIZProMessage et);

    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/batch")
    Boolean createBatch(@RequestBody List<IBIZProMessage> ibizpromessages);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizpromessages/{ibizpromessageid}")
    IBIZProMessage get(@PathVariable("ibizpromessageid") String ibizpromessageid);

    @RequestMapping(method = RequestMethod.GET, value = "/ibizpromessages/getbycodename/{ibizpromessageid}")
    String getByCodeName(@PathVariable("ibizpromessageid") String codeName);


    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizpromessages/{ibizpromessageid}")
    Boolean remove(@PathVariable("ibizpromessageid") String ibizpromessageid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/ibizpromessages/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.PUT, value = "/ibizpromessages/{ibizpromessageid}")
    IBIZProMessage update(@PathVariable("ibizpromessageid") String ibizpromessageid, @RequestBody IBIZProMessage et);

    @RequestMapping(method = RequestMethod.PUT, value = "/ibizpromessages/batch")
    Boolean updateBatch(@RequestBody List<IBIZProMessage> ibizpromessages);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/checkkey")
    Boolean checkKey(@RequestBody IBIZProMessage et);



    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/searchdefault")
    Page<IBIZProMessage> searchDefault(@RequestBody IBIZProMessageSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/searchuserallmessages")
    Page<IBIZProMessage> searchUserAllMessages(@RequestBody IBIZProMessageSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/searchuserunreadmessages")
    Page<IBIZProMessage> searchUserUnreadMessages(@RequestBody IBIZProMessageSearchContext context);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizpromessages/getdraft")
    IBIZProMessage getDraft(IBIZProMessage entity);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessageid}/markdone")
    IBIZProMessage markDone(@PathVariable("ibizpromessageid") String ibizpromessageid, @RequestBody IBIZProMessage et);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessageid}/markread")
    IBIZProMessage markRead(@PathVariable("ibizpromessageid") String ibizpromessageid, @RequestBody IBIZProMessage et);


    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/save")
    Object saveEntity(@RequestBody IBIZProMessage et);

    default Boolean save(@RequestBody IBIZProMessage et) { return saveEntity(et)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/savebatch")
    Boolean saveBatch(@RequestBody List<IBIZProMessage> ibizpromessages);


    @RequestMapping(method = RequestMethod.GET, value = "/ibizpromessages/select")
    Page<IBIZProMessage> select();


    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessageid}/send")
    IBIZProMessage send(@PathVariable("ibizpromessageid") String ibizpromessageid, @RequestBody IBIZProMessage et);


}
