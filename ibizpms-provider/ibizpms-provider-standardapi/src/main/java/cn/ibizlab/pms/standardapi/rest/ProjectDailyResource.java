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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectDailyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectDailyRuntime;

@Slf4j
@Api(tags = {"项目日报"})
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

    @PreAuthorize("quickTest('IBIZPRO_PROJECTDAILY', 'DENY')")
    @ApiOperation(value = "汇总项目日报", tags = {"项目日报" },  notes = "汇总项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/projectdailies/{projectdaily_id}/summary")
    public ResponseEntity<ProjectDailyDTO> summary(@PathVariable("projectdaily_id") String projectdaily_id, @RequestBody ProjectDailyDTO projectdailydto) {
        IbizproProjectDaily domain = projectdailyMapping.toDomain(projectdailydto);
        domain.setIbizproprojectdailyid(projectdaily_id);
        domain = ibizproprojectdailyService.sumProjectDaily(domain);
        projectdailydto = projectdailyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(domain.getIbizproprojectdailyid());
        projectdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdailydto);
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

    @ApiOperation(value = "生成项目日报报表", tags = {"项目日报"}, notes = "生成项目日报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/projectdailies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproProjectDailySearchContext context, HttpServletResponse response) {
        try {
            ibizproprojectdailyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproprojectdailyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproprojectdailyRuntime);
        }
    }

    @ApiOperation(value = "打印项目日报", tags = {"项目日报"}, notes = "打印项目日报")
    @RequestMapping(method = RequestMethod.GET, value = "/projectdailies/{projectdaily_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("projectdaily_ids") Set<String> projectdaily_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproprojectdailyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproProjectDaily> domains = new ArrayList<>();
            for (String projectdaily_id : projectdaily_ids) {
                domains.add(ibizproprojectdailyService.get( projectdaily_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproProjectDaily[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproprojectdailyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", projectdaily_ids, e.getMessage()), Errors.INTERNALERROR, ibizproprojectdailyRuntime);
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

