package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.ProjectTeamRuntime;

@Slf4j
@Api(tags = {"项目团队" })
@RestController("WebApi-projectteam")
@RequestMapping("")
public class ProjectTeamResource {

    @Autowired
    public IProjectTeamService projectteamService;

    @Autowired
    public ProjectTeamRuntime projectteamRuntime;

    @Autowired
    @Lazy
    public ProjectTeamMapping projectteamMapping;

    @ApiOperation(value = "获取成员角色", tags = {"项目团队" },  notes = "获取成员角色")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectteams/{projectteam_id}/getuserrole")
    public ResponseEntity<ProjectTeamDTO> getUserRole(@PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setId(projectteam_id);
        domain = projectteamService.getUserRole(domain);
        projectteamdto = projectteamMapping.toDto(domain);
        Map<String,Integer> opprivs = projectteamRuntime.getOPPrivs(domain.getId());
        projectteamdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectteams/{projectteam_id}/{action}")
    public ResponseEntity<ProjectTeamDTO> dynamicCall(@PathVariable("projectteam_id") Long projectteam_id , @PathVariable("action") String action , @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamService.dynamicCall(projectteam_id, action, projectteamMapping.toDomain(projectteamdto));
        projectteamdto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }
    @ApiOperation(value = "根据项目获取成员角色项目团队", tags = {"项目团队" },  notes = "根据项目项目团队")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectteams/{projectteam_id}/getuserrole")
    public ResponseEntity<ProjectTeamDTO> getUserRoleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectteam_id") Long projectteam_id, @RequestBody ProjectTeamDTO projectteamdto) {
        ProjectTeam domain = projectteamMapping.toDomain(projectteamdto);
        domain.setRoot(project_id);
        domain.setId(projectteam_id);
        domain = projectteamService.getUserRole(domain) ;
        projectteamdto = projectteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectteamdto);
    }

}

