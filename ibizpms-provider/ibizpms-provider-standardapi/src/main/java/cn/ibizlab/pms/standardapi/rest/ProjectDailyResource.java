package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectDailyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectDailyRuntime;

@Slf4j
@Api(tags = {"项目日报" })
@RestController("StandardAPI-projectdaily")
@RequestMapping("")
public class ProjectDailyResource {

    @Autowired
    public IIbizproProjectDailyService ibizproprojectdailyService;

    @Autowired
    public IbizproProjectDailyRuntime ibizproprojectdailyRuntime;

    @Autowired
    @Lazy
    public ProjectDailyMapping projectdailyMapping;

    @PreAuthorize("quickTest('IBIZPRO_PROJECTDAILY', 'DENY')")
    @ApiOperation(value = "汇总项目日报", tags = {"项目日报" },  notes = "汇总项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/projectdailies/{projectdaily_id}/sumprojectdaily")
    public ResponseEntity<ProjectDailyDTO> sumProjectDaily(@PathVariable("projectdaily_id") String projectdaily_id, @RequestBody ProjectDailyDTO projectdailydto) {
        IbizproProjectDaily domain = projectdailyMapping.toDomain(projectdailydto);
        domain.setIbizproprojectdailyid(projectdaily_id);
        domain = ibizproprojectdailyService.sumProjectDaily(domain);
        projectdailydto = projectdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(domain.getIbizproprojectdailyid());
        projectdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdailydto);
    }


    @PreAuthorize("test('IBIZPRO_PROJECTDAILY', #projectdaily_id, 'NONE')")
    @ApiOperation(value = "获取项目日报", tags = {"项目日报" },  notes = "获取项目日报")
	@RequestMapping(method = RequestMethod.GET, value = "/projectdailies/{projectdaily_id}")
    public ResponseEntity<ProjectDailyDTO> get(@PathVariable("projectdaily_id") String projectdaily_id) {
        IbizproProjectDaily domain = ibizproprojectdailyService.get(projectdaily_id);
        ProjectDailyDTO dto = projectdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(projectdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproprojectdaily" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PROJECTDAILY', #projectdaily_id, 'UPDATE')")
    @ApiOperation(value = "更新项目日报", tags = {"项目日报" },  notes = "更新项目日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectdailies/{projectdaily_id}")
    @Transactional
    public ResponseEntity<ProjectDailyDTO> update(@PathVariable("projectdaily_id") String projectdaily_id, @RequestBody ProjectDailyDTO projectdailydto) {
		IbizproProjectDaily domain  = projectdailyMapping.toDomain(projectdailydto);
        domain.setIbizproprojectdailyid(projectdaily_id);
		ibizproprojectdailyService.update(domain );
        if(!ibizproprojectdailyRuntime.test(projectdaily_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectDailyDTO dto = projectdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(projectdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PROJECTDAILY', 'CREATE')")
    @ApiOperation(value = "获取项目日报草稿", tags = {"项目日报" },  notes = "获取项目日报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectdailies/getdraft")
    public ResponseEntity<ProjectDailyDTO> getDraft(ProjectDailyDTO dto) {
        IbizproProjectDaily domain = projectdailyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectdailyMapping.toDto(ibizproprojectdailyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PROJECTDAILY', 'CREATE')")
    @ApiOperation(value = "新建项目日报", tags = {"项目日报" },  notes = "新建项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/projectdailies")
    @Transactional
    public ResponseEntity<ProjectDailyDTO> create(@Validated @RequestBody ProjectDailyDTO projectdailydto) {
        IbizproProjectDaily domain = projectdailyMapping.toDomain(projectdailydto);
		ibizproprojectdailyService.create(domain);
        if(!ibizproprojectdailyRuntime.test(domain.getIbizproprojectdailyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectDailyDTO dto = projectdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(domain.getIbizproprojectdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBIZPRO_PROJECTDAILY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"项目日报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projectdailies/fetchdefault")
	public ResponseEntity<List<ProjectDailyDTO>> fetchdefault(@RequestBody IbizproProjectDailySearchContext context) {
        Page<IbizproProjectDaily> domains = ibizproprojectdailyService.searchDefault(context) ;
        List<ProjectDailyDTO> list = projectdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projectdailies/{projectdaily_id}/{action}")
    public ResponseEntity<ProjectDailyDTO> dynamicCall(@PathVariable("projectdaily_id") String projectdaily_id , @PathVariable("action") String action , @RequestBody ProjectDailyDTO projectdailydto) {
        IbizproProjectDaily domain = ibizproprojectdailyService.dynamicCall(projectdaily_id, action, projectdailyMapping.toDomain(projectdailydto));
        projectdailydto = projectdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdailydto);
    }
}

