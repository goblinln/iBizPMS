package cn.ibizlab.pms.core.ou.client;

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
import cn.ibizlab.pms.core.ou.domain.SysTeam;
import cn.ibizlab.pms.core.ou.filter.SysTeamSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[SysTeam] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.ibzou-api:ibzou-api}", contextId = "SysTeam", fallback = SysTeamFallback.class)
public interface SysTeamFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/systeams/select")
    Page<SysTeam> select();


    @RequestMapping(method = RequestMethod.POST, value = "/systeams")
    SysTeam create(@RequestBody SysTeam systeam);

    @RequestMapping(method = RequestMethod.POST, value = "/systeams/batch")
    Boolean createBatch(@RequestBody List<SysTeam> systeams);


    @RequestMapping(method = RequestMethod.PUT, value = "/systeams/{teamid}")
    SysTeam update(@PathVariable("teamid") String teamid,@RequestBody SysTeam systeam);

    @RequestMapping(method = RequestMethod.PUT, value = "/systeams/batch")
    Boolean updateBatch(@RequestBody List<SysTeam> systeams);


    @RequestMapping(method = RequestMethod.DELETE, value = "/systeams/{teamid}")
    Boolean remove(@PathVariable("teamid") String teamid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/systeams/batch")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/systeams/{teamid}")
    SysTeam get(@PathVariable("teamid") String teamid);


    @RequestMapping(method = RequestMethod.GET, value = "/systeams/getdraft")
    SysTeam getDraft(SysTeam entity);


    @RequestMapping(method = RequestMethod.POST, value = "/systeams/checkkey")
    Boolean checkKey(@RequestBody SysTeam systeam);


    @RequestMapping(method = RequestMethod.POST, value = "/systeams/save")
    Object saveEntity(@RequestBody SysTeam systeam);

    default Boolean save(@RequestBody SysTeam systeam) { return saveEntity(systeam)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/systeams/savebatch")
    Boolean saveBatch(@RequestBody List<SysTeam> systeams);



    @RequestMapping(method = RequestMethod.POST, value = "/systeams/fetchdefault")
    Page<SysTeam> searchDefault(@RequestBody SysTeamSearchContext context);



}
