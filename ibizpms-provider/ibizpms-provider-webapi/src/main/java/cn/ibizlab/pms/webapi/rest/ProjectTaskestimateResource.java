package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTaskestimate;
import cn.ibizlab.pms.core.ibizpro.service.IProjectTaskestimateService;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTaskestimateSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.ProjectTaskestimateRuntime;

@Slf4j
@Api(tags = {"项目工时统计"})
@RestController("WebApi-projecttaskestimate")
@RequestMapping("")
public class ProjectTaskestimateResource {

    @Autowired
    public IProjectTaskestimateService projecttaskestimateService;

    @Autowired
    public ProjectTaskestimateRuntime projecttaskestimateRuntime;

    @Autowired
    @Lazy
    public ProjectTaskestimateMapping projecttaskestimateMapping;

    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'CREATE')")
    @ApiOperation(value = "新建项目工时统计", tags = {"项目工时统计" },  notes = "新建项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates")
    @Transactional
    public ResponseEntity<ProjectTaskestimateDTO> create(@Validated @RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        ProjectTaskestimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
		projecttaskestimateService.create(domain);
        if(!projecttaskestimateRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = projecttaskestimateRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('PROJECTTASKESTIMATE', #projecttaskestimate_id, 'READ')")
    @ApiOperation(value = "获取项目工时统计", tags = {"项目工时统计" },  notes = "获取项目工时统计")
	@RequestMapping(method = RequestMethod.GET, value = "/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<ProjectTaskestimateDTO> get(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id) {
        ProjectTaskestimate domain = projecttaskestimateService.get(projecttaskestimate_id);
        ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = projecttaskestimateRuntime.getOPPrivs(projecttaskestimate_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('PROJECTTASKESTIMATE', #projecttaskestimate_id, 'DELETE')")
    @ApiOperation(value = "删除项目工时统计", tags = {"项目工时统计" },  notes = "删除项目工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projecttaskestimates/{projecttaskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateService.remove(projecttaskestimate_id));
    }

    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'DELETE')")
    @ApiOperation(value = "批量删除项目工时统计", tags = {"项目工时统计" },  notes = "批量删除项目工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projecttaskestimates/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        projecttaskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('PROJECTTASKESTIMATE', #projecttaskestimate_id, 'UPDATE')")
    @ApiOperation(value = "更新项目工时统计", tags = {"项目工时统计" },  notes = "更新项目工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/projecttaskestimates/{projecttaskestimate_id}")
    @Transactional
    public ResponseEntity<ProjectTaskestimateDTO> update(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id, @RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
		ProjectTaskestimate domain  = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        domain.setId(projecttaskestimate_id);
		projecttaskestimateService.update(domain );
        if(!projecttaskestimateRuntime.test(projecttaskestimate_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = projecttaskestimateRuntime.getOPPrivs(projecttaskestimate_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'CREATE')")
    @ApiOperation(value = "检查项目工时统计", tags = {"项目工时统计" },  notes = "检查项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateService.checkKey(projecttaskestimateMapping.toDomain(projecttaskestimatedto)));
    }

    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'CREATE')")
    @ApiOperation(value = "获取项目工时统计草稿", tags = {"项目工时统计" },  notes = "获取项目工时统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projecttaskestimates/getdraft")
    public ResponseEntity<ProjectTaskestimateDTO> getDraft(ProjectTaskestimateDTO dto) {
        ProjectTaskestimate domain = projecttaskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimateMapping.toDto(projecttaskestimateService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'DENY')")
    @ApiOperation(value = "保存项目工时统计", tags = {"项目工时统计" },  notes = "保存项目工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/save")
    public ResponseEntity<ProjectTaskestimateDTO> save(@RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        ProjectTaskestimate domain = projecttaskestimateMapping.toDomain(projecttaskestimatedto);
        projecttaskestimateService.save(domain);
        ProjectTaskestimateDTO dto = projecttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = projecttaskestimateRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'READ')")
	@ApiOperation(value = "获取用户月工时详情", tags = {"项目工时统计" } ,notes = "获取用户月工时详情")
    @RequestMapping(method= RequestMethod.POST , value="/projecttaskestimates/fetchaccountdetail")
	public ResponseEntity<List<ProjectTaskestimateDTO>> fetchaccountdetail(@RequestBody ProjectTaskestimateSearchContext context) {
        projecttaskestimateRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchAccountDetail(context) ;
        List<ProjectTaskestimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('PROJECTTASKESTIMATE', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"项目工时统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projecttaskestimates/fetchdefault")
	public ResponseEntity<List<ProjectTaskestimateDTO>> fetchdefault(@RequestBody ProjectTaskestimateSearchContext context) {
        projecttaskestimateRuntime.addAuthorityConditions(context,"READ");
        Page<ProjectTaskestimate> domains = projecttaskestimateService.searchDefault(context) ;
        List<ProjectTaskestimateDTO> list = projecttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目工时统计报表", tags = {"项目工时统计"}, notes = "生成项目工时统计报表")
    @RequestMapping(method = RequestMethod.GET, value = "/projecttaskestimates/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProjectTaskestimateSearchContext context, HttpServletResponse response) {
        try {
            projecttaskestimateRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projecttaskestimateRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, projecttaskestimateRuntime);
        }
    }

    @ApiOperation(value = "打印项目工时统计", tags = {"项目工时统计"}, notes = "打印项目工时统计")
    @RequestMapping(method = RequestMethod.GET, value = "/projecttaskestimates/{projecttaskestimate_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("projecttaskestimate_ids") Set<String> projecttaskestimate_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = projecttaskestimateRuntime.getDEPrintRuntime(print_id);
        try {
            List<ProjectTaskestimate> domains = new ArrayList<>();
            for (String projecttaskestimate_id : projecttaskestimate_ids) {
                domains.add(projecttaskestimateService.get( projecttaskestimate_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new ProjectTaskestimate[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projecttaskestimateRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", projecttaskestimate_ids, e.getMessage()), Errors.INTERNALERROR, projecttaskestimateRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/projecttaskestimates/{projecttaskestimate_id}/{action}")
    public ResponseEntity<ProjectTaskestimateDTO> dynamicCall(@PathVariable("projecttaskestimate_id") String projecttaskestimate_id , @PathVariable("action") String action , @RequestBody ProjectTaskestimateDTO projecttaskestimatedto) {
        ProjectTaskestimate domain = projecttaskestimateService.dynamicCall(projecttaskestimate_id, action, projecttaskestimateMapping.toDomain(projecttaskestimatedto));
        projecttaskestimatedto = projecttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttaskestimatedto);
    }
}

