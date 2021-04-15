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
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[SysEmployee] 服务对象接口
 */
@FeignClient(value = "${ibiz.ref.service.ibzou-api:ibzou-api}", contextId = "SysEmployee", fallback = SysEmployeeFallback.class)
public interface SysEmployeeFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/sysemployees/select")
    Page<SysEmployee> select();


    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees")
    SysEmployee create(@RequestBody SysEmployee sysemployee);

    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/batch")
    Boolean createBatch(@RequestBody List<SysEmployee> sysemployees);


    @RequestMapping(method = RequestMethod.PUT, value = "/sysemployees/{userid}")
    SysEmployee update(@PathVariable("userid") String userid,@RequestBody SysEmployee sysemployee);

    @RequestMapping(method = RequestMethod.PUT, value = "/sysemployees/batch")
    Boolean updateBatch(@RequestBody List<SysEmployee> sysemployees);


    @RequestMapping(method = RequestMethod.DELETE, value = "/sysemployees/{userid}")
    Boolean remove(@PathVariable("userid") String userid);

    @RequestMapping(method = RequestMethod.DELETE, value = "/sysemployees/batch}")
    Boolean removeBatch(@RequestBody Collection<String> idList);


    @RequestMapping(method = RequestMethod.GET, value = "/sysemployees/{userid}")
    SysEmployee get(@PathVariable("userid") String userid);


    @RequestMapping(method = RequestMethod.GET, value = "/sysemployees/getdraft")
    SysEmployee getDraft(SysEmployee entity);


    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/checkkey")
    Boolean checkKey(@RequestBody SysEmployee sysemployee);


    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/save")
    Object saveEntity(@RequestBody SysEmployee sysemployee);

    default Boolean save(@RequestBody SysEmployee sysemployee) { return saveEntity(sysemployee)!=null; }

    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/savebatch")
    Boolean saveBatch(@RequestBody List<SysEmployee> sysemployees);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchbuguser")
    Page<SysEmployee> searchBugUser(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchcontactlist")
    Page<SysEmployee> searchContActList(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchdefault")
    Page<SysEmployee> searchDefault(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchproductteamm")
    Page<SysEmployee> searchProductTeamM(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchprojectteamm")
    Page<SysEmployee> searchProjectTeamM(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchprojectteammproduct")
    Page<SysEmployee> searchProjectTeamMProduct(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchprojectteamtaskusertemp")
    Page<SysEmployee> searchProjectTeamTaskUserTemp(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchprojectteamuser")
    Page<SysEmployee> searchProjectTeamUser(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchprojectteamusertask")
    Page<SysEmployee> searchProjectTeamUserTask(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchprojectteampk")
    Page<SysEmployee> searchProjectteamPk(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchstoryproductteampk")
    Page<SysEmployee> searchStoryProductTeamPK(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchtaskmteam")
    Page<SysEmployee> searchTaskMTeam(@RequestBody SysEmployeeSearchContext context);



    @RequestMapping(method = RequestMethod.POST, value = "/sysemployees/searchtaskteam")
    Page<SysEmployee> searchTaskTeam(@RequestBody SysEmployeeSearchContext context);


}
