package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectMonthlyRuntime;

@Slf4j
@Api(tags = {"项目月报"})
@RestController("StandardAPI-projectmonthly")
@RequestMapping("")
public class ProjectMonthlyResource {

    @Autowired
    public IIbizproProjectMonthlyService ibizproprojectmonthlyService;

    @Autowired
    public IbizproProjectMonthlyRuntime ibizproprojectmonthlyRuntime;

    @Autowired
    @Lazy
    public ProjectMonthlyMapping projectmonthlyMapping;

    @PreAuthorize("quickTest('IBIZPRO_PROJECTMONTHLY', 'CREATE')")
    @ApiOperation(value = "新建项目月报", tags = {"项目月报" },  notes = "新建项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmonthlies")
    @Transactional
    public ResponseEntity<ProjectMonthlyDTO> create(@Validated @RequestBody ProjectMonthlyDTO projectmonthlydto) {
        IbizproProjectMonthly domain = projectmonthlyMapping.toDomain(projectmonthlydto);
		ibizproprojectmonthlyService.create(domain);
        if(!ibizproprojectmonthlyRuntime.test(domain.getIbizproprojectmonthlyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectMonthlyDTO dto = projectmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(domain.getIbizproprojectmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PROJECTMONTHLY', #projectmonthly_id, 'NONE')")
    @ApiOperation(value = "获取项目月报", tags = {"项目月报" },  notes = "获取项目月报")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmonthlies/{projectmonthly_id}")
    public ResponseEntity<ProjectMonthlyDTO> get(@PathVariable("projectmonthly_id") String projectmonthly_id) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyService.get(projectmonthly_id);
        ProjectMonthlyDTO dto = projectmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(projectmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproprojectmonthly" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PROJECTMONTHLY', #projectmonthly_id, 'UPDATE')")
    @ApiOperation(value = "更新项目月报", tags = {"项目月报" },  notes = "更新项目月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/projectmonthlies/{projectmonthly_id}")
    @Transactional
    public ResponseEntity<ProjectMonthlyDTO> update(@PathVariable("projectmonthly_id") String projectmonthly_id, @RequestBody ProjectMonthlyDTO projectmonthlydto) {
		IbizproProjectMonthly domain  = projectmonthlyMapping.toDomain(projectmonthlydto);
        domain.setIbizproprojectmonthlyid(projectmonthly_id);
		ibizproprojectmonthlyService.update(domain );
        if(!ibizproprojectmonthlyRuntime.test(projectmonthly_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectMonthlyDTO dto = projectmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(projectmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBIZPRO_PROJECTMONTHLY', #projectmonthly_id, 'NONE')")
    @ApiOperation(value = "手动生成项目月报", tags = {"项目月报" },  notes = "手动生成项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/projectmonthlies/{projectmonthly_id}/autocreate")
    public ResponseEntity<ProjectMonthlyDTO> autoCreate(@PathVariable("projectmonthly_id") String projectmonthly_id, @RequestBody ProjectMonthlyDTO projectmonthlydto) {
        IbizproProjectMonthly domain = projectmonthlyMapping.toDomain(projectmonthlydto);
        domain.setIbizproprojectmonthlyid(projectmonthly_id);
        domain = ibizproprojectmonthlyService.manualCreateMonthly(domain);
        projectmonthlydto = projectmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(domain.getIbizproprojectmonthlyid());
        projectmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectmonthlydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PROJECTMONTHLY', 'CREATE')")
    @ApiOperation(value = "获取项目月报草稿", tags = {"项目月报" },  notes = "获取项目月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projectmonthlies/getdraft")
    public ResponseEntity<ProjectMonthlyDTO> getDraft(ProjectMonthlyDTO dto) {
        IbizproProjectMonthly domain = projectmonthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectmonthlyMapping.toDto(ibizproprojectmonthlyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PROJECTMONTHLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"项目月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projectmonthlies/fetchdefault")
	public ResponseEntity<List<ProjectMonthlyDTO>> fetchdefault(@RequestBody IbizproProjectMonthlySearchContext context) {
        Page<IbizproProjectMonthly> domains = ibizproprojectmonthlyService.searchDefault(context) ;
        List<ProjectMonthlyDTO> list = projectmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目月报报表", tags = {"项目月报"}, notes = "生成项目月报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmonthlies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproProjectMonthlySearchContext context, HttpServletResponse response) {
        try {
            ibizproprojectmonthlyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproprojectmonthlyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproprojectmonthlyRuntime);
        }
    }

    @ApiOperation(value = "打印项目月报", tags = {"项目月报"}, notes = "打印项目月报")
    @RequestMapping(method = RequestMethod.GET, value = "/projectmonthlies/{projectmonthly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("projectmonthly_ids") Set<String> projectmonthly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproprojectmonthlyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproProjectMonthly> domains = new ArrayList<>();
            for (String projectmonthly_id : projectmonthly_ids) {
                domains.add(ibizproprojectmonthlyService.get( projectmonthly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproProjectMonthly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproprojectmonthlyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", projectmonthly_ids, e.getMessage()), Errors.INTERNALERROR, ibizproprojectmonthlyRuntime);
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

}

